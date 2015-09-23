package com.example.felipe.prog1;

import java.util.Locale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

//public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    static ViewPager mViewPager;

    int visiters = 0;

    static SpeakWords MySpeakWords = null;

    public static void SetViewPagerPosition(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG","onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG","onRestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        visiters++;
        Log.d("TAG","onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("visiters",visiters);
        Log.d("TAG",visiters+" visiters  was saved ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        visiters=savedInstanceState.getInt("visiters");
        Log.d("TAG",visiters+" visiters was restored");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy");

        if (isFinishing()) {
            MySpeakWords.Destroy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MySpeakWords.ActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","onCreate");

        if (MySpeakWords == null) {
            MySpeakWords = new SpeakWords(this);
        }

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

/*
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
//                          .setTabListener(new MyTabListener<FragmentTab1>(this, "frag1", FragmentTab1.class))
                            );
        }
    }
*/
        actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(0))
                .setTabListener(new FragmentTab1()));
        actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(1))
                .setTabListener(new FragmentTab2()));
        actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(2))
                .setTabListener(new FragmentTab3()));

    } // onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//
// ACTIONBAR TABLISTENER METHODS --- se estiverem em FragmentTabX.java nao precisa...
//
/*
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
*/
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    default:
                    return FragmentTab1.newInstance(1);
                case 1:
                    return FragmentTab2.newInstance(2);
                case 2:
                    return FragmentTab3.newInstance(3);
            }
            // return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

/*

    // A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {
        //
        // The fragment argument representing the section number for this
        // fragment.
        //
        private static final String ARG_SECTION_NUMBER = "section_number";

        //
        // Returns a new instance of this fragment for the given section
        // number.
        //
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // View rootView = inflater.inflate(R.layout.fragment_main3, container, false);
            View rootView = null;
            int s;

            s = getArguments().getInt(ARG_SECTION_NUMBER, 0);
            switch (s) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_main3, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_main2, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_main1, container, false);
                    break;
            }
            return rootView;
        }
    }
*/
}
