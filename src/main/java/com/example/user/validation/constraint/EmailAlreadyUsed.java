/**
 * 
 */
package com.example.user.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.user.validation.EmailAlreadyUsedValidator;


/**
 *@author Salman.Khandu
 *
 */
@Documented
@Constraint(validatedBy = EmailAlreadyUsedValidator.class)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAlreadyUsed {

	String message() default "{email.exists}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
