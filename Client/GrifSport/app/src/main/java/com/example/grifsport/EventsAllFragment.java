package com.example.grifsport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import com.example.grifsport.databinding.FragmentAllEventsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EventsAllFragment extends Fragment implements DialogFragmentFilters.OnInputSelected {
    Integer count ;
    // поля каждого фрагмента квартир
    String filterSport="";
    String filterCity="";
    String FilterGender="";
    String FilterAge="";
    Bundle bundle;
    private  int profile_id;
    String filteeeer="";
    private Integer assessment;
    private String description="";
    private String date="";
    String id="";
    private String access="";
    private String accessToken="";
    private java.util.ArrayList<EventResponse> ArrayList;
    private FragmentAllEventsBinding binding;
    private AdapterEvent adapterNote; //одна квартира

    public EventsAllFragment() {
        // Required empty public constructor
    }


    public static EventsAllFragment newInstance(Integer assessment,String description,String date,String access,Integer profile_id) {
        EventsAllFragment fragment = new EventsAllFragment();
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
        binding = FragmentAllEventsBinding.inflate(LayoutInflater.from(getContext()),container,false);

        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();

                bundle.putString("access", access);
                bundle.putInt("profile_id", profile_id);
                DialogFragmentFilters dialogFragmentFilters=new DialogFragmentFilters();
                dialogFragmentFilters.setArguments(bundle);
                dialogFragmentFilters.setTargetFragment(EventsAllFragment.this,1);
                dialogFragmentFilters.show(getParentFragmentManager(),"custom");
            }
        });

        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    //для всех доступных квартир применяем фу-ию filter (строка,введенная польз)
                    adapterNote.getFilter().filter(charSequence);
                    filteeeer=charSequence.toString(); //
                }
                catch (Exception e){
                    Log.d("Error","onTetChanged: search"+e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        access = getArguments().getString("access","");
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

        count = 0;
        Call<List<EventResponse>> eventResponse = ApiClient.eventServiceGet().getEvent();
        eventResponse.enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                if (response.isSuccessful()) {
                    //для каждой квартиры из бд создаем model1 и добавляем ее в лист
                    Log.d("Otladka",response.body().toString());
                    for(int i=0;i<response.body().size();i++) {
                        binding.noNotes.setVisibility(View.GONE);
                        EventResponse model1 = new EventResponse();
                        model1.setAdress(response.body().get(i).getAdress());
                        model1.setId(response.body().get(i).getId());
                        model1.setDescription(response.body().get(i).getDescription());
                        model1.setAges(response.body().get(i).getAges());
                        model1.setDate_created(response.body().get(i).getDate_created());
                        model1.setDeadline_for_accepting_applications(response.body().get(i).getDeadline_for_accepting_applications());
                        model1.setEvent_end_date(response.body().get(i).getEvent_end_date());
                        model1.setEvent_start_date(response.body().get(i).getEvent_start_date());
                        model1.setSchedule(response.body().get(i).getSchedule());
                        model1.setGenders(response.body().get(i).getGenders());
                        model1.setIs_active(response.body().get(i).getIs_active());
                        model1.setImage(response.body().get(i).getImage());
                        model1.setType(response.body().get(i).getType());
                        model1.setPhone_number(response.body().get(i).getPhone_number());
                        model1.setResults(response.body().get(i).getResults());
                        model1.setName(response.body().get(i).getName());
                        model1.setCity(response.body().get(i).getCity());
                        model1.setSport(response.body().get(i).getSport());

                        if(model1.getIs_active()==true) {

                            if(FilterGender.equals("") || FilterGender.equals("Мужчины и женщины")) {
                                if (filterCity.equals("") || filterCity.equals("Без фильтра")) {
                                    if (filterSport.equals("") || filterSport.equals("Без фильтра")) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    } else if (model1.getSport().getName().equals(filterSport)) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    }
                                } else if (model1.getCity().getName().equals(filterCity)) {
                                    if (filterSport.equals("") || filterSport.equals("Без фильтра")) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    } else if (model1.getSport().getName().equals(filterSport)) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    }
                                }
                            }else if(model1.getGenders().equals(FilterGender)){
                                if (filterCity.equals("") || filterCity.equals("Без фильтра")) {
                                    if (filterSport.equals("") || filterSport.equals("Без фильтра")) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    } else if (model1.getSport().getName().equals(filterSport)) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    }
                                } else if (model1.getCity().getName().equals(filterCity)) {
                                    if (filterSport.equals("") || filterSport.equals("Без фильтра")) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    } else if (model1.getSport().getName().equals(filterSport)) {
                                        count += 1;
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    }
                                }
                            }
                        }
                    }
                    if (count==0) {
                        adapterNote = new AdapterEvent(getContext(), ArrayList, accessToken, profile_id); //лист с экз-ами доступных кв.

                        binding.allNotes.setAdapter(adapterNote);
                        binding.noNotes.setVisibility(View.VISIBLE);
                    }
                }
            }

            //запрос неуспешный
            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), "Проблемы с соединением!", Toast.LENGTH_SHORT).show();

            }
        });



    }



    @Override
    public void sendInput(String sport,String age,String gender,String city) {
         filterSport = sport;
         filterCity=city;
         FilterGender=gender;
         FilterAge=age;
         loadAllCondition(id);

    }
}
