package peksoft.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface PhoneNumberValidation {

    String message() default "Phone number must start with country code '+996' and must 13 symbol";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
