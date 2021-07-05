package br.com.zupacademy.gabrielpedrico.mercadolivre.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValidator.class)
public @interface Exists {

    Class<?> domainClass();

    String fieldName();

    String message() default "Nao existe um objeto com o Id informado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
