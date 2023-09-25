package com.example.grifsport;

public class OrganizerForEventResponse {
    private int	id;
    private EventResponse event;
    private OrganizerResponse organizer;


    public OrganizerForEventResponse() {
    }

    public OrganizerForEventResponse(int id, EventResponse event, OrganizerResponse organizer) {
        this.id = id;
        this.event = event;
        this.organizer = organizer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventResponse getEventResponse() {
        return event;
    }

    public void setEventResponse(EventResponse event) {
        this.event = event;
    }

    public OrganizerResponse getOrganizerResponse() {
        return organizer;
    }

    public void setOrganizerResponse(OrganizerResponse organizer) {
        this.organizer = organizer;
    }
}
