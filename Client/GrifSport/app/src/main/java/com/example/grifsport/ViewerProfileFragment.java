package com.example.grifsport;

import android.content.Context;
import android.os.Bundle;
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
 * Use the {@link ViewerProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewerProfileFragment extends Fragment {
    String access;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapterViewer ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewerProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkerProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewerProfileFragment newInstance(String param1, String param2) {
        ViewerProfileFragment fragment = new ViewerProfileFragment();
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
        access = getArguments().getString("access","");
        View rootView = inflater.inflate(R.layout.fragment_viewer_profile, container, false);
        viewPager = rootView.findViewById(R.id.viewPagerViewer);
        setupViewPagerAdapter(viewPager);
        return rootView;
    }

    private void setupViewPagerAdapter(final ViewPager viewPager){
        //список фрагментов "квартир"
        viewPagerAdapterViewer = new ViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,getActivity());

        //его создание
        viewPagerAdapterViewer.addFragment(EventsAllFragmentViewer.newInstance(
                2,
                "",
                "",access
        ));

        viewPagerAdapterViewer.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapterViewer);
    }



    public class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<EventsAllFragmentViewer> fragmentList = new ArrayList<>();

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

        void addFragment(EventsAllFragmentViewer fragment){
            fragmentList.add(fragment);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);

        }



    }
}