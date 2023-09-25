package com.example.grifsport;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.grifsport.databinding.JudgeFragmentAllEventsBinding;
import com.example.grifsport.databinding.OrganizerFragmentAllEventsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsAllFragmentJudge#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EventsAllFragmentJudge extends Fragment {
    int count;
    // поля каждого фрагмента квартир
    String filteeeer="";
    private Integer assessment;
    private String description="";
    private String date="";
    String id="";
    private String accessToken="";
    private java.util.ArrayList<EventResponse> ArrayList;
    private JudgeFragmentAllEventsBinding binding;
    private AdapterEventJudge adapterNote; //одна квартира

    public EventsAllFragmentJudge() {
        // Required empty public constructor
    }
    public EventsAllFragmentJudge(String access) {
        accessToken=access;
        // Required empty public constructor
    }


    @NotNull
    public static EventsAllFragmentJudge newInstance(Integer assessment, String description, String date, String access) {
        EventsAllFragmentJudge fragment = new EventsAllFragmentJudge();
        Bundle args = new Bundle();
        args.putString("access",access);
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
        binding = JudgeFragmentAllEventsBinding.inflate(LayoutInflater.from(getContext()),container,false);




        String access = getArguments().getString("access","");
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
        count=0;
        Call<ProfileResponse> call = ApiClient.profileServiceGet().getProfile(Integer.valueOf(id));
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    Call<List<JudgeForEventResponse>>  judge = ApiClient.judgeForAllEventServiceGet().JudgeForEvent(response.body().getId());
                    judge.enqueue(new Callback<List<JudgeForEventResponse>>() {
                        @Override
                        public void onResponse(Call<List<JudgeForEventResponse>> call, Response<List<JudgeForEventResponse>> response) {
                            if (response.isSuccessful()) {
                                //для каждой квартиры из бд создаем model1 и добавляем ее в лист
                                Log.d("Otladka",response.body().toString());
                                for(int i=0;i<response.body().size();i++) {
                                    binding.noNotes.setVisibility(View.GONE);
                                    EventResponse model1 = new EventResponse();
                                    model1.setAdress(response.body().get(i).getEvent().getAdress());
                                    model1.setId(response.body().get(i).getEvent().getId());
                                    model1.setDescription(response.body().get(i).getEvent().getDescription());
                                    model1.setAges(response.body().get(i).getEvent().getAges());
                                    model1.setDate_created(response.body().get(i).getEvent().getDate_created());
                                    model1.setDeadline_for_accepting_applications(response.body().get(i).getEvent().getDeadline_for_accepting_applications());
                                    model1.setEvent_end_date(response.body().get(i).getEvent().getEvent_end_date());
                                    model1.setEvent_start_date(response.body().get(i).getEvent().getEvent_start_date());
                                    model1.setSchedule(response.body().get(i).getEvent().getSchedule());
                                    model1.setGenders(response.body().get(i).getEvent().getGenders());
                                    model1.setIs_active(response.body().get(i).getEvent().getIs_active());
                                    model1.setImage(response.body().get(i).getEvent().getImage());
                                    model1.setType(response.body().get(i).getEvent().getType());
                                    model1.setPhone_number(response.body().get(i).getEvent().getPhone_number());
                                    model1.setResults(response.body().get(i).getEvent().getResults());
                                    model1.setName(response.body().get(i).getEvent().getName());

                                    if(model1.getIs_active()==false) {
                                        ArrayList.add(model1); //формирование листа

                                        adapterNote = new AdapterEventJudge(getContext(), ArrayList, accessToken); //лист с экз-ами доступных кв.

                                        count++;

                                        binding.allNotes.setAdapter(adapterNote); //вставка в форму фрагментов
                                    }

                                }
                                if(response.body().size()==0)
                                    binding.noNotes.setVisibility(View.VISIBLE);
                                binding.Organizer.setText(String.valueOf(count));

                            }
                        }

                        @Override
                        public void onFailure(Call<List<JudgeForEventResponse>> call, Throwable t) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });



    }

}
