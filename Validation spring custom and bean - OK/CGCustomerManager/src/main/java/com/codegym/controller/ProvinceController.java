package com.codegym.controller;


import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import com.codegym.validator.EmailValidator;
import com.codegym.validator.ProvinceValidator;
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

@Controller
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces(){
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateForm(){
        System.out.println("ProvinceController: " +  "showCreateForm");
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }
    @PostMapping("/create-province")
    public ModelAndView saveProvince(@Valid @ModelAttribute("province") Province province, BindingResult bindingResult){

        new ProvinceValidator().validate(province, bindingResult);

        ModelAndView modelAndView = new ModelAndView("/province/create");

        if(!bindingResult.hasErrors()){
            provinceService.save(province);
            modelAndView.addObject("province", new Province());
        }else{
            modelAndView.addObject("province", province);
        }
        return modelAndView;
    }
}
