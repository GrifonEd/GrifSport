package com.example.grifsport;

import com.google.gson.annotations.SerializedName;

public class UserResponseGet {
    private String email;
    private int id;
    private String username;
    private String date_joined;
    private String is_active;
    private String is_staff;
    private String is_verified;

    public UserResponseGet(String email, int id, String username, String date_joined, String is_active, String is_staff, String is_verified) {
        this.email = email;
        this.id = id;
        this.username = username;
        this.date_joined = date_joined;
        this.is_active = is_active;
        this.is_staff = is_staff;
        this.is_verified = is_verified;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
