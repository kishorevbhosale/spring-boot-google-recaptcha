package com.captcha.demo.controller;

import com.captcha.demo.entity.Employee;
import com.captcha.demo.service.CaptchaValidator;
import com.captcha.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;
    private final CaptchaValidator validator;

    @GetMapping("/register")
    public String registerEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "registerEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute Employee employee,
            Model model,
            @RequestParam("g-recaptcha-response") String captcha
    ) {
        if (validator.isValidCaptcha(captcha)) {
            Integer id = service.createUser(employee);
            model.addAttribute("message", "Employee with id : '" + id + "' Saved Successfully !");
            model.addAttribute("employee", new Employee());
        } else {
            model.addAttribute("message", "Please validate captcha first");
        }

        return "registerEmployee";
    }

    @GetMapping("/all")
    public String getAllEmployee(Model model) {
        List<Employee> employees = service.getAllUsers();
        model.addAttribute("list", employees);
        return "listEmployee";
    }
}

