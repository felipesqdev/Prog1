package com.example.felipe.prog1;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * Created by Felipe on 19/09/2015.
 */

public class FragmentTab1 extends Fragment implements ActionBar.TabListener, View.OnClickListener{
    private static final String TAG = FragmentTab1.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static View myView;

    public static FragmentTab1 newInstance(int sectionNumber) {
        FragmentTab1 fragment = new FragmentTab1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTab1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // int s;
        // s = getArguments().getInt(ARG_SECTION_NUMBER, 0);

        myView = inflater.inflate(R.layout.fragment_main1, container, false);

        return myView;
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick === " + v.getId());

        switch(v.getId())
        {
            case R.id.f3button1:
                Log.d(TAG, "onClick - speak");

                EditText enteredText = (EditText)myView.findViewById(R.id.f3text1);

                if (enteredText == null) {
                    Log.d(TAG, "onClick - speak - enteredText == null");
                }

                String words = enteredText.getText().toString();

                Log.d(TAG, "onClick - speak - words = " + words);

                SpeakWords.DoSpeak(words);
                break;
            /*
            case : R.id.secondbutton:
                // second button clicked
                break:
            */
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        MainActivity.SetViewPagerPosition(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
}
