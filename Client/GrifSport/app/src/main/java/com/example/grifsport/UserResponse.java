package com.example.grifsport;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    private String email;
    private int id;
    private String password;
    private String re_password;
    private String username;
    private int age;
    private String first_name;
    private String second_name;
    private String sex;
    private String city;
    private String phone_number;
    private String date_joined;
    private String is_active;
    private String is_staff;
    private String is_verified;

    public UserResponse(String email, int id, String password, String re_password, String username, int age, String first_name, String second_name, String sex,String city, String phone_number) {
        this.email = email;
        this.id = id;
        this.password = password;
        this.re_password = re_password;
        this.username = username;
        this.age = age;
        this.first_name = first_name;
        this.second_name = second_name;
        this.sex = sex;
        this.city=city;
        this.phone_number=phone_number;
    }

    public UserResponse(String email, int id, String password, String re_password, String username, int age, String first_name, String second_name, String sex, String city, String phone_number, String date_joined, String is_active, String is_staff, String is_verified) {
        this.email = email;
        this.id = id;
        this.password = password;
        this.re_password = re_password;
        this.username = username;
        this.age = age;
        this.first_name = first_name;
        this.second_name = second_name;
        this.sex = sex;
        this.city = city;
        this.phone_number = phone_number;
        this.date_joined = date_joined;
        this.is_active = is_active;
        this.is_staff = is_staff;
        this.is_verified = is_verified;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(String is_staff) {
        this.is_staff = is_staff;
    }

    public String getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(String is_verified) {
        this.is_verified = is_verified;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_password() {
        return re_password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @SerializedName("body")



    @Override
    public String toString() {
        return "{" +
                "\"id\": "+ id +
                ", \"email\": \"" + email + '\"' +
                ", \"password\": \"" + password + '\"' +
                ", \"age\": "+ age +
                ", \"re_password\": \"" + re_password + '\"' +
                ", \"username\": \"" + username + '\"' +
                ", \"first_name\": \"" + first_name + '\"' +
                ", \"second_name\": \"" + second_name + '\"' +
                ", \"sex\": \"" + sex + '\"' +
                ", \"city\": \"" + city + '\"' +
                ", \"phone_number\": \"" + phone_number + '\"' +
                '}';
    }

}
