package com.example.grifsport;

public class TypeSportResponse {
    private int	id;
    private Boolean	olympic;
    private Boolean team;

    public TypeSportResponse() {
    }

    public TypeSportResponse(int id, Boolean olympic, Boolean team) {
        this.id = id;
        this.olympic = olympic;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getOlympic() {
        return olympic;
    }

    public void setOlympic(Boolean olympic) {
        this.olympic = olympic;
    }

    public Boolean getTeam() {
        return team;
    }

    public void setTeam(Boolean team) {
        this.team = team;
    }
}
