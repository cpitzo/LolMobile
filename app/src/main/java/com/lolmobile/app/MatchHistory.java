package com.lolmobile.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

       // new RetrieveStuffTask().execute(lol);

        return rootView;
    }
    /*
    private class RetrieveStuffTask extends AsyncTask<RiotAPI,Void,Integer> {
        private Exception exception;

        @Override
        protected Integer doInBackground(RiotAPI... params) {
            try {
                Summoner summoner = params[0].getSummoner(getArguments().getString("SummonerName"));
                List<Game> recGames =  params[0].getRecentGames(summoner.ID);
                for(Game g: recGames){

                }
            } catch (APIException e) {
            }
            return null;
        }
    }*/
}
