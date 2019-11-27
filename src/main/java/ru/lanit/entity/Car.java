package ru.lanit.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    private long id;

    private String vendor;

    private String model;

    private int horsepower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Person owner;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public Car setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public Car setHorsepower(int horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public Person getOwner() {
        return owner;
    }

    public Car setOwner(Person owner) {
        this.owner = owner;
        return this;
    }
}
