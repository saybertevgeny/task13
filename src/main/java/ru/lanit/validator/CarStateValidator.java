package ru.lanit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.lanit.constraint.CarStateConstraint;
import ru.lanit.constraint.EntityState;
import ru.lanit.repository.CarRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarStateValidator implements ConstraintValidator<CarStateConstraint, Long> {

    private CarStateConstraint constraint;
    private CarRepositoryInterface carRepository;

    @Autowired
    public CarStateValidator(CarRepositoryInterface carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void initialize(CarStateConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        if (id == 0)
            return false;
        if (constraint.existence().equals(EntityState.EXIST)) {
            return carRepository.existsById(id);
        } else {
            return !carRepository.existsById(id);
        }
    }
}