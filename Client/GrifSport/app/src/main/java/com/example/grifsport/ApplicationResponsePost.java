package com.example.grifsport;

public class ApplicationResponsePost {
    private int	id;
    private int profile;
    private int event;
    private  String vacancy;
    private  String description;

    public ApplicationResponsePost() {
    }

    public ApplicationResponsePost(int id, int profile, int event, String vacancy, String description) {
        this.id = id;
        this.profile = profile;
        this.event = event;
        this.vacancy = vacancy;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
