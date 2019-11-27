package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.Exception.NoEntityException;
import ru.lanit.dto.CarDto;
import ru.lanit.dto.PersonDto;
import ru.lanit.entity.Car;
import ru.lanit.entity.Person;
import ru.lanit.repository.PersonRepositoryInterface;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonService {

    private PersonRepositoryInterface personRepository;

    @Autowired
    public PersonService(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    public boolean existPerson(long id) {
        return personRepository.existsById(id);
    }

    public PersonDto getById(long id) throws NoEntityException {
        Person person = personRepository.findById(id).orElseThrow(() -> new NoEntityException());
        return new PersonDto(person.getId(), person.getName(), person.getBirthDay());
    }

    public void save(ru.lanit.dto.request.Person requestPerson) {
        Person person = new Person();
        person.setBirthDay(requestPerson.getBirthdate())
                .setName(requestPerson.getName())
                .setId(requestPerson.getId());
        personRepository.save(person);
    }

    @Transactional
    public PersonDto getWithCars(Long personId) throws NoEntityException{
        Person person = personRepository.findById(personId).orElseThrow(() -> new NoEntityException());
        List<Car> cars = person.getCars();
        List<CarDto> listCarDto = new ArrayList<>();
        for(Car car: cars){
            listCarDto.add(new CarDto(car.getId(),car.getModel(),car.getVendor(),car.getHorsepower()));
        }
        return new PersonDto(person.getId(),person.getName(),person.getBirthDay(),listCarDto);
    }

    public long getCountPersons(){
        return personRepository.count();
    }

    @Transactional
    public void drop(){
        personRepository.deleteAll();
    }

}
