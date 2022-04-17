package com.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.codegym.model.PhoneNumber;

import javax.validation.Valid;

@Controller

public class PhoneController {
    @Autowired
    private PhoneNumber phoneNumber;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(phoneNumber);
    }


    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("phonemunber", new PhoneNumber());
        return "index";
    }
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
        @PostMapping("/")
        public String checkValidation ( @ModelAttribute("phonemunber") @Valid PhoneNumber phoneNumber,
                                        BindingResult bindingResult, Model model){
            if(!bindingResult.hasErrors()){
                model.addAttribute("phoneNumber", phoneNumber.getNumber());
                return "result";
            }else {
                return "index";
            }
        }

}
