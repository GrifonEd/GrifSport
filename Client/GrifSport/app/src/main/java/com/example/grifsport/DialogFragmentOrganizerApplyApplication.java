package com.example.grifsport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentOrganizerApplyApplication#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentOrganizerApplyApplication extends androidx.fragment.app.DialogFragment   {

    private int event_id;
    private int profile_id;
    private String vacancy;
    private  String description="";
    ViewPagerAdapterApplcation viewPagerAdapter ;
    String access;
    ViewPager viewPager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogFragmentOrganizerApplyApplication() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogFragmentOrganizerApplyApplication newInstance(String param1, String param2) {
        DialogFragmentOrganizerApplyApplication fragment = new DialogFragmentOrganizerApplyApplication();
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
        event_id = getArguments().getInt("event_id",-1);
        profile_id = getArguments().getInt("profile_id",-1);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dialog_organizer_apply_application, null);

        ImageButton back = v.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    onDismiss(getDialog());
            }
        });
        viewPager = v.findViewById(R.id.viewPagerApplication);
        setupViewPagerAdapter(viewPager);





        return v;
    }

    private void setupViewPagerAdapter(final ViewPager viewPager){
        //список фрагментов "квартир"
        viewPagerAdapter = new ViewPagerAdapterApplcation(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,getActivity());

        //его создание
        viewPagerAdapter.addFragment(ApplicationAllFragment.newInstance(
                2,
                "",
                "",access,profile_id,event_id
        ));

        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);
    }




    public class ViewPagerAdapterApplcation extends FragmentPagerAdapter{

        private ArrayList<ApplicationAllFragment> fragmentList = new ArrayList<>();

        private Context context;

        private ArrayList<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapterApplcation(FragmentManager fm, int behavior, Context context) {
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

        void addFragment(ApplicationAllFragment fragment){
            fragmentList.add(fragment);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);

        }



    }
}