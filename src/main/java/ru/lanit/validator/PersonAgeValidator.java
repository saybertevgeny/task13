package ru.lanit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.lanit.exception.NoEntityException;
import ru.lanit.constraint.PersonAgeConstraint;
import ru.lanit.entity.Person;
import ru.lanit.repository.PersonRepositoryInterface;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonAgeValidator implements ConstraintValidator<PersonAgeConstraint, Long> {

    private PersonAgeConstraint constraint;
    private PersonRepositoryInterface personRepository;

    @Autowired
    public PersonAgeValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(PersonAgeConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {

        try {
            Person person = personRepository.findById(id).orElseThrow(() -> new NoEntityException());
            return person.getAge() > constraint.minAge();
        } catch (NoEntityException e) {
            return false;
        }
    }
}
