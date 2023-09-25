package com.example.grifsport;

public class SportResponse {
    private int	id;
    private String	name;
    private TypeSportResponse typeSport;


    public SportResponse() {
    }

    public SportResponse(int id, String name, TypeSportResponse typeSport) {
        this.id = id;
        this.name = name;
        this.typeSport = typeSport;
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

    public TypeSportResponse getTypeSport() {
        return typeSport;
    }

    public void setTypeSport(TypeSportResponse typeSport) {
        this.typeSport = typeSport;
    }
}
