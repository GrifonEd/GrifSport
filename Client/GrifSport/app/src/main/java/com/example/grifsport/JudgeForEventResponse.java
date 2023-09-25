package com.example.grifsport;

public class JudgeForEventResponse {
    private int	id;
    private EventResponse event;
    private JudgeResponse judge;


    public JudgeForEventResponse() {
    }

    public JudgeForEventResponse(int id, EventResponse event, JudgeResponse judge) {
        this.id = id;
        this.event = event;
        this.judge = judge;
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

    public JudgeResponse getJudge() {
        return judge;
    }

    public void setJudge(JudgeResponse judge) {
        this.judge = judge;
    }
}
