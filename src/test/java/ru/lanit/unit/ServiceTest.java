package ru.lanit.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lanit.service.CarService;
import ru.lanit.service.PersonService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private CarService carService;

    @Test
    @Sql(scripts = {"classpath:clear.sql","classpath:prepare.sql"})
    public void countPerson() {
        assertEquals(personService.getCountPersons(), 1);
    }

    @Test
    @Sql(scripts = {"classpath:clear.sql","classpath:prepare.sql"})
    public void countCars() {
        assertEquals(carService.getCount(), 3);
        assertEquals(carService.getCountUniqueVendor(), 2);
    }
}
