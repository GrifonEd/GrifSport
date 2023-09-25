package com.example.grifsport;

public class OrganizerResponse {
    private int	id;
    private ProfileResponse profile;
    private String description;


    public OrganizerResponse() {
    }

    public OrganizerResponse(int id, ProfileResponse profile, String description) {
        this.id = id;
        this.profile = profile;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfileResponse getProfileResponse() {
        return profile;
    }

    public void setProfileResponse(ProfileResponse profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
