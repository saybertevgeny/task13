package ru.lanit.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.lanit.constraint.EntityState;
import ru.lanit.constraint.PersonStateConstraint;
import ru.lanit.dto.desirializer.LocalDateDeserialize;
import ru.lanit.dto.serializer.LocalDateSerializer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class Person {

    @NotNull
    @PersonStateConstraint(existence = EntityState.NOT_EXIST)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Past
    @JsonDeserialize(using = LocalDateDeserialize.class, as = LocalDate.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthdate;

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Person setBirthdate(LocalDate birthDay) {
        this.birthdate = birthDay;
        return this;
    }
}
