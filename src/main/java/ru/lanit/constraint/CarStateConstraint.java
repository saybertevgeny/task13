package ru.lanit.constraint;

import ru.lanit.validator.CarStateValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CarStateValidator.class)
public @interface CarStateConstraint {

    String message() default "Не валидное значение";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    EntityState existence();
}
