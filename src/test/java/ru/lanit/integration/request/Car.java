package ru.lanit.integration.request;

public class Car {

    private long id;

    private int horsepower;

    private String model;

    private long ownerId;

    public Car(long id, int horsepower, String model, long ownerId) {
        this.id = id;
        this.horsepower = horsepower;
        this.model = model;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
