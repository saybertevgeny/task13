package ru.lanit.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lanit.entity.Person;

public interface PersonRepositoryInterface extends CrudRepository<Person,Long> {
}
