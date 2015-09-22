package com.example.felipe.prog1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Felipe on 19/09/2015.
 */

public class FragmentTab3 extends Fragment implements ActionBar.TabListener, View.OnClickListener{
    private static final String TAG = FragmentTab3.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static View myView;

    public static FragmentTab3 newInstance(int sectionNumber) {
        FragmentTab3 fragment = new FragmentTab3();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTab3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // int s;
        // s = getArguments().getInt(ARG_SECTION_NUMBER, 0);

        myView = inflater.inflate(R.layout.fragment_main3, container, false);

        return myView;
    }

    public void onClick(View v) {

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