package com.example.grifsport;

public class EventResponse {
    private int	id;
    private SportResponse sport;
    private CityResponse city;
    private String  name;
    private String description;
    private String adress;
    private String date_created;
    private String event_start_date;
    private String event_end_date;
    private String image;
    private String schedule;
    private String results;
    private String deadline_for_accepting_applications;
    private String ages;
    private String genders;
    private String phone_number;
    private Boolean is_active;
    private TypeEventResponse type;

    public EventResponse() {
    }

    public EventResponse(int id, String name, String description, String adress, String date_created, String event_start_date, String event_end_date, String image, String schedule, String results, String deadline_for_accepting_applications, String ages, String genders, String phone_number, Boolean is_active, TypeEventResponse type,CityResponse city, SportResponse sport) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.date_created = date_created;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
        this.image = image;
        this.schedule = schedule;
        this.results = results;
        this.deadline_for_accepting_applications = deadline_for_accepting_applications;
        this.ages = ages;
        this.genders = genders;
        this.phone_number = phone_number;
        this.is_active = is_active;
        this.type = type;
        this.sport = sport;
        this.city = city;
    }

    public SportResponse getSport() {
        return sport;
    }

    public void setSport(SportResponse sport) {
        this.sport = sport;
    }

    public CityResponse getCity() {
        return city;
    }

    public void setCity(CityResponse city) {
        this.city = city;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getDeadline_for_accepting_applications() {
        return deadline_for_accepting_applications;
    }

    public void setDeadline_for_accepting_applications(String deadline_for_accepting_applications) {
        this.deadline_for_accepting_applications = deadline_for_accepting_applications;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getGenders() {
        return genders;
    }

    public void setGenders(String genders) {
        this.genders = genders;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public TypeEventResponse getType() {
        return type;
    }

    public void setType(TypeEventResponse type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adress='" + adress + '\'' +
                ", date_created='" + date_created + '\'' +
                ", event_start_date='" + event_start_date + '\'' +
                ", event_end_date='" + event_end_date + '\'' +
                ", image='" + image + '\'' +
                ", schedule='" + schedule + '\'' +
                ", results='" + results + '\'' +
                ", deadline_for_accepting_applications='" + deadline_for_accepting_applications + '\'' +
                ", ages='" + ages + '\'' +
                ", genders='" + genders + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", is_active='" + is_active + '\'' +
                ", type=" + type +
                '}';
    }
}
