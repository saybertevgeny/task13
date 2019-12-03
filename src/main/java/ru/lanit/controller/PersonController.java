package ru.lanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.exception.NoEntityException;
import ru.lanit.constraint.EntityState;
import ru.lanit.constraint.PersonStateConstraint;
import ru.lanit.service.PersonService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody @Valid ru.lanit.dto.request.Person personDto) {
        personService.save(personDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
    public ResponseEntity personWithCars(@NotNull @PersonStateConstraint(existence = EntityState.EXIST) Long personid) {
        try {
            if (personid == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(personService.getWithCars(personid));
        } catch (NoEntityException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
