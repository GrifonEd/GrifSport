package com.example.grifsport;

public class TypeEventResponse {
    private int	id;
    private String type;

    public TypeEventResponse(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeEventResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
