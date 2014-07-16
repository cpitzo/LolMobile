package com.lolmobile.app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import android.os.Handler;

/**
 * Created by Cpitzo on 5/14/2014.
 */
public class Timer extends Fragment implements View.OnClickListener {
    private TextView dragText = null;
    private ImageButton drag;
    private TextView baronText = null;
    private ImageButton baron;
    private TextView mblueText = null;
    private ImageButton mblue;
    private TextView eblueText = null;
    private ImageButton eblue;
    private TextView mredText = null;
    private ImageButton mred;
    private TextView eredText = null;
    private ImageButton ered;
    private String status = "alive";
    private Handler mHandler = new Handler();
    private int DRAGTIME = 360000;
    private int BARONTIME = 420000;
    private int BUFFTIME = 300000;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timer, container, false);

        drag = (ImageButton) rootView.findViewById(R.id.dragon);
        dragText = (TextView) rootView.findViewById(R.id.dtext);
        dragText.setText(status);
        drag.setOnClickListener(this);

        baron = (ImageButton) rootView.findViewById(R.id.baron);
        baronText = (TextView) rootView.findViewById(R.id.btext);
        baronText.setText(status);
        baron.setOnClickListener(this);

        mblue = (ImageButton) rootView.findViewById(R.id.myblue);
        mblueText = (TextView) rootView.findViewById(R.id.mybluetext);
        mblueText.setText(status);
        mblue.setOnClickListener(this);

        eblue = (ImageButton) rootView.findViewById(R.id.eblue);
        eblueText = (TextView) rootView.findViewById(R.id.ebluetext);
        eblueText.setText(status);
        eblue.setOnClickListener(this);

        mred = (ImageButton) rootView.findViewById(R.id.myred);
        mredText = (TextView) rootView.findViewById(R.id.myredtext);
        mredText.setText(status);
        mred.setOnClickListener(this);

        ered = (ImageButton) rootView.findViewById(R.id.ered);
        eredText = (TextView) rootView.findViewById(R.id.eredtext);
        eredText.setText(status);
        ered.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View view) {
        if (view == drag) {
            Jtime dragT = new Jtime(DRAGTIME, dragText);
        }
        else if(view == baron) {
            Jtime baronT = new Jtime(BARONTIME, baronText);
        }
        else if(view == mblue){
            Jtime mblueT = new Jtime(BUFFTIME, mblueText);
        }
        else if(view == eblue){
            Jtime eblueT = new Jtime(BUFFTIME, eblueText);
        }
        else if(view == mred){
            Jtime mredT = new Jtime(BUFFTIME, mredText);
        }
        else if(view == ered){
            Jtime eredT = new Jtime(BUFFTIME, eredText);
        }
    }

}