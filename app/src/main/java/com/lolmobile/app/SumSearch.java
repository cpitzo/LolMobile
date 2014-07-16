package com.lolmobile.app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Cpitzo on 7/7/2014.
 */
public class SumSearch extends Fragment {
    private EditText sumName;
    private Button sumSearch;
    private Spinner spinner;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lookup, container, false);

         spinner = (Spinner) rootView.findViewById(R.id.pickregion);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.region_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        sumName = (EditText)rootView.findViewById(R.id.sumname);
        sumSearch = (Button)rootView.findViewById((R.id.searchbutton));
        sumSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSumLook();
            }});

        return rootView;
    }
    private void goToSumLook(){

    Bundle bundle = new Bundle();
        bundle.putString("region",spinner.getSelectedItem().toString());
        bundle.putString("SummonerName", sumName.getText().toString());
        SummonerLookUp sumfrag = new SummonerLookUp();
        Fragment tempFrag = sumfrag.newInstance(spinner.getSelectedItem().toString(),sumName.getText().toString());
        FragmentTransaction sft = this.getActivity().getSupportFragmentManager().beginTransaction();
        sft.add(0,tempFrag);
        sft.commit();
        //sumfrag.instantiate(this.getActivity(), "SummonerLookUp", bundle);

    }

}
