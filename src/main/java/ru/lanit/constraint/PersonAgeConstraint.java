package ru.lanit.constraint;

import ru.lanit.validator.PersonAgeValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonAgeValidator.class)
public @interface PersonAgeConstraint {

    String message() default "Пользователь слишком молод";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long minAge() default 18;
}
