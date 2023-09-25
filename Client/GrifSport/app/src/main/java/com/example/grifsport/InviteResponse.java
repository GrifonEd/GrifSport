package com.example.grifsport;

public class InviteResponse {
    private int	id;
    private ProfileResponse profile;
    private EventResponse event;
    private  String vacancy;

    public InviteResponse() {
    }

    public InviteResponse(int id, ProfileResponse profile, EventResponse event, String vacancy) {
        this.id = id;
        this.profile = profile;
        this.event = event;
        this.vacancy = vacancy;

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

}
