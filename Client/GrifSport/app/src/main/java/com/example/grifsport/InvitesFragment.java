package com.example.grifsport;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvitesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvitesFragment extends Fragment  {
   ViewPagerAdapter viewPagerAdapter ;
    String access;
    ViewPager viewPager;
    int profile_id;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvitesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notes.
     */
    // TODO: Rename and change types and number of parameters
    public static InvitesFragment newInstance(String param1, String param2) {
        InvitesFragment fragment = new InvitesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        access = getArguments().getString("access","");
        profile_id = getArguments().getInt("profile_id",-1);
        Log.d("Calendar",access);
        View rootView = inflater.inflate(R.layout.fragment_invites, container, false);
        viewPager = rootView.findViewById(R.id.viewPagerInvites);
        setupViewPagerAdapter(viewPager);
        return rootView;
    }


    private void setupViewPagerAdapter(final ViewPager viewPager){
        //список фрагментов "квартир"
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,getActivity());

        //его создание
        viewPagerAdapter.addFragment(InvitesAllFragment.newInstance(
                2,
                "",
                "",access,profile_id
        ));

        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);
    }



    public class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<InvitesAllFragment> fragmentList = new ArrayList<>();

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

        void addFragment(InvitesAllFragment fragment){
            fragmentList.add(fragment);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);

        }



    }
}