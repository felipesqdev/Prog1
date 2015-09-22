package com.example.felipe.prog1;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Felipe on 19/09/2015.
 */

public class FragmentTab1 extends Fragment implements ActionBar.TabListener {

    public FragmentTab1 (){

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.

        // correto
        // mViewPager.setCurrentItem(tab.getPosition());

        MainActivity.SetViewPagerPosition(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
}
