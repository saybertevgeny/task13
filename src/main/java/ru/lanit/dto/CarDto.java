package ru.lanit.dto;

public class CarDto {

    private long id;

    private String model;

    private String vendor;

    private int horsepower;

    public CarDto(long id, String model, String vendor, int horsepower) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
        this.horsepower = horsepower;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
}
