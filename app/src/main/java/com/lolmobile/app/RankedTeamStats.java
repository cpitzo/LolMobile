package com.lolmobile.app;

/**
 * Created by Cpitzo on 7/14/2014.
 */
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RankedTeamStats extends Fragment {
    private RelativeLayout rLayout;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rteamstats, container, false);
        JRiot lol = new JRiot("bf37de44-361f-41fb-ad70-0d9f12b443bb",getArguments().getString("region"));
       new RetrieveStuffTask().execute(lol);
         rLayout = (RelativeLayout)rootView.findViewById(R.id.rstats);


        return rootView;
    }

    private class RetrieveStuffTask extends AsyncTask<JRiot,Void,Integer> {
        private Exception exception;
        private ArrayList<ChampionStats> maxChamps;
        private String[] names =new String[3];
        private  List<Team> rankedTeams;
        private Image[] champPics = new Image[3];
        @Override
        protected Integer doInBackground(JRiot... params) {
            try {
                Summoner summoner = params[0].getSummoner(getArguments().getString("SummonerName"));
                List<Team> rankedTeams = params[0].getTeams(summoner.getId());

               RankedStats rStats = params[0].getRankedStats(summoner.getId(), 4);
                ArrayList<ChampionStats> rChamps =rStats.getChampions();
                int counter = 0;
               ArrayList<ChampionStats> maxChamps =new ArrayList<>();
                int maxPlays = 1;
                for(ChampionStats c: rChamps){
                   int currentStats = c.getStats().getTotalSessionsPlayed();
                    if(currentStats>=maxPlays) {
                        if(maxPlays ==0)
                            maxChamps.add(c);
                        else {
                            for (int i = 0; i < maxChamps.size(); i++) {
                                if (currentStats > maxChamps.get(i).getStats().getTotalSessionsPlayed()) {
                                    maxChamps.add(i, c);
                                }
                            }
                            if(maxChamps.size()>3)
                                maxChamps.remove(3);
                        }
                        maxPlays = currentStats;
                    }

                    counter++;
                    for(int i = 0; i <maxChamps.size(); i++) {
                        names[i] = params[0].getChampion(maxChamps.get(i).getId()).getName();
                       champPics[i]= params[0].getChampion(maxChamps.get(i).getId()).getImage();

                    }
                }
            } catch (JRiotException e) {
                Log.v("api error", e.toString());
            }

            return null;
        }
        protected void onPostExecute(String result) {
            int count = 0;
            int ids = 0;
            for(ChampionStats c: maxChamps){

                RelativeLayout.LayoutParams lParamsName = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lParamsName.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lParamsName.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                TextView tvName = new TextView(getActivity());
                tvName.setText(names[count]);
                tvName.setId(ids++);
                tvName.setLayoutParams(lParamsName);
                rLayout.addView(tvName);

                RelativeLayout.LayoutParams lParamsImage = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lParamsImage.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                ImageView iV = new ImageView(getActivity());

                iV.setId(ids++);
                iV.setLayoutParams(lParamsImage);
                rLayout.addView(iV);


                count++;
            }
        }
    }
}
