package ru.lanit.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonDto {

    private long id;

    private String name;

    private String birthDay;

    private List<CarDto> cars;

    public PersonDto(long id, String name, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay.format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
        this.cars = new ArrayList<>();
    }

    public PersonDto(long id, String name, LocalDate birthDay, List<CarDto> cars) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay.format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public void addCar(CarDto carDto){
        cars.add(carDto);
    }
}
