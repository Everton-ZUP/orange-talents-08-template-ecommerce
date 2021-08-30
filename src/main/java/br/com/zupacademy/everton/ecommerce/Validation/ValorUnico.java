package br.com.zupacademy.everton.ecommerce.Validation;

import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {ValorUnicoValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

    String message() default "Já existe um registro com este mesmo valor";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String fieldName();
    Class<?> domainClass();
}
