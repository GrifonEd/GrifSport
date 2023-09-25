package com.example.grifsport;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import com.example.grifsport.databinding.ActivityProfileBinding;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    int user_id;
    String access;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        access = intent.getStringExtra("access");
        user_id = intent.getIntExtra("user_id",0);
        bundle = new Bundle();
        bundle.putString("access", access);
        Fragment frg = new ParticipantProfileFragment();
        frg.setArguments(bundle);
        replaceFragment(frg,bundle);
        loadUserInfo();

        binding.profileEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(ProfileActivity.this,ProfileEditActivity.class));
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.bottomNavigationView.setSelectedItemId(R.id.participant);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.participant: {
                    // binding.viewPager.setVisibility(View.INVISIBLE);

                    replaceFragment(new ParticipantProfileFragment(), bundle);
                    break;
                }
                case R.id.organizer: {
                    // setupViewPagerAdapter(binding.viewPager);
                    // binding.viewPager.setVisibility(View.VISIBLE);

                    replaceFragment(new OrganizerProfileFragment(), bundle);
                    break;
                }
                case R.id.judge:
                    // binding.viewPager.setVisibility(View.INVISIBLE);

                   replaceFragment(new JudgeProfileFragment(),bundle);
                    break;
                case R.id.viewer:
                    //setupViewPagerAdapterTests(binding.viewPager);
                    //  binding.viewPager.setVisibility(View.INVISIBLE);
                    replaceFragment(new ViewerProfileFragment(),bundle);
                    break;
                case R.id.worker:
                    // binding.viewPager.setVisibility(View.INVISIBLE);
                    replaceFragment(new WorkerProfileFragment(),bundle);
                    break;
            }



            return true;
        });


    }

    private void replaceFragment(Fragment fragment, Bundle bundle){
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void loadUserInfo() {

        Call<ProfileResponse> call = ApiClient.profileServiceGet().getProfile(user_id);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    binding.emailTv.setText(response.body().getUserResponse().getEmail()+ " "+ response.body().getUserResponse().getUsername());
                    binding.nameTv.setText(response.body().getFirst_name()+" "+response.body().getSecond_name()+",");
                    binding.Age.setText("Возраст : "+String.valueOf(response.body().getAge()));
                    binding.phoneNumberProfile.setText(response.body().getPhone_number());
                    binding.genderProfile.setText(response.body().getSex());

                    Glide.with(ProfileActivity.this)
                            .load(R.drawable.olymicpng)
                            .placeholder(R.drawable.olymicpng)
                            .into(binding.profileTv);
                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Проблемы с соединением!", Toast.LENGTH_SHORT).show();

            }
        });


                    }


}