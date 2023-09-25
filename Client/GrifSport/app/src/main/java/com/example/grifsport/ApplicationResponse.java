package com.example.grifsport;

public class ApplicationResponse {
    private int	id;
    private ProfileResponse profile;
    private EventResponse event;
    private  String vacancy;
    private  String description;

    public ApplicationResponse() {
    }

    public ApplicationResponse(int id, ProfileResponse profile, EventResponse event, String vacancy, String description) {
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

    public ProfileResponse getProfile() {
        return profile;
    }

    public void setProfile(ProfileResponse profile) {
        this.profile = profile;
    }

    public EventResponse getEvent() {
        return event;
    }

    public void setEvent(EventResponse event) {
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
