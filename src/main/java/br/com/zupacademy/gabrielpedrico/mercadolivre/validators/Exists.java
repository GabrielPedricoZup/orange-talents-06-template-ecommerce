package br.com.zupacademy.gabrielpedrico.mercadolivre.validators;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValidator.class)
public @interface Exists {

    Class<?> domainClass();

    String fieldName();

    String message() default "Nao existe um objeto informado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
