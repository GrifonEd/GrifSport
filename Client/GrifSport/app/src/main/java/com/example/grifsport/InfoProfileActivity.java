package com.example.grifsport;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.grifsport.databinding.ActivityInfoEventBinding;
import com.example.grifsport.databinding.ActivityInfoProfileBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoProfileActivity extends AppCompatActivity {
    String access="";
    int event_id;
    String id;
    ActivityInfoProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoProfileBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        String access = intent.getStringExtra("access");

        event_id = intent.getIntExtra("event_id",-1);
        setContentView(binding.getRoot());

        Call<EventResponse> call = ApiClient.oneEventServiceGet().getEvent(event_id);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if(response.isSuccessful()){
                   binding.eventName.setText(response.body().getName());
                    binding.eventDescription.setText(response.body().getDescription());
                    binding.eventGroups.setText(response.body().getAges()+", "+response.body().getGenders());



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
                                String disciplineRes="Дисциплины:\n";
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