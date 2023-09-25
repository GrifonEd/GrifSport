package com.example.grifsport;

public class DisciplineResponse {
    private int	id;
    private String	name;
    private SportResponse sport;
    private TypeDisciplineResponse type;

    public DisciplineResponse() {
    }

    public DisciplineResponse(int id, String name, SportResponse sport, TypeDisciplineResponse type) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportResponse getSport() {
        return sport;
    }

    public void setSport(SportResponse sport) {
        this.sport = sport;
    }

    public TypeDisciplineResponse getType() {
        return type;
    }

    public void setType(TypeDisciplineResponse type) {
        this.type = type;
    }


}
