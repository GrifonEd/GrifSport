package com.example.grifsport;

public class DisciplineForEventResponse {
    private int	id;
    private EventResponse event;
    private DisciplineResponse discipline;


    public DisciplineForEventResponse() {
    }

    public DisciplineForEventResponse(int id, EventResponse event, DisciplineResponse discipline) {
        this.id = id;
        this.event = event;
        this.discipline = discipline;
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

    public DisciplineResponse getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineResponse discipline) {
        this.discipline = discipline;
    }
}
