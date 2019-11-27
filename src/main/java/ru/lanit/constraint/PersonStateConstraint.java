package ru.lanit.constraint;

import ru.lanit.validator.PersonStateValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonStateValidator.class)
public @interface PersonStateConstraint {

    String message() default "Не валидное значение";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    EntityState existence ();
}
