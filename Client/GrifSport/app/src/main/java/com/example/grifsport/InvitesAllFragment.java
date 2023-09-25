package com.example.grifsport;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.grifsport.databinding.FragmentAllEventsBinding;
import com.example.grifsport.databinding.FragmentAllInviteBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvitesAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class InvitesAllFragment extends Fragment {

    // поля каждого фрагмента квартир
    private  int profile_id;
    String filteeeer="";
    private Integer assessment;
    private String description="";
    private String date="";
    String id="";
    private String accessToken="";
    private java.util.ArrayList<InviteResponse> ArrayList;
    private FragmentAllInviteBinding binding;
    private AdapterInvite adapterNote; //одна квартира

    public InvitesAllFragment() {
        // Required empty public constructor
    }


    public static InvitesAllFragment newInstance(Integer assessment, String description, String date, String access, Integer profile_id) {
        InvitesAllFragment fragment = new InvitesAllFragment();
        Bundle args = new Bundle();
        args.putString("access",access);
        args.putInt("profile_id",profile_id);
        args.putString("description",description);
        args.putInt("assessment",assessment);
        args.putString("date",date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            description=getArguments().getString("description");
            assessment = getArguments().getInt("assessment");
            date = getArguments().getString("date");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllInviteBinding.inflate(LayoutInflater.from(getContext()),container,false);




        String access = getArguments().getString("access","");
        profile_id = getArguments().getInt("profile_id",-1);
        Log.e("FRAGMENT",access);
        checkAccess(access);
        accessToken=access;
        //Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
           // @Override
            //public void run() {
         //получение доступных квартир каждые 10 сек
            //}
       // }, 0, 10, TimeUnit.SECONDS);

        //функция поиска по номеру квартиры


        return binding.getRoot();
    }
    private void checkAccess(String access) {

        Call<AuthorizationResponse> call = ApiClient.AuthorizationUserService().getloginUser(access);
        Log.d("Norm123234235",call.request().header("Authorization").toString());
        call.enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                Log.d("Norm",response.headers().toString());
                Log.d("Norm",response.toString());
                if(response.isSuccessful()){
                    id=response.body().getId().toString();
                    if(!id.equals("Bad")&&!id.equals("") )
                        loadAllCondition(id);
                }
                else {
                  id="Bad";
                }

            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                Log.d("Norm","hm");
                id="Bad";
            }
        });




    }
    //запрос всех доступных квартир
    private void loadAllCondition(String id) {



        ArrayList = new ArrayList<>();

        Call<List<InviteResponse>> callInvite =ApiClient.inviteForProfileServiceGet().getInvite(profile_id);
        callInvite.enqueue(new Callback<List<InviteResponse>>() {
            @Override
            public void onResponse(Call<List<InviteResponse>> call, Response<List<InviteResponse>> response) {
                if (response.isSuccessful()) {
                    //для каждой квартиры из бд создаем model1 и добавляем ее в лист
                    Log.d("Otladka", response.body().toString());
                    for (int i = 0; i < response.body().size(); i++) {
                        binding.noNotes.setVisibility(View.GONE);
                        InviteResponse model1 = new InviteResponse();
                        model1.setEvent(response.body().get(i).getEvent());
                        model1.setId(response.body().get(i).getId());
                        model1.setProfile(response.body().get(i).getProfile());
                        model1.setVacancy(response.body().get(i).getVacancy());


                            ArrayList.add(model1); //формирование листа

                            adapterNote = new AdapterInvite(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                            binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов

                    }
                    if (response.body().size() == 0)
                        binding.noNotes.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<InviteResponse>> call, Throwable t) {

            }
        });





    }

}
