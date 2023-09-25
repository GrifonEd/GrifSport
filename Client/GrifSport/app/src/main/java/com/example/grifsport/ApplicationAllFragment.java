package com.example.grifsport;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.grifsport.databinding.FragmentAllApplicationBinding;
import com.example.grifsport.databinding.FragmentAllEventsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplicationAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ApplicationAllFragment extends Fragment {

    // поля каждого фрагмента квартир
    private  int profile_id;
    String filteeeer="";
    private Integer assessment;
    private String description="";
    private String date="";
    private  int event_id;
    String id="";
    private String accessToken="";
    private java.util.ArrayList<ApplicationResponse> ArrayList;
    private FragmentAllApplicationBinding binding;
    private AdapterApplication adapterNote; //одна квартира

    public ApplicationAllFragment() {
        // Required empty public constructor
    }


    public static ApplicationAllFragment newInstance(Integer assessment, String description, String date, String access, Integer profile_id,int event_id) {
        ApplicationAllFragment fragment = new ApplicationAllFragment();
        Bundle args = new Bundle();
        args.putString("access",access);
        args.putInt("profile_id",profile_id);
        args.putInt("event_id",event_id);
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
        binding = FragmentAllApplicationBinding.inflate(LayoutInflater.from(getContext()),container,false);



        event_id = getArguments().getInt("event_id",0);
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

        Call<List<ApplicationResponse>> eventResponse = ApiClient.applicationsForEventServiceGet().getApplications(event_id);
        eventResponse.enqueue(new Callback<List<ApplicationResponse>>() {
            @Override
            public void onResponse(Call<List<ApplicationResponse>> call, Response<List<ApplicationResponse>> response) {
                if (response.isSuccessful()) {
                    //для каждой квартиры из бд создаем model1 и добавляем ее в лист
                    Log.d("Otladka",response.body().toString());
                    for(int i=0;i<response.body().size();i++) {
                        binding.noNotes.setVisibility(View.GONE);
                        ApplicationResponse model1 = new ApplicationResponse();
                        model1.setId(response.body().get(i).getId());
                        model1.setDescription(response.body().get(i).getDescription());
                        model1.setEvent(response.body().get(i).getEvent());
                        model1.setProfile(response.body().get(i).getProfile());
                        model1.setVacancy(response.body().get(i).getVacancy());

                            ArrayList.add(model1); //формирование листа

                            adapterNote = new AdapterApplication(getContext(), ArrayList, accessToken,profile_id); //лист с экз-ами доступных кв.

                            binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов

                    }
                    if (response.body().size()==0)
                        binding.noNotes.setVisibility(View.VISIBLE);

                }
            }

            //запрос неуспешный
            @Override
            public void onFailure(Call<List<ApplicationResponse>> call, Throwable t) {

            }
        });



    }

}
