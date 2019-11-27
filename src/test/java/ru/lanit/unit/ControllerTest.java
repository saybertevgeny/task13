package ru.lanit.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lanit.CarController;
import ru.lanit.PersonController;
import ru.lanit.dto.CarModel;
import ru.lanit.dto.request.Car;
import ru.lanit.dto.request.Person;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private CarController carController;


    @Test
    public void testPersonSave() {
        Person requestPerson = new Person();
        requestPerson.setId(1).setName("TestUser").setBirthdate(LocalDate.now().minusYears(19));
        assertEquals(personController.save(requestPerson), ResponseEntity.ok().build());
    }

    @Test
    public void testCarSave() {
        Car carRequest = new Car();
        carRequest.setModel(new CarModel("BMW", "X5")).setHorsepower(200).setOwnerId(1).setId(1);
        assertEquals(carController.save(carRequest), ResponseEntity.ok().build());

        carRequest.setModel(new CarModel("Lada", "Granta")).setHorsepower(87).setOwnerId(1).setId(2);
        assertEquals(carController.save(carRequest), ResponseEntity.ok().build());

    }

    @Test
    public void testPersonWithCars() {
        long personId = 1;
        Person requestPerson = new Person();
        requestPerson.setId(personId).setName("TestUser").setBirthdate(LocalDate.now().minusYears(19));
        assertEquals(personController.personWithCars(personId).getStatusCode(), HttpStatus.OK);
        assertEquals(personController.personWithCars(Long.valueOf(2)).getStatusCode(), HttpStatus.NOT_FOUND);
    }

}