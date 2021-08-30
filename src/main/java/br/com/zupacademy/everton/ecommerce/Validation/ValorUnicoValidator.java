package br.com.zupacademy.everton.ecommerce.Validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico,Object> {

    private Class<?> classe;
    private String campoParaValidar;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        classe = constraintAnnotation.domainClass();
        campoParaValidar = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("HEHE");
        Query sql = manager.createQuery("select 1 from "+classe.getName()+" where "+campoParaValidar+" = :value");
        sql.setParameter("value", value);
        List<?> retorno = sql.getResultList();
        Assert.isTrue(retorno.size() <= 1,"Foi encontrado mais de 1 registro na tabela "+classe.getName()+" com o mesmo "+campoParaValidar+" = "+value);
        return retorno.isEmpty();
    }
}
