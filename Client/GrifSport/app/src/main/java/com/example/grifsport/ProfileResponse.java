package com.example.grifsport;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    private int id;
    private UserResponseGet user;
    private int age;
    private String first_name;
    private String second_name;
    private String sex;
    private String city;
    private String phone_number;

    public ProfileResponse(int id, UserResponseGet user, int age, String first_name, String second_name, String sex, String city, String phone_number) {
        this.id = id;
        this.user = user;
        this.age = age;
        this.first_name = first_name;
        this.second_name = second_name;
        this.sex = sex;
        this.city = city;
        this.phone_number = phone_number;
    }

    public ProfileResponse() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public UserResponseGet getUserResponse() {
        return user;
    }

    public void setUserResponse(UserResponseGet user) {
        this.user = user;
    }
}
