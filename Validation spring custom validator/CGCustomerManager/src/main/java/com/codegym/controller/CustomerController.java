package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.validator.EmailValidator;
import com.codegym.validator.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private WebAppValidator webAppValidator;

    @Autowired
    private EmailValidator emailValidator;
    //    @PostMapping("/")
//    public String checkValidation (@Valid @ModelAttribute("phonemunber")PhoneNumber phoneNumber, BindingResult bindingResult, Model model){
//        new PhoneNumber().validate(phoneNumber, bindingResult);
//        if (bindingResult.hasFieldErrors()){
//            return "index";
//        }
//        else {
//            model.addAttribute("phoneNumber", phoneNumber.getNumber());
//            return "result";
//        }
//
//    }

    @Autowired
    CustomerService customerService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //binder.setValidator(emailValidator);
        binder.setValidator(webAppValidator);
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView m = new ModelAndView("/addcustomer");

        m.addObject("customer", new Customer());
        m.addObject("message", "Create customer");
        return  m;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult){
        ModelAndView m = new ModelAndView("/addcustomer");

        System.out.println(customer.getFirstName() +"---" + customer.getLastName());
        if(!bindingResult.hasErrors()){

            customerService.saveCustomer(customer);
            m.addObject("customer", new Customer(customer.getFirstName(), customer.getLastName()));
            m.addObject("message", "Create customer");
        }else{
            m.addObject("message", "Error email input");
        }

        return  m;
    }
    @GetMapping("/index")
    public ModelAndView listCustomer(){
        ModelAndView m = new ModelAndView("/index");
        List<Customer> listCustomers = customerService.findAllCustomer();

        System.out.println(listCustomers);
        m.addObject("customers", listCustomers);
        return m;
    }
}
