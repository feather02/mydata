package com.learn.thymeleaf.service;

import com.learn.thymeleaf.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    @Autowired
    Employee employee;

    List<Employee> employeeList = new ArrayList<>(
            Arrays.asList(
                    new Employee("Steve","Smith","smith@gmail.com"),
                    new Employee("David","Miller","miller@gmail.com")
            )
    );

     public List<Employee> getAllEmployees() {
        return employeeList;
    }

    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
     public String[] allDays() {
         return days;
     }
 }
