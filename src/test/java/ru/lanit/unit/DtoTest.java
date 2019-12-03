package ru.lanit.unit;

import org.junit.Test;
import ru.lanit.dto.request.Car;
import ru.lanit.dto.CarModel;
import ru.lanit.dto.request.Person;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DtoTest {

    @Test
    public void CarDtoTest(){
        Car carDto = new Car(1,new CarModel("x5","BMW"),220,1);

        assertEquals(carDto.getId(),1);
        assertEquals(carDto.getHorsepower(),220);
        assertEquals(carDto.getModel().getVendor(),"BMW");
        assertEquals(carDto.getModel().getModel(),"x5");
        assertEquals(carDto.getOwnerId(),1);

    }

    @Test
    public void PersonDtoTest(){
        Person personRequest = new Person(1,"Test", LocalDate.now());

        assertEquals(personRequest.getId(),1);
        assertEquals(personRequest.getName(),"Test");
        assertEquals(personRequest.getBirthdate().getYear(),LocalDate.now().getYear());
        assertEquals(personRequest.getBirthdate().getMonth(),LocalDate.now().getMonth());
        assertEquals(personRequest.getBirthdate().getDayOfMonth(),LocalDate.now().getDayOfMonth());

    }
}
