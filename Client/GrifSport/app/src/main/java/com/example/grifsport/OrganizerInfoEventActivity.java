package com.example.grifsport;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.grifsport.databinding.ActivityInfoEventBinding;
import com.example.grifsport.databinding.ActivityOrganizerInfoEventBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganizerInfoEventActivity extends AppCompatActivity  {
    private EventResponse event;
    boolean visible=true;
    Bundle bundle;
    int event_id;
    int profile_id;
    String access="";
    String id;
    ActivityOrganizerInfoEventBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrganizerInfoEventBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        access = intent.getStringExtra("access");

        event_id = intent.getIntExtra("event_id",-1);
        setContentView(binding.getRoot());

        binding.eventName.setEnabled(false);
        binding.eventName.setCursorVisible(false);

        binding.eventDescription.setEnabled(false);
        binding.eventDescription.setCursorVisible(false);

        binding.eventGroups.setEnabled(false);
        binding.eventGroups.setCursorVisible(false);

        binding.EventAdress.setEnabled(false);
        binding.EventAdress.setCursorVisible(false);

        binding.EventDesciplines.setEnabled(false);
        binding.EventDesciplines.setCursorVisible(false);

        binding.EventUrlImage.setEnabled(false);
        binding.EventUrlImage.setCursorVisible(false);

        binding.EventUrlImage.setVisibility(View.GONE);
        binding.buttonSave.setVisibility(View.GONE);

        binding.svernMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visible == true) {
                    visible=false;
                    binding.toolBarRl.setVisibility(View.GONE);
                    binding.svernMenu.setImageResource(R.drawable.ic_down);
                }
                else{
                    visible=true;
                    binding.toolBarRl.setVisibility(View.VISIBLE);
                    binding.svernMenu.setImageResource(R.drawable.ic_scrit);
                }

            }
        });

        binding.udpateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.eventName.setEnabled(true);
                binding.eventName.setCursorVisible(true);

                binding.eventDescription.setEnabled(true);
                binding.eventDescription.setCursorVisible(true);

                binding.eventGroups.setEnabled(true);
                binding.eventGroups.setCursorVisible(true);

                binding.EventAdress.setEnabled(true);
                binding.EventAdress.setCursorVisible(true);

                binding.EventDesciplines.setEnabled(true);
                binding.EventDesciplines.setCursorVisible(true);

                binding.EventUrlImage.setEnabled(true);
                binding.EventUrlImage.setCursorVisible(true);

                binding.EventUrlImage.setVisibility(View.VISIBLE);
                binding.buttonSave.setVisibility(View.VISIBLE);

            }
        });


        binding.openApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();

                bundle.putString("access", access);
                bundle.putInt("event_id", event_id);
                bundle.putInt("profile_id", profile_id);
                DialogFragmentOrganizerApplyApplication dialogFragmentMy=new DialogFragmentOrganizerApplyApplication();
                dialogFragmentMy.setArguments(bundle);
                dialogFragmentMy.show(getSupportFragmentManager(),"custom");
            }
        });

        binding.openInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("access", access);
                bundle.putInt("event_id", event_id);
                bundle.putInt("profile_id", profile_id);
                DialogFragmentOrganizerInviteProfile dialogFragmentMy=new DialogFragmentOrganizerInviteProfile();
                dialogFragmentMy.setArguments(bundle);
                dialogFragmentMy.show(getSupportFragmentManager(),"custom");
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.setResults("");
                if(!binding.eventName.getText().toString().equals(""))
                    event.setName(binding.eventName.getText().toString());
                if(!binding.EventUrlImage.getText().toString().equals(""))
                    event.setImage(binding.EventUrlImage.getText().toString());
                if(!binding.eventGroups.getText().toString().equals(""))
                    event.setGenders(binding.eventGroups.getText().toString());
                if(!binding.eventGender.getText().toString().equals(""))
                    event.setGenders(binding.eventGender.getText().toString());
                if(!binding.eventDescription.getText().toString().equals(""))
                    event.setDescription(binding.eventDescription.getText().toString());
                Call<EventResponse> callPut = ApiClient.eventServicePut().eventPut(event);
                callPut.enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(OrganizerInfoEventActivity.this,"Данные успешно обновлены",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EventResponse> call, Throwable t) {

                    }
                });
            }
        });
        Call<EventResponse> call = ApiClient.oneEventServiceGet().getEvent(event_id);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if(response.isSuccessful()){
                    event=response.body();
                   binding.eventName.setText(response.body().getName());
                    binding.eventDescription.setText(response.body().getDescription());
                    binding.eventGroups.setText(response.body().getAges());
                    binding.eventGender.setText(response.body().getGenders());
                    String disciplineRes="Вид спорта:\n";
                    disciplineRes = disciplineRes + response.body().getSport().getName();
                    binding.EventDesciplines.setText(disciplineRes);

                    binding.EventDates.setText(getMyDate(response.body().getEvent_start_date())+"-"+getMyDateYear(response.body().getEvent_end_date()));
                    binding.EventAdress.setText(response.body().getAdress());
                    Glide
                            .with(binding.iconEvent)
                            .load(response.body().getImage())
                            .error(R.drawable.olymicpng)
                            .fitCenter()
                            .override(1000,1000)
                            .into(binding.iconEvent);

                    ArrayList<String> disciplines=new ArrayList<>();
                    /*
                    Call<List<DisciplineForEventResponse>> disciplineForEventResponseCall = ApiClient.disciplineForEventServiceGet().getDisciplineForEvent(event_id);
                    disciplineForEventResponseCall.enqueue(new Callback<List<DisciplineForEventResponse>>() {
                        @Override
                        public void onResponse(Call<List<DisciplineForEventResponse>> call, Response<List<DisciplineForEventResponse>> response) {
                            if(response.isSuccessful()){
                                for(int i=0;i<response.body().size();i++) {
                                    DisciplineForEventResponse model1 = new DisciplineForEventResponse();
                                    model1.setDiscipline(response.body().get(i).getDiscipline());
                                    String disciplineName=model1.getDiscipline().getName();
                                    disciplines.add(disciplineName);
                                }
                                String disciplineRes="Вид спорта:\n";
                                for(int h=0;h<disciplines.size();h++) {
                                    if (h == disciplines.size() - 1)
                                        disciplineRes = disciplineRes + disciplines.get(h);
                                    else
                                        disciplineRes = disciplineRes + disciplines.get(h)+"\n";
                                }
                                if(disciplineRes.length()!=0)
                                    binding.EventDesciplines.setText(disciplineRes);
                            }
                            else {

                            }

                        }

                        @Override
                        public void onFailure(Call<List<DisciplineForEventResponse>> disciplineForEventResponseCall, Throwable t) {

                        }
                    });


                     */


                }
                else {

                }

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });




        try(InputStream inputStream = getApplicationContext().getAssets().open(String.valueOf(R.drawable.olymicpng))){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            binding.iconEvent.setImageDrawable(drawable);
        }
        catch (IOException e){
            e.printStackTrace();
        }




        binding.backloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public String getMyDate(String date) {
        String[]words = date.split("-");
        String year=words[0];
        String month=words[1];
        String[]words1=words[2].split("T");
        String day=words1[0];
        String[] words2=words1[1].split(":");
        String hour=words2[0];
        String mins   =words2[1];

        Character first=day.charAt(0);
        if (first=='0') {
            day=day.substring(1);
        }

        switch (month){
            case("01"):
                month="Января";
                break;
            case("02"):
                month="Февраля";
                break;
            case("03"):
                month="Марта";
                break;
            case("04"):
                month="Апреля";
                break;
            case("05"):
                month="Мая";
                break;
            case("06"):
                month="Июня";
                break;
            case("07"):
                month="Июля";
                break;
            case("08"):
                month="Августа";
                break;
            case("09"):
                month="Сентября";
                break;
            case("10"):
                month="Октября";
                break;
            case("11"):
                month="Ноября";
                break;
            case("12"):
                month="Декабря";
                break;

        }

        String MyDate= day+" "+month;
        return MyDate;
    }


    public String getMyDateYear(String date) {
        String[]words = date.split("-");
        String year=words[0];
        String month=words[1];
        String[]words1=words[2].split("T");
        String day=words1[0];
        String[] words2=words1[1].split(":");
        String hour=words2[0];
        String mins   =words2[1];

        Character first=day.charAt(0);
        if (first=='0') {
            day=day.substring(1);
        }

        switch (month){
            case("01"):
                month="Января";
                break;
            case("02"):
                month="Февраля";
                break;
            case("03"):
                month="Марта";
                break;
            case("04"):
                month="Апреля";
                break;
            case("05"):
                month="Мая";
                break;
            case("06"):
                month="Июня";
                break;
            case("07"):
                month="Июля";
                break;
            case("08"):
                month="Августа";
                break;
            case("09"):
                month="Сентября";
                break;
            case("10"):
                month="Октября";
                break;
            case("11"):
                month="Ноября";
                break;
            case("12"):
                month="Декабря";
                break;

        }

        String MyDate= day+" "+month+"\n"+year;
        return MyDate;
    }


}