package ru.lanit.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.lanit.constraint.CarStateConstraint;
import ru.lanit.constraint.PersonAgeConstraint;
import ru.lanit.constraint.EntityState;
import ru.lanit.constraint.PersonStateConstraint;
import ru.lanit.dto.CarModel;
import ru.lanit.dto.desirializer.CarModelDeserialize;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Car {

    @NotNull
    @CarStateConstraint(existence = EntityState.NOT_EXIST)
    private long id;

    @NotNull
    @JsonDeserialize(using = CarModelDeserialize.class, as = CarModel.class)
    private CarModel model;

    @NotNull
    @Min(1)
    private int horsepower;

    @NotNull
    @PersonStateConstraint(existence = EntityState.EXIST)
    @PersonAgeConstraint(minAge = 18)
    private long ownerId;

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public CarModel getModel() {
        return model;
    }

    public Car setModel(CarModel model) {
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

    public long getOwnerId() {
        return ownerId;
    }

    public Car setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
