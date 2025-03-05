package com.app.employee_management.controller;

import com.app.employee_management.model.Employee;
import com.app.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("allemplist",employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add";
    }


    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/";
    }
}
