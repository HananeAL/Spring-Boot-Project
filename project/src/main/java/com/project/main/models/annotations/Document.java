package com.project.main.models.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DocumentValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface Document {
  String message() default "only pdf and word are allowed";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

/*
 * @Documented
 * 
 * @Constraint(validatedBy = RequiredFileValidator.class)
 * 
 * @Target({ElementType.METHOD, ElementType.FIELD })
 * 
 * @Retention(RetentionPolicy.RUNTIME)
 * 
 * public @interface RequiredFile { String message() default ""; Class<?>[]
 * groups() default {}; Class<? extends Payload>[] payload() default {}; }
 */
