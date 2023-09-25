package com.example.grifsport;

public class WorkerForEventResponse {
    private int	id;
    private EventResponse event;
    private WorkerResponse worker;


    public WorkerForEventResponse() {
    }

    public WorkerForEventResponse(int id, EventResponse event, WorkerResponse worker) {
        this.id = id;
        this.event = event;
        this.worker = worker;
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

    public WorkerResponse getWorker() {
        return worker;
    }

    public void setWorker(WorkerResponse worker) {
        this.worker = worker;
    }
}
