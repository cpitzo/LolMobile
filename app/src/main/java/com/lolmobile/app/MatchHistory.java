package com.lolmobile.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Cpitzo on 7/14/2014.
 */
public class MatchHistory extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mhistory, container, false);


        JRiot lol = new JRiot("bf37de44-361f-41fb-ad70-0d9f12b443bb",getArguments().getString("region"));
        new RetrieveStuffTask().execute(lol);
        return rootView;
    }

    private class RetrieveStuffTask extends AsyncTask<JRiot,Void,Integer> {
        private Exception exception;

        @Override
        protected Integer doInBackground(JRiot... params) {
            try {
                Summoner summoner = params[0].getSummoner(getArguments().getString("SummonerName"));
                RecentGames recGames =  params[0].getRecentGames(summoner.getId());
               ArrayList<Game> games = recGames.getGames();
                for(Game g: games){

                }
            } catch (JRiotException e) {
                Log.v("api error", e.toString());
            }
            return null;
        }
    }
}
