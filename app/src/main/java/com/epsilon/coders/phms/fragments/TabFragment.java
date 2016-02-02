package com.epsilon.coders.phms.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epsilon.coders.phms.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(R.layout.tab_layout,null);

            viewPager = (ViewPager) x.findViewById(R.id.viewpager);
            setupViewPager(viewPager);

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        /**
//         *Set an Apater for the View Pager
//         */
//        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
//
//        /**
//         * Now , this is a workaround ,
//         * The setupWithViewPager dose't works without the runnable .
//         * Maybe a Support Library Bug .
//         */
//
//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                    tabLayout.setupWithViewPager(viewPager);
//                   }
//        });
        setupTabIcons();
        return x;

    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.homee,
               // R.drawable.homee,
                R.drawable.disease
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        //tabLayout.getTabAt(2).setIcon(tabIcons[2]);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new Home(), "Home");
       // adapter.addFrag(new ProfileActivity(), "Profiles");
        adapter.addFrag(new HealthTips(), "Health Tips");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }

    }


//    class MyAdapter extends FragmentPagerAdapter {
//
//        public MyAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        /**
//         * Return fragment with respect to Position .
//         */
//
//        @Override
//        public Fragment getItem(int position)
//        {
//          switch (position){
//              case 0 : return new Home();
//              case 1 : return new ProfileActivity();
//              case 2 : return new SocialFragment();
//          }
//        return null;
//        }
//
//        @Override
//        public int getCount() {
//
//            return int_items;
//
//        }
//
//        /**
//         * This method returns the title of the tab according to the position.
//         */
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//
//            switch (position){
//                case 0 :
//                    return "Home";
//                case 1 :
//                    return "Profiles";
//                case 2 :
//                    return "Tools";
//            }
//                return null;
//        }
//    }

}
