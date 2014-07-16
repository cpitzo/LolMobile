package com.lolmobile.app;

import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Cpitzo on 6/18/2014.
 */
public class Jtime{
private int countDown;
private StopWatch watch;
    private String status = "alive";
    private TextView name;
    private Handler mHandler = new Handler();
    public Jtime(int time, TextView text){
        countDown = time;
        name = text;
         watch = new StopWatch();
        watch.start();
        mHandler.removeCallbacks(updateTime);
        mHandler.postDelayed(updateTime, 0);
    }

    private Runnable updateTime = new Runnable() {
        public void run() {

            if(watch == null)
                ;
            else if (watch.getTime() >= countDown) {
                mHandler.removeCallbacks(updateTime);
                watch.stop();
                name.setText(status);
            }
            else if(watch.isStarted()){
                watch.split();
                String tempTime = Long.toString(countDown - watch.getSplitTime());
                String timeTemplate = null;
                 if(countDown-watch.getSplitTime()<= 1000)
                    timeTemplate  = "0";
                 else if(countDown-watch.getSplitTime()<= 10000)
                     timeTemplate = tempTime.charAt(0) +"s";
                 else if(countDown-watch.getSplitTime()<= 100000)
                    timeTemplate = tempTime.substring(0,2) +"s";
                else
                    timeTemplate = tempTime.substring(0,3) +"s";
                name.setText(timeTemplate);
                watch.unsplit();
                mHandler.postDelayed(this,1);
            }

        }
    };
}

