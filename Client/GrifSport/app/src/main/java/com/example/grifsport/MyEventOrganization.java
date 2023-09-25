package com.example.grifsport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;

import com.example.grifsport.databinding.ActivityMenuBinding;
import com.example.grifsport.databinding.ActivityMyEventOrganizationBinding;

import java.util.ArrayList;

public class MyEventOrganization extends AppCompatActivity {
    ActivityMyEventOrganizationBinding binding;
    String access;
    private SpeechRecognizer sr;
    Bundle bundle;
    String frag;
    Dialog dialog;
    int user_id;
    private int profile_id;
    ViewPagerAdapter viewPagerAdapterOrganizerNow ;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyEventOrganizationBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        access = intent.getStringExtra("access");
        user_id= intent.getIntExtra("user_id",0);
        viewPager = binding.viewPager;
        setupViewPagerAdapter(viewPager);
        setContentView(binding.getRoot());


    }


    private void setupViewPagerAdapter(final ViewPager viewPager){
        //список фрагментов "квартир"
        viewPagerAdapterOrganizerNow = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this);

        //его создание
        viewPagerAdapterOrganizerNow.addFragment(EventsAllFragmentOrganizerNow.newInstance(
                2,
                "",
                "",access
        ));

        viewPagerAdapterOrganizerNow.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapterOrganizerNow);
    }



    public class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<EventsAllFragmentOrganizerNow> fragmentList = new ArrayList<>();

        private Context context;

        private ArrayList<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm, int behavior, Context context) {
            super(fm, behavior);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(EventsAllFragmentOrganizerNow fragment){
            fragmentList.add(fragment);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);

        }



    }

}