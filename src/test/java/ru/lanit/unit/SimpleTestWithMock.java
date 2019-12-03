package ru.lanit.unit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.lanit.entity.Car;
import ru.lanit.entity.Person;
import ru.lanit.exception.NoEntityException;
import ru.lanit.repository.CarRepositoryInterface;
import ru.lanit.repository.PersonRepositoryInterface;
import ru.lanit.service.PersonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class SimpleTestWithMock {

    @Test
    public void personWithCars(){
        Car car = new Car();
        ArrayList<Car> cars = new ArrayList<>();
        Person person = new Person();
        person.setId(1).setName("TestUser").setBirthDay(LocalDate.now());
        car.setHorsepower(200).setModel("X5").setVendor("BMW").setOwner(person).setId(1);
        cars.add(car);
        person.setCars(cars);

        PersonRepositoryInterface personRepository = Mockito.mock(PersonRepositoryInterface.class);
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(personRepository.existsById(anyLong())).thenReturn(true);

        long personId = 1;
        PersonService personService = new PersonService(personRepository);
        try {
            assertEquals(personService.getWithCars(personId).getId(), 1);
            assertEquals(personService.getWithCars(personId).getCars().size(), 1);
        }
        catch (NoEntityException e){
            Assert.fail(e.getMessage());
        }
    }
}
