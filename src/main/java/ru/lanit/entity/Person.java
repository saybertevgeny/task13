package ru.lanit.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    private long id;

    private String name;

    private LocalDate birthDay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    private List<Car> cars;

    public Person() {
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Person setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public int getAge() {
        return Period.between(birthDay, LocalDate.now()).getYears();
    }
}
