package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.dto.request.Car;
import ru.lanit.entity.Person;
import ru.lanit.repository.CarRepositoryInterface;
import ru.lanit.repository.PersonRepositoryInterface;

@Component
public class CarService {

    private CarRepositoryInterface carRepository;
    private PersonRepositoryInterface personRepository;

    @Autowired
    public CarService(CarRepositoryInterface carRepository, PersonRepositoryInterface personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    public void save(Car requestCar) {
        Person person = personRepository.findById(requestCar.getOwnerId()).get();
        ru.lanit.entity.Car carEntity = new ru.lanit.entity.Car();
        carEntity.setId(requestCar.getId())
                .setHorsepower(requestCar.getHorsepower())
                .setModel(requestCar.getModel().getModel())
                .setVendor(requestCar.getModel().getVendor())
                .setOwner(person);
        carRepository.save(carEntity);
    }

    public long getCount(){
        return carRepository.count();
    }

    public long getCountUniqueVendor(){
        return carRepository.getCountUniqueVendor();
    }

    @Transactional
    public void drop(){
        carRepository.deleteAll();
    }
}
