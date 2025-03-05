package com.app.employee_management.service;

import com.app.employee_management.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void addEmployee(Employee employee);
}
