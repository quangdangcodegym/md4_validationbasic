package com.codegym.validator;

import com.codegym.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        //ValidationUtils.rejectIfEmpty(errors, "firstName", "email.exist");

        if(!customer.getFirstName().contains("cgh")){
            System.out.println("CGH - Error validate :"  + customer.getFirstName());
            errors.rejectValue("firstName", "email.exist");
        }
    }
}
