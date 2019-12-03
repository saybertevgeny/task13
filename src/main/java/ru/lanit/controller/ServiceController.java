package ru.lanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.dto.Statistic;
import ru.lanit.service.CarService;
import ru.lanit.service.PersonService;

@RestController
public class ServiceController {

    private PersonService personService;
    private CarService carService;

    @Autowired
    public ServiceController(PersonService personService, CarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResponseEntity statistics() {

        return ResponseEntity.ok(
                new Statistic(
                        personService.getCountPersons(),
                        carService.getCount(),
                        carService.getCountUniqueVendor()
                )
        );
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public ResponseEntity clear() {
        personService.drop();
        carService.drop();
        return ResponseEntity.ok().build();
    }
}
