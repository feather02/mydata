package com.learn.thymeleaf;

import com.learn.thymeleaf.model.Employee;
import com.learn.thymeleaf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("message","Message from the MVC controller");
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees",employees);
        model.addAttribute("days",employeeService.allDays());
        return "home";
    }
}
