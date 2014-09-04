package com.lolmobile.app;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;



/**
 * Created by Cpitzo on 7/7/2014.
 */
public class CurrStatFrag extends Fragment {
    private TextView tvSumName;
    private TextView tvWinLoss;
    private TextView tvTier;
    private TextView tvRPage;
    private TextView tvRed;
    private TextView tvBlue;
    private TextView tvYellow;
    private TextView tvQuint;
    private TextView tvMaster;
    private ImageView tView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.currstatlayout, container, false);
        tvSumName = (TextView)rootView.findViewById(R.id.sumName);
        tvWinLoss = (TextView)rootView.findViewById(R.id.winLoss);
        tvTier = (TextView)rootView.findViewById(R.id.rankedStats);
        tvRPage =(TextView)rootView.findViewById(R.id.rpageName);
        tvRed = (TextView)rootView.findViewById(R.id.redTV);
        tvBlue = (TextView)rootView.findViewById(R.id.blueTV);
        tvYellow = (TextView)rootView.findViewById(R.id.yellowTV);
        tvQuint = (TextView)rootView.findViewById(R.id.quintTV);
        tvMaster = (TextView)rootView.findViewById(R.id.mpageName);
        tView = (ImageView)rootView.findViewById(R.id.imageView);

        tvSumName.setText(getArguments().getString("SummonerName"));

    JRiot lol = new JRiot("bf37de44-361f-41fb-ad70-0d9f12b443bb",getArguments().getString("region"));
        new RetrieveStuffTask().execute(lol);
    return rootView;
    }

   private class RetrieveStuffTask extends AsyncTask<JRiot,Void,String> {
       private Exception exception;
        private String sumWL;
       private String tier;
       private int[] masters = new int[3];
       private String rname;
       private String mname;
       private  HashMap<String,Integer> redRunes = new HashMap<String, Integer>();
       private  HashMap<String,Integer> yellowRunes = new HashMap<String, Integer>();
       private  HashMap<String,Integer> blueRunes = new HashMap<String, Integer>();
       private  HashMap<String,Integer> quintRunes = new HashMap<String, Integer>();
       private String tname;
       @Override
       protected String doInBackground(JRiot... params) {
           try {
               //Log.v("purpid:", getArguments().getString("SummonerName"));
               Summoner summoner = params[0].getSummoner(getArguments().getString("SummonerName"));


               PlayerStatsSummaryList statList =  params[0].getPlayerStatsSummaryList(summoner.getId(), 4);
               ArrayList<PlayerStatsSummary> pStats = statList.getPlayerStatSummaries();
//               tvWinLoss.setText(pStats.get(0).getWins() + "/" + pStats.get(0).getLosses());
               sumWL = pStats.get(0).getWins() + "/" + pStats.get(0).getLosses();
               Log.v("queue",sumWL);
               List<League> leagues = params[0].getLeagues(summoner.getId());

               // Iterate through all leagues
               // Check if Soloqueue
// (could also be done with the key of the map)
//getName()
               for (League league : leagues)
                   if (league.getQueue().equals("RANKED_SOLO_5x5")) {
                       List<LeagueEntry> leagueItems = league.getEntries();
                       //  System.out.println("Division: " + leagueItem.getRank(()));
                       String division = leagueItems.get(0).getDivision();
                      int points = leagueItems.get(0).getLeaguePoints();
                       tier = league.getTier() + " "+division+" "+points;
                       tname = league.getTier();
                       //System.out.println(league.getTier()); //might need to convert to string


                   }

               MasteryPages mpages = params[0].getMasteryPages(summoner.getId());
               HashSet<MasteryPage> masteryList = mpages.getPages();
               ArrayList<Mastery> talents = null;
               int offense = 0, defense = 0, utility = 0;
               for(MasteryPage m: masteryList){
                   if(m.isCurrent()) {
                       mname = m.getName();
                       Log.v("hit",Integer.toString( m.getMasteries().get(0).getId()));
                       for(Mastery t: m.getMasteries()){
                           String idString= Integer.toString(t.getId());
                           if(idString.substring(1,2).equals("1"))
                               offense++;
                           else if(idString.substring(1,2).equals("2"))
                               defense++;
                           else;
                           utility++;
                       }
                   }
               }Log.v("hit",Integer.toString(offense));
                masters[0] = offense;
               masters[1] = defense;
               masters[2] = utility;

                //check runeSlots for which color, then add up same name runes and print
               RunePages rpages = params[0].getRunePages(summoner.getId());
               ArrayList<RunePage> listOfRunes = rpages.getPages();
              // Map<String, Rune> rlist =  runeList.getData();

               List<RuneSlot> rslots = null;

               for(RunePage r: listOfRunes){
                   if(r.isCurrent()) {
                       rslots = r.getSlots();
                        rname = r.getName();
                   }
               }

               for(RuneSlot r: rslots){
                   if(r.getRuneSlotId()<=9)
                       if(redRunes.get(params[0].getRunes(r.getRuneId()).getName()) == null)
                           redRunes.put(params[0].getRunes(r.getRuneId()).getName(),1);
                       else
                            redRunes.put(params[0].getRunes(r.getRuneId()).getName(), redRunes.get(params[0].getRunes(r.getRuneId()).getName())+1);
                   else if(r.getRuneSlotId()<=18){
                       if(blueRunes.get(params[0].getRunes(r.getRuneId()).getName()) == null)
                           blueRunes.put(params[0].getRunes(r.getRuneId()).getName(),1);
                       else
                           blueRunes.put(params[0].getRunes(r.getRuneId()).getName(), blueRunes.get(params[0].getRunes(r.getRuneId()).getName())+1);
                   }
                   else if(r.getRuneSlotId()<=27) {
                       if(yellowRunes.get(params[0].getRunes(r.getRuneId()).getName()) == null)
                           yellowRunes.put(params[0].getRunes(r.getRuneId()).getName(),1);
                       else
                           yellowRunes.put(params[0].getRunes(r.getRuneId()).getName(), yellowRunes.get(params[0].getRunes(r.getRuneId()).getName())+1);
                   }
                   else {
                       if(quintRunes.get(params[0].getRunes(r.getRuneId()).getName()) == null)
                           quintRunes.put(params[0].getRunes(r.getRuneId()).getName(),1);
                       else
                           quintRunes.put(params[0].getRunes(r.getRuneId()).getName(), quintRunes.get(params[0].getRunes(r.getRuneId()).getName())+1);
                   }
                 }


           }
           catch(JRiotException e){
               Log.v("api error",e.toString());
           }
           return null;
       }
       @Override
       protected void onPostExecute(String result) {

           tvWinLoss.setText(sumWL);
           tvRPage.setText(rname);
           tvMaster.setText("   "+mname+" "+masters[0]+"/"+masters[1]+"/"+masters[2]);
           tvTier.setText(tier);
           int c =0;
           String bRunes = "";
           for(Map.Entry<String, Integer> entry : blueRunes.entrySet()) {
               if(c!=0)
               bRunes +=","+entry.getKey()+":"+entry.getValue();
               else
                   bRunes +=entry.getKey()+":"+entry.getValue();
               c++;
           }
           tvBlue.setText(bRunes);
           c = 0;
           String rRunes = "";
           for(Map.Entry<String, Integer> entry : redRunes.entrySet()) {
               if(c!=0)
                   rRunes +=","+entry.getKey()+":"+entry.getValue();
               else
                   rRunes +=entry.getKey()+":"+entry.getValue();
               c++;
           }
           tvRed.setText(rRunes);
           c = 0;
           String yRunes = "";
           for(Map.Entry<String, Integer> entry : yellowRunes.entrySet()) {
               if(c!=0)
                   yRunes +=","+entry.getKey()+":"+entry.getValue();
               else
                   yRunes +=entry.getKey()+":"+entry.getValue();
               c++;
           }
           tvYellow.setText(yRunes);
           c = 0;
           String qRunes = "";
           for(Map.Entry<String, Integer> entry : quintRunes.entrySet()) {
               if(c!=0)
                   qRunes +=","+entry.getKey()+":"+entry.getValue();
               else
                   qRunes +=entry.getKey()+":"+entry.getValue();
               c++;
           }
           tvQuint.setText(qRunes);
        if(tname.equals("BRONZE"))
            tView.setImageResource(R.drawable.bronzeicon);
        else if(tname.equals("SILVER"))
            tView.setImageResource(R.drawable.silvericon);
        else if(tname.equals("GOLD"))
            tView.setImageResource(R.drawable.goldicon);
        else if(tname.equals("PLATINUM"))
            tView.setImageResource(R.drawable.platicon);
        else if(tname.equals("DIAMOND"))
            tView.setImageResource(R.drawable.diamondicon);
        else if(tname.equals("CHALLENGER"))
            tView.setImageResource(R.drawable.challengericon);
           else
            tView.setImageResource(R.drawable.unrankedicon);
       }

   }

}
