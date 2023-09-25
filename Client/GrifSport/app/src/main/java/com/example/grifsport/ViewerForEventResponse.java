package com.example.grifsport;

public class ViewerForEventResponse {
    private int	id;
    private EventResponse event;
    private ViewerResponse viewer;


    public ViewerForEventResponse() {
    }

    public ViewerForEventResponse(int id, EventResponse event, ViewerResponse viewer) {
        this.id = id;
        this.event = event;
        this.viewer = viewer;
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

    public ViewerResponse getViewer() {
        return viewer;
    }

    public void setViewer(ViewerResponse viewer) {
        this.viewer = viewer;
    }
}
