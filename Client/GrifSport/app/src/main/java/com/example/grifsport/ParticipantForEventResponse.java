package com.example.grifsport;

public class ParticipantForEventResponse {
    private int	id;
    private EventResponse event;
    private ParticipantResponse participant;


    public ParticipantForEventResponse() {
    }

    public ParticipantForEventResponse(int id, EventResponse event, ParticipantResponse participant) {
        this.id = id;
        this.event = event;
        this.participant = participant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventResponse getEvent() {
        return event;
    }

    public void setEvent(EventResponse event) {
        this.event = event;
    }

    public ParticipantResponse getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantResponse participant) {
        this.participant = participant;
    }
}
