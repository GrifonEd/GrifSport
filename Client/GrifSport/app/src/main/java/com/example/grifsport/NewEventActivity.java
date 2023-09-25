package com.example.grifsport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grifsport.databinding.ActivityNewEventBinding;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEventActivity extends AppCompatActivity {
    String access="";
    private String id="";
    private int user_id;
    private  int event_id;
    private SportResponse sport;
    private CityResponse city;
    private ActivityNewEventBinding binding;
    private TimePickerDialog timePickerDialogEnd;
    private Button timeButtonStart;
    private DatePickerDialog datePickerDialogStart;
    private DatePickerDialog datePickerDialogEnd;
    private Button dateButtonStart;
    private Button dateButtonEnd;
    private String dateStart;
    private String dateEnd;
    private String dateApplication;
    private String timeApplication;
    private TypeEventResponse typeEventResponse;
    private DatePickerDialog datePickerDialogDeadline;
    private String gender;
    private Button dateButtonDeadline;
    private Integer yearStart;
    private Integer monthStart;
    private Integer dayStart;
    private Integer yearEnd;
    private Integer monthEnd;
    private Integer dayEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        access=intent.getStringExtra("access");
        user_id = intent.getIntExtra("user_id",0);
        initDatePickerStart();
        initDatePickerEnd();
        initDatePickerDeadline();
        initTimePickerDeadline();

        dateButtonStart = findViewById(R.id.datePickerButtonStart);
        dateButtonStart.setText(getTodaysDate());
        dateButtonEnd=findViewById(R.id.datePickerButtonEnd);
        dateButtonEnd.setText(getTodaysDate());
        dateButtonDeadline=findViewById(R.id.datePickerButtonDeadline);
        dateButtonDeadline.setText(getTodaysDate());

        timeButtonStart=findViewById(R.id.timePickerButtonDeadline);
        timeButtonStart.setText(getTodaysTime());

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
                String str = "ОГЭ_2021_Вариант_21-22й2.pdf";
                Call<FileResponse> call1 = ApiClient.fileServiceGet().fileGet(str);
                call1.enqueue(new Callback<FileResponse>() {
                    @Override
                    public void onResponse(Call<FileResponse> call1, Response<FileResponse> response) {
                        if(response.isSuccessful())
                            Toast.makeText(NewEventActivity.this,"Что-то удачное",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<FileResponse> call1, Throwable t) {
                        Toast.makeText(NewEventActivity.this,"ХЗ",Toast.LENGTH_SHORT).show();

                    }
                });


 */

                if(binding.nameEt.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите название мероприятия!",Toast.LENGTH_SHORT).show();
                else if(binding.descriptionText.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите описание мероприятия!",Toast.LENGTH_SHORT).show();
                else if(binding.AdressText.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите адрес мероприятия!",Toast.LENGTH_SHORT).show();
                else if(binding.imagePath.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите ссылку на картинку для мероприятия!",Toast.LENGTH_SHORT).show();
                else if(binding.scheduleText.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите расписание мероприятия!",Toast.LENGTH_SHORT).show();
                else if(binding.ageText.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите возраст участник мероприятия!",Toast.LENGTH_SHORT).show();
                else if(binding.phoneNumber.getText().toString().equals(""))
                    Toast.makeText(NewEventActivity.this,"Введите номер телефона мероприятия!",Toast.LENGTH_SHORT).show();
                else {

                    String image = "Без ссылки";
                    if(!binding.imagePath.getText().toString().equals(""))
                        image = binding.imagePath.getText().toString();
                    EventResponse eventResponse = new EventResponse(0, binding.nameEt.getText().toString(), binding.descriptionText.getText().toString(), binding.AdressText.getText().toString(), "2023-05-06T10:37:49.047759", dateStart, dateEnd, image, binding.scheduleText.getText().toString(), "", dateApplication + timeApplication, binding.ageText.getText().toString(), gender, binding.phoneNumber.getText().toString(), true, typeEventResponse,city,sport);
                    Call<EventResponse> call = ApiClient.eventServicePost().eventPost(eventResponse);
                    call.enqueue(new Callback<EventResponse>() {
                        @Override
                        public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(NewEventActivity.this, "Мероприятие успешно создано", Toast.LENGTH_SHORT).show();
                                binding.saveBtn.setVisibility(View.INVISIBLE);
                                event_id=response.body().getId();
                                Call<ProfileResponse> callProfile = ApiClient.profileServiceGet().getProfile(user_id);
                                callProfile.enqueue(new Callback<ProfileResponse>() {
                                    @Override
                                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                                        if (response.isSuccessful()) {

                                            Call<OrganizerResponse> callOrganizer = ApiClient.organizerServiceGet().OrganizerGet(response.body().getId());
                                            callOrganizer.enqueue(new Callback<OrganizerResponse>() {
                                                @Override
                                                public void onResponse(Call<OrganizerResponse> call, Response<OrganizerResponse> response) {
                                                    if (response.isSuccessful()) {

                                                        Call<OrganizerForEventResponse> callOrganizer = ApiClient.organizerForEventServicePostServiceGet().OrganizerForEvent(event_id,response.body().getId());
                                                        callOrganizer.enqueue(new Callback<OrganizerForEventResponse>() {
                                                            @Override
                                                            public void onResponse(Call<OrganizerForEventResponse> call, Response<OrganizerForEventResponse> response) {
                                                                if (response.isSuccessful())
                                                                Toast.makeText(NewEventActivity.this, "Вы стали организатором данного мероприятия", Toast.LENGTH_SHORT).show();

                                                            }

                                                            @Override
                                                            public void onFailure(Call<OrganizerForEventResponse> call, Throwable t) {

                                                            }
                                                        });
                                                    }
                                                }
                                                @Override
                                                public void onFailure(Call<OrganizerResponse> call, Throwable t) {

                                                }
                                            });
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ProfileResponse> call, Throwable t) {

                                    }
                                });


                                // OrganizerResponse organizerResponse = new OrganizerResponse(0,)
                            }
                        }

                        @Override
                        public void onFailure(Call<EventResponse> call, Throwable t) {
                            Toast.makeText(NewEventActivity.this, "Проблемы с соединением! Мероприятие не создано!", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        typeEventResponse = new TypeEventResponse(0,"Фестиваль");
        Spinner spinnerTypeEvent = binding.typeEvent;
        String[] typesEvent = {"Фестиваль","Тренировка","Официальные соревнования"};
        ArrayAdapter<String> adapterTypesEvent  = new ArrayAdapter(NewEventActivity.this,android.R.layout.simple_spinner_item,typesEvent);
        adapterTypesEvent.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTypeEvent.setAdapter(adapterTypesEvent);
        spinnerTypeEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapterView.getChildAt(0)).setTextSize(15);
                typeEventResponse = new TypeEventResponse(0,((TextView) adapterView.getChildAt(0)).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        gender="Мужчины";
        Spinner spinnerGenderEvent = binding.genderEvent;
        String[] gendersEvent = {"Мужчины","Женщины","Мужчины и женщины"};
        ArrayAdapter<String> adaptergendersEvent  = new ArrayAdapter(NewEventActivity.this,android.R.layout.simple_spinner_item,gendersEvent);
        adaptergendersEvent.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGenderEvent.setAdapter(adaptergendersEvent);
        spinnerGenderEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapterView.getChildAt(0)).setTextSize(15);
                gender=((TextView) adapterView.getChildAt(0)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        city = new CityResponse(0,"Уфа");
        Spinner spinnerCity = binding.cityEvent;
        String[] citiesEvent = {"Уфа","Москва","Санкт-Петербург","Екатеринбург","Тюмень","Сыктывкар"};
        ArrayAdapter<String> adapterCitiesEvent  = new ArrayAdapter(NewEventActivity.this,android.R.layout.simple_spinner_item,citiesEvent);
        adapterCitiesEvent.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCity.setAdapter(adapterCitiesEvent);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapterView.getChildAt(0)).setTextSize(15);
                city = new CityResponse(0,((TextView) adapterView.getChildAt(0)).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        sport = new SportResponse(0,"Уфа",new TypeSportResponse(1,true,false));
        Spinner spinnerSport = binding.sportEvent;
        String[] sportsEvent = {"Скалолазание","Бокс","Шахматы","Ниндзя-спорт","Керлинг"};
        ArrayAdapter<String> adapterSportEvent  = new ArrayAdapter(NewEventActivity.this,android.R.layout.simple_spinner_item,sportsEvent);
        adapterSportEvent.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSport.setAdapter(adapterSportEvent);
        spinnerSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapterView.getChildAt(0)).setTextSize(15);
                sport = new SportResponse(0,((TextView) adapterView.getChildAt(0)).getText().toString(),new TypeSportResponse(1,true,false));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }






    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String getTodaysTime()
    {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minut = cal.get(Calendar.MINUTE);
        return makeTimeString(hour,minut);
    }

    private void initDatePickerStart()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                if(year*365+month*30+day<=yearEnd*365+monthEnd*30+dayEnd) {

                    dateButtonStart.setText(date);
                    yearStart=year;
                    monthStart=month;
                    dayStart=day;
                    if (month < 10) {
                        if (day < 10) {
                            dateStart = year + "-0" + month + "-0" + day + "T12:00:00.000000";
                        } else {
                            dateStart = year + "-0" + month + "-" + day + "T12:00:00.000000";
                        }
                    } else {
                        if (day < 10) {
                            dateStart = year + "-" + month + "-0" + day + "T12:00:00.000000";
                        } else {
                            dateStart = year + "-" + month + "-" + day + "T12:00:00.000000";
                        }
                    }
                }
                else{
                    Toast.makeText(NewEventActivity.this,"Дата начала должна быть до даты окончания мероприятия",Toast.LENGTH_SHORT).show();
                }
            }
        };



        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        yearStart=year;
        monthStart=month+1;
        dayStart=day;
        if (monthStart < 10) {
            if (day < 10) {
                dateStart = year + "-0" + monthStart + "-0" + day + "T12:00:00.000000";
            } else {
                dateStart = year + "-0" + monthStart + "-" + day + "T12:00:00.000000";
            }
        } else {
            if (day < 10) {
                dateStart = year + "-" + monthStart + "-0" + day + "T12:00:00.000000";
            } else {
                dateStart = year + "-" + monthStart + "-" + day + "T12:00:00.000000";
            }
        }
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialogStart = new DatePickerDialog(this, style, dateSetListener, year, month, day);
       // datePickerDialogStart.getDatePicker().setMinDate().setMaxDate(System.currentTimeMillis());

    }


    private void initDatePickerEnd()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                if(year*365+month*30+day>=yearStart*365+monthStart*30+dayStart) {
                    dateButtonEnd.setText(date);
                    yearEnd=year;
                    monthEnd=month;
                    dayEnd=day;
                    if (month < 10) {
                        if (day < 10) {
                            dateEnd = year + "-0" + month + "-0" + day + "T12:00:00.000000";
                        } else {
                            dateEnd = year + "-0" + month + "-" + day + "T12:00:00.000000";
                        }
                    } else {
                        if (day < 10) {
                            dateEnd = year + "-" + month + "-0" + day + "T12:00:00.000000";
                        } else {
                            dateEnd = year + "-" + month + "-" + day + "T12:00:00.000000";
                        }
                    }
                }
                else{
                    Toast.makeText(NewEventActivity.this,"Дата окончания мероприятия должна быть после даты начала мероприятия",Toast.LENGTH_SHORT).show();
                }
            }
        };



        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        yearEnd=year;
        monthEnd=month+1;
        dayEnd=day;

        if (monthEnd < 10) {
            if (day < 10) {
                dateEnd = year + "-0" + monthEnd + "-0" + day + "T12:00:00.000000";
            } else {
                dateEnd = year + "-0" + monthEnd + "-" + day + "T12:00:00.000000";
            }
        } else {
            if (day < 10) {
                dateEnd = year + "-" + monthEnd + "-0" + day + "T12:00:00.000000";
            } else {
                dateEnd = year + "-" + monthEnd + "-" + day + "T12:00:00.000000";
            }
        }

        int style = AlertDialog.THEME_HOLO_LIGHT;


        datePickerDialogEnd = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private void initDatePickerDeadline()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);

                        dateButtonDeadline.setText(date);

                        if (month < 10) {
                            if (day < 10) {
                                dateApplication = year + "-0" + month + "-0" + day + "T";
                            } else {
                                dateApplication = year + "-0" + month + "-" + day + "T";
                            }
                        } else {
                            if (day < 10) {
                                dateApplication = year + "-" + month + "-0" + day + "T";
                            } else {
                                dateApplication = year + "-" + month + "-" + day + "T";
                            }
                        }

            }
        };



        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        month=month+1;
        if (month < 10) {
            if (day < 10) {
                dateApplication = year + "-0" + month + "-0" + day + "T";
            } else {
                dateApplication = year + "-0" + month + "-" + day + "T";
            }
        } else {
            if (day < 10) {
                dateApplication = year + "-" + month + "-0" + day + "T";
            } else {
                dateApplication = year + "-" + month + "-" + day + "T";
            }
        }

        datePickerDialogDeadline = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private void initTimePickerDeadline()

    {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String time = makeTimeString(hour,min);


                        timeButtonStart.setText(time);
                        if (hour < 10) {
                            if (min < 10) {
                               timeApplication = "0"+hour+":0"+min+":00.000000";
                            } else {
                                timeApplication = "0"+hour+":"+min+":00.000000";
                            }
                        } else {
                            if (min < 10) {
                                timeApplication = hour+":0"+min+":00.000000";
                            } else {
                                timeApplication = +hour+":"+min+":00.000000";
                            }
                        }
            }

        };

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        if (hour < 10) {
            if (min < 10) {
                timeApplication = "0"+hour+":0"+min+":00.000000";
            } else {
                timeApplication = "0"+hour+":"+min+":00.000000";
            }
        } else {
            if (min < 10) {
                timeApplication = hour+":0"+min+":00.000000";
            } else {
                timeApplication = +hour+":"+min+":00.000000";
            }
        }
        timePickerDialogEnd = new TimePickerDialog(this,  timeSetListener, hour, min,true);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }





    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String makeTimeString(int Hour, int min)
    {
        if(Hour<10)
            if(min<10)
                return "0"+Hour + ":0" + min;
            else
                return "0"+Hour + ":" + min;
        else
            if(min<10)
                return Hour + ":0" + min;
            else
                return Hour + ":" + min;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "ЯНВ";
        if(month == 2)
            return "ФЕВ";
        if(month == 3)
            return "МАР";
        if(month == 4)
            return "АПР";
        if(month == 5)
            return "МАЙ";
        if(month == 6)
            return "ИЮН";
        if(month == 7)
            return "ИЮЛ";
        if(month == 8)
            return "АВГ";
        if(month == 9)
            return "СЕН";
        if(month == 10)
            return "ОКТ";
        if(month == 11)
            return "НОВ";
        if(month == 12)
            return "ДЕК";

        //default should never happen
        return "ЯНВ";
    }

    public void openDatePickerStart(View view)
    {
        datePickerDialogStart.show();
    }

    public void openDatePickerEnd(View view)
    {
        datePickerDialogEnd.show();
    }

    public void openDatePickerDeadline(View view)
    {
        datePickerDialogDeadline.show();
    }

    public void openTimePickerDeadline(View view)
    {
        timePickerDialogEnd.show();
    }


}