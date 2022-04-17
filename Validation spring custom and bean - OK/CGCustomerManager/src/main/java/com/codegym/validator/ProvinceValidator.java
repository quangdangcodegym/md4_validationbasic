package com.codegym.validator;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProvinceValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Province.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Province province = (Province) target;
        //ValidationUtils.rejectIfEmpty(errors, "firstName", "email.exist");

        if(!province.getName().contains("cgh")){
            System.out.println("ProvinceValidator :"  + province.getName());
            errors.rejectValue("name", "province.name.exist");
        }
    }
}
