package com.example.world.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp="^[A-Z]{3}$")
@Constraint(validatedBy = {})
public @interface CountryCode {
	String message() default "{validation.countryCode}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
