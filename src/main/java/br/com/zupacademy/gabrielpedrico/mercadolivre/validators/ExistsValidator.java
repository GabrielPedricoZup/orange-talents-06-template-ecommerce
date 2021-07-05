package br.com.zupacademy.gabrielpedrico.mercadolivre.validators;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    private String name;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(Exists params) {

        name = params.fieldName();
        domainClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Boolean isValid = em.createQuery("select 1 from " + domainClass.getName() + " where " + name + " = :pValue " )
                .setParameter("pValue", value)
                .getResultList()
                .isEmpty();

        //isValid = !isValid;
        return isValid;
    }



}
