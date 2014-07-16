package com.lolmobile.app;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by Cpitzo on 7/7/2014.
 */
public class CurrStatFrag extends Fragment {
    private TextView tvSumName;
    private TextView tvWinLoss;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.currstatlayout, container, false);
        tvSumName = (TextView)rootView.findViewById(R.id.sumName);
        tvWinLoss = (TextView)rootView.findViewById(R.id.winLoss);

        tvSumName.setText(getArguments().getString("SummonerName"));


        //Lol4JClient client = new Lol4JClientImpl("bf37de44-361f-41fb-ad70-0d9f12b443bb");

        //new RetrieveStuffTask().execute(client);


    return rootView;
    }
    /*
   private class RetrieveStuffTask extends AsyncTask<Lol4JClient,Void,Integer> {
       private Exception exception;
       @Override
       protected Integer doInBackground(Lol4JClient... params) {
           try {
               String regionName = getArguments().getString("region");
               Region region = Region.create(regionName);
               SummonerDto summoner = params[0].getSummonerByName(getArguments().getString("SummonerName"), region);
                Log.v("id:", Long.toString(summoner.getId()));
               /*
               Map<PlayerStatsSummaryType,PlayerStatsSummary> statList =  params[0].getSummonerStats(summoner.ID, Season.SEASON4);
               PlayerStatsSummary pStats = statList.get(PlayerStatsSummaryType.RankedSolo5x5);
//               tvWinLoss.setText(pStats.get(0).getWins() + "/" + pStats.get(0).getLosses());
               Log.v("queue","here");
               List<League> leagues = params[0].getLeaguesBySummonerID(summoner.ID);

               // Iterate through all leagues
               for (League league : leagues) {
                   // Check if Soloqueue
                   // (could also be done with the key of the map)
                   if (league.queue == LeagueType.RANKED_SOLO_5x5) {
                      // System.out.println("Tier: " + league.getTier());

                       // Get all divisions of the league
                       List<LeagueEntry> leagueItems = league.entries;
                      //  System.out.println("Division: " + leagueItem.division));
                       //System.out.println(league.tier); //might need to convert to string


                   }
               }

               List<MasteryPage> mpages = params[0].getMasteryPagesByID(summoner.ID);
               List<MasterySlot> masteryList = null;
               int offense = 0, defense = 0, utility = 0;
               for(MasteryPage m: mpages){
                   if(m.current)
                       masteryList = m.masteries;
               }
               for(MasterySlot m: masteryList){
                  String idString= Integer.toString(m.mastery.ID);
                   if(idString.substring(1,2).equals("1"))
                       offense++;
                   else if(idString.substring(1,2).equals("2"))
                        defense++;
                   else;
                    utility++;

               }

                //check runeSlots for which color, then add up same name runes and print
               List<RunePage> rpages = params[0].getRunePagesByID(summoner.ID);
              // Map<String, Rune> rlist =  runeList.getData();
               HashMap<String,Integer> redRunes = new HashMap<String, Integer>();
               HashMap<String,Integer> yellowRunes = new HashMap<String, Integer>();
               HashMap<String,Integer> blueRunes = new HashMap<String, Integer>();
               HashMap<String,Integer> quintRunes = new HashMap<String, Integer>();
               Rune tempRune;
               Rune realRune;
               List<RuneSlot> rslots = null;

               for(RunePage r: rpages){
                   if(r.current)
                       rslots = r.slots;
               }
               for(RuneSlot r: rslots){
                   if(r.type == RuneType.MARK)
                        redRunes.put(r.rune.name, redRunes.get(r.rune.name)+1);
                   else if(r.type == RuneType.GLYPH){
                       blueRunes.put(r.rune.name, blueRunes.get(r.rune.name)+1);
                   }
                   else if(r.type == RuneType.SEAL) {
                       yellowRunes.put(r.rune.name, yellowRunes.get(r.rune.name) + 1);
                   }
                   else {
                       quintRunes.put(r.rune.name, quintRunes.get(r.rune.name) + 1);
                   }

                 }


           }
           catch(TooManyRequestsException e){
               Log.v("api error",e.toString());
           }
           return null;
       }
   }
                   */
}
