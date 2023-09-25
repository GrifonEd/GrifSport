package com.example.grifsport;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.grifsport.databinding.ActivityMenuBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements AdapterInvite.OnInviteListener {
    ActivityMenuBinding binding;
    String access;
    private SpeechRecognizer sr;
    Bundle bundle;
    String frag;
    Dialog dialog;
    int user_id;
    private int profile_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        access = intent.getStringExtra("access");
        frag = intent.getStringExtra("fragment");
        user_id= intent.getIntExtra("user_id",0);

        bundle = new Bundle();
        setContentView(binding.getRoot());

        Call<ProfileResponse> call = ApiClient.profileServiceGet().getProfile(user_id);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                profile_id= response.body().getId();
                Fragment frg = new EventsFragment();
                bundle.putString("access", access);
                bundle.putInt("profile_id",profile_id);
                frg.setArguments(bundle);
                replaceFragment(frg,bundle);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });


        //setupViewPagerAdapter(binding.viewPager);

        //binding.all.setBackgroundColor(getResources().getColor(R.color.teal_phone));




        if (frag!=null)
            if(frag.equals("events"))
                replaceFragment(new EventsFragment(), bundle);



        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MenuActivity.this, MainActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });



        binding.infoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MenuActivity.this, ProfileActivity.class);
                intent2.putExtra("access",access);
                intent2.putExtra("user_id",user_id);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });






        binding.bottomNavigationView.setSelectedItemId(R.id.events);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


            switch (item.getItemId()){
                case R.id.Invites: {
                   // binding.viewPager.setVisibility(View.INVISIBLE);


                    replaceFragment(new InvitesFragment(), bundle);
                    //binding.all.setBackgroundColor(getResources().getColor(R.color.teal_phone));
                    break;


                }
                case R.id.events: {
                   // setupViewPagerAdapter(binding.viewPager);
                   // binding.viewPager.setVisibility(View.VISIBLE);

                    replaceFragment(new EventsFragment(), bundle);
                    //binding.all.setBackgroundColor(getResources().getColor(R.color.teal_phone));
                    break;
                }



                case R.id.EventOrganiztion:
                    Intent intent2 = new Intent(MenuActivity.this,MyEventOrganization.class) ;
                    intent2.putExtra("access", access);
                    intent2.putExtra("user_id",user_id);
                    startActivity(intent2);
                    break;
                    //setupViewPagerAdapterTests(binding.viewPager);
                  //  binding.viewPager.setVisibility(View.INVISIBLE);
                    /*
                    replaceFragment(new TestsFragment(),bundle);
                    binding.all.setBackgroundColor(getResources().getColor(R.color.teal_phone));
                    break;

                     */
                case R.id.NewEvent:
                   // binding.viewPager.setVisibility(View.INVISIBLE);

                    Intent intent1 = new Intent(MenuActivity.this,NewEventActivity.class) ;
                    intent1.putExtra("access", access);
                    intent1.putExtra("user_id",user_id);
                    startActivity(intent1);
                    break;


            }



            return true;
        });


    }


    private void replaceFragment(Fragment fragment,Bundle bundle){
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onInviteListener(Intent intent) {
        replaceFragment(new InvitesFragment(),bundle);
    }
}