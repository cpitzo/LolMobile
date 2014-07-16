package com.lolmobile.app;

/**
 * Created by Cpitzo on 7/14/2014.
 */
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class RankedTeamStats extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rteamstats, container, false);
      //  RiotAPI lol = new RiotAPI(Region.NA, "bf37de44-361f-41fb-ad70-0d9f12b443bb", RateLimiter.defaultDevelopmentRateLimiter());
     //   new RetrieveStuffTask().execute(lol);
        return rootView;
    }
/*
    private class RetrieveStuffTask extends AsyncTask<RiotAPI,Void,Integer> {
        private Exception exception;

        @Override
        protected Integer doInBackground(RiotAPI... params) {
            try {
            } catch (APIException e) {
            }
            return null;
        }
    }*/
}
