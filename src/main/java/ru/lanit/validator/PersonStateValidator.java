package ru.lanit.validator;

import ru.lanit.constraint.EntityState;
import ru.lanit.constraint.PersonStateConstraint;
import ru.lanit.repository.PersonRepositoryInterface;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonStateValidator implements ConstraintValidator<PersonStateConstraint, Long> {

    private PersonRepositoryInterface personRepository;
    private PersonStateConstraint constraint;

    public PersonStateValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(PersonStateConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        if (constraint.existence().equals(EntityState.EXIST)) {
            return personRepository.existsById(id);
        } else {
            return !personRepository.existsById(id);
        }
    }
}
