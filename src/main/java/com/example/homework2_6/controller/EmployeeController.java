package com.example.homework2_6.controller;

import com.example.homework2_6.exception.EmployeeAlreadyAddedException;
import com.example.homework2_6.exception.EmployeeNotFoundException;
import com.example.homework2_6.exception.EmployeeStorageIsFullException;
import com.example.homework2_6.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeAlreadyAddedException q) {
            return "В данном списке уже есть такой сотрудник.";
        } catch (EmployeeStorageIsFullException w) {
            return "Список сотрудников переполнен.";
        }
    }

    @GetMapping(path = "/employee/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            return employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException q) {
            return "Сотрудник не найден!";
        }
    }

    @GetMapping(path = "/employee/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException q) {
            return "Сотрудник не найден!";
        }
    }
}
