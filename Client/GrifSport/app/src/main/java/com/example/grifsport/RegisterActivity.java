package com.example.grifsport;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.grifsport.databinding.ActivityRegisterBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData(); //проверка данных введенных в форму регистрации
            }
        });
        //стрелочка назад
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.sexEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( binding.sexEt.getText().toString().equals("Мужской"))
                binding.sexEt.setText("Женский");
                else
                    binding.sexEt.setText("Мужской");
            }
        });

    }
    private String email="";
    private int id=1;
    private String password="";
    private String re_password="";
    private String username="";
    private int age;
    private String first_name="";
    private String second_name="";
    private String city="";
    private  String phone="";
    private String sex="";

    private void validateData() {
        first_name = binding.nameEt.getText().toString().trim(); //вносим в переменные данные из полей формы
        second_name = binding.secondName.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();
        re_password = binding.rePasswordEt.getText().toString().trim();
        username = binding.usernameEt.getText().toString().trim();
        sex=binding.sexEt.getText().toString().trim();
        email=binding.emailEt.getText().toString().trim();
        phone = binding.phoneNumberEt.getText().toString().trim();
        city = binding.cityEt.getText().toString().trim();
        if(!binding.ageEt.getText().toString().equals("")) {
            age = Integer.valueOf(binding.ageEt.getText().toString().trim());
            if (age > 100 || age < 0) {
                Toast.makeText(com.example.grifsport.RegisterActivity.this, "Введите свой действительный возраст", Toast.LENGTH_SHORT).show();
            }
        }

        if(TextUtils.isEmpty(first_name)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите свое имя", Toast.LENGTH_SHORT).show();
        }
        //пустая ли фамилия?
        else if(TextUtils.isEmpty(second_name)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите фамилию", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(username)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите имя пользователя", Toast.LENGTH_SHORT).show();

        }



        else if(TextUtils.isEmpty(email)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите адрес электронной почты", Toast.LENGTH_SHORT).show();

        }
        // и т.д.
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<8){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Пароль должен содержать не меньше 8-ми символов", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(re_password)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Повторите пароль", Toast.LENGTH_SHORT).show();

        }

        //если подтверждение пароля не совпало с паролем
        else if(!password.equals((re_password))) {
            Toast.makeText(com.example.grifsport.RegisterActivity.this, "Ваши пароли не совпадают!", Toast.LENGTH_SHORT).show();

        }
        else if (binding.ageEt.getText().toString().equals(""))
            Toast.makeText(com.example.grifsport.RegisterActivity.this, "Введите свой возраст!", Toast.LENGTH_SHORT).show();

        else if(TextUtils.isEmpty(city)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите свой город", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(com.example.grifsport.RegisterActivity.this,"Введите свой номер телефона", Toast.LENGTH_SHORT).show();
        }

        else  //если нет такого же пользователя, создаем аккаунт нового пользователя
        {

            createUserAccount(); //создание нового аккаунта
        }

    }

    private void createUserAccount() {

        UserResponse user = new UserResponse(email,id,password,re_password,username,age,first_name,second_name,sex,city,phone);
        Call<UserResponse> call = ApiClient.postUserService().createUser(user);
        Log.d("Error",user.toString());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                //если запрос неверный
                if (!response.isSuccessful()) {
                    Log.e("Тест занятого пользователя2",response.headers().toString());
                    Log.e("Тест занятого пользователя3",response.toString());



                    Gson gson = new Gson();
                    Type type = new TypeToken<ErrorResponse>() {}.getType();
                    ErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(),type);
                    if(errorResponse.getEmail()!=null)
                        if(errorResponse.getEmail().get(0).equals("user with this email address already exists."))
                            Toast.makeText(RegisterActivity.this, "Пользователь с такой электронной почтой уже существует!", Toast.LENGTH_SHORT).show();
                    if(errorResponse.getUsername()!=null)
                    if(errorResponse.getUsername().get(0).equals("user with this username already exists."))
                        Toast.makeText(RegisterActivity.this, "Пользователь с таким именем пользователя уже существует!", Toast.LENGTH_SHORT).show();
                    if(errorResponse.getPassword()!=null)
                    if(errorResponse.getPassword().get(0).equals("This password is entirely numeric.") || errorResponse.getPassword().get(0).equals("password is too common."))
                        Toast.makeText(RegisterActivity.this, "Пароль должен содержать не только цифры!", Toast.LENGTH_SHORT).show();
                    if(errorResponse.getPassword()!=null)
                        if(errorResponse.getPassword().get(1).equals("This password is entirely numeric.")|| errorResponse.getPassword().get(1).equals("password is too common."))
                            Toast.makeText(RegisterActivity.this, "Пароль должен содержать не только цифры!", Toast.LENGTH_SHORT).show();
                }
                else
                //запрос верный
                {

                    Call<ProfileResponse> call1 = ApiClient.profileServiceGet().getProfile(response.body().getId());
                    call1.enqueue(new Callback<ProfileResponse>() {
                        @Override
                        public void onResponse(Call<ProfileResponse> call1, Response<ProfileResponse> response) {
                            if (response.isSuccessful()) {
                                WorkerResponse workerResponse = new WorkerResponse(1,response.body(),"Рабочий");
                                Call<WorkerResponse> call2 = ApiClient.workerServicePost().workerPost(workerResponse);
                                call2.enqueue(new Callback<WorkerResponse>() {
                                    @Override
                                    public void onResponse(Call<WorkerResponse> call, Response<WorkerResponse> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<WorkerResponse> call, Throwable t) {

                                    }
                                });

                                OrganizerResponse organizerResponse = new OrganizerResponse(0,response.body(),"Организатор");
                                Call<OrganizerResponse> callOrganizer = ApiClient.organizerServicePost().OrganizerPost(organizerResponse);
                                callOrganizer.enqueue(new Callback<OrganizerResponse>() {
                                    @Override
                                    public void onResponse(Call<OrganizerResponse> call, Response<OrganizerResponse> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<OrganizerResponse> call, Throwable t) {

                                    }
                                });

                                JudgeResponse judgeResponse = new JudgeResponse(0,response.body(),"Судья");
                                Call<JudgeResponse> calljudge = ApiClient.judgeServicePost().JudgePost(judgeResponse);
                                calljudge.enqueue(new Callback<JudgeResponse>() {
                                    @Override
                                    public void onResponse(Call<JudgeResponse> call, Response<JudgeResponse> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<JudgeResponse> call, Throwable t) {

                                    }
                                });

                                ViewerResponse viewerResponse  = new ViewerResponse(0,response.body(),"Зритель");
                                Call<ViewerResponse> callviewer = ApiClient.viewerServicePost().ViewerPost(viewerResponse);
                                callviewer.enqueue(new Callback<ViewerResponse>() {
                                    @Override
                                    public void onResponse(Call<ViewerResponse> call, Response<ViewerResponse> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<ViewerResponse> call, Throwable t) {

                                    }
                                });

                                ParticipantResponse participantResponse = new ParticipantResponse(0,response.body(),"Участник");
                                Call<ParticipantResponse> callparticipant = ApiClient.participantServicePost().ParticipantPost(participantResponse);
                                callparticipant.enqueue(new Callback<ParticipantResponse>() {
                                    @Override
                                    public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<ParticipantResponse> call, Throwable t) {

                                    }
                                });

                            }
                        }
                        @Override
                        public void onFailure(Call<ProfileResponse> call1, Throwable t) {

                        }
                    });


                    Toast.makeText(RegisterActivity.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();

                }
            }

            //неуспешный запрос
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Проблемы с соединением!", Toast.LENGTH_SHORT).show();

                return;
            }
        });
    }


    }
