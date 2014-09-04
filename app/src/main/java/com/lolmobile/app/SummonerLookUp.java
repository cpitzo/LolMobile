package com.lolmobile.app;



import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Cpitzo on 7/5/2014.
 */
public class SummonerLookUp extends Fragment {

    public SummonerLookUp(){
    }
    public static final SummonerLookUp newInstance(String reg, String name)
    {
        SummonerLookUp f = new SummonerLookUp();
        Bundle bdl = new Bundle(2);
        bdl.putString("region", reg);
        bdl.putString("SummonerName", name);
        f.setArguments(bdl);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.currstatlayout, container, false);
        setupTabs();
        return rootView;
    }


        private void setupTabs() {
            ActionBar actionBar = this.getActivity().getActionBar();
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBar.setDisplayShowTitleEnabled(true);

            Bundle tab1Bundle = new Bundle();
            tab1Bundle.putString("region",  getArguments().getString("region"));
            tab1Bundle.putString("SummonerName",  getArguments().getString("SummonerName"));

            ActionBar.Tab tab1 = actionBar
                    .newTab()
                    .setText("Current Stats")
                    .setTag("HomeTimelineFragment")
                    .setTabListener(
                            new FragmentTabListener<CurrStatFrag>( this.getActivity(), "Current Stats",
                                    CurrStatFrag.class,tab1Bundle));

            ActionBar.Tab tab2 = actionBar
                    .newTab()
                    .setText("Match History")
                    .setTag("HomeTimelineFragment")
                    .setTabListener(
                            new FragmentTabListener<MatchHistory>( this.getActivity(), "Match History",
                                    MatchHistory.class,tab1Bundle));

            ActionBar.Tab tab3 = actionBar
                    .newTab()
                    .setText("Ranked and Team stats")
                    .setTag("HomeTimelineFragment")
                    .setTabListener(
                            new FragmentTabListener<RankedTeamStats>( this.getActivity(), "Ranked and Team stats",
                                    RankedTeamStats.class,tab1Bundle));



            actionBar.addTab(tab1);
            actionBar.addTab(tab2);
            actionBar.addTab(tab3);
            actionBar.selectTab(tab1);

        }
        /*try{

            Summoner summoner = lol.getSummoner(intent.getExtras().getString("SummonerName")); //ask summoner name
            // Get mastery pages of a summoner
            MasteryPages masteryPages = lol.getMasteryPages(summoner.getId());

            // Get rune pages of a summoner
            RunePages runePages = lol.getRunePages(summoner.getId());

            // Get summoner stats
            PlayerStatsSummaryList playerStats = lol.getPlayerStatsSummaryList(summoner.getId(), 4);

            RankedStats rankedStats = lol.getRankedStats(summoner.getId(), 4);
            //print stats and other stuff

            List<Team> teams = lol.getTeams(summoner.getId());

            List<League> leagues = lol.getLeagues(summoner.getId());
            // Iterate through all leagues
            for (League league : leagues) {

                // Check if Soloqueue
                // (could also be done with the key of the map)
                if (league.getQueue().equals("RANKED_SOLO_5x5")) {

                    System.out.println("Soloqueue:");

                    // Print some stuff about the League
                    System.out.println("Name: " + league.getName());
                    System.out.println("Tier: " + league.getTier());

                    // Get all divisions of the league
                    List<LeagueEntry> leagueItems = league.getEntries();

                    // Go through all divisions
                    for (LeagueEntry leagueItem : leagueItems) {

                        // Check if the summoner we are looking for is in the division
                        if (Long.parseLong(leagueItem.getPlayerOrTeamId()) == summoner.getId()) {

                            // Print some stuff about the division
                            System.out.println("Division: " + leagueItem.getDivision());
                            System.out.println("Wins: " + leagueItem.getWins());
                        }

                    }

                }

            }
        }
        catch(JRiotException e){
            Log.i("failed stats retrieve", "no stats found");
        }*/

}
