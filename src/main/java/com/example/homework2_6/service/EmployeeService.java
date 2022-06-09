package com.example.homework2_6.service;

import com.example.homework2_6.data.Employee;
import com.example.homework2_6.exception.EmployeeAlreadyAddedException;
import com.example.homework2_6.exception.EmployeeNotFoundException;
import com.example.homework2_6.exception.EmployeeStorageIsFullException;
import com.example.homework2_6.interface1.EmployeeInterface;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmployeeService implements EmployeeInterface {

    Employee[] employees = {

            new Employee("Жан", "Рено"),
            new Employee("Чак", "Норис"),
            new Employee("Джеки", "Чан"),
            new Employee("Силвестер", "Сталоне"),
            new Employee("Евгений", "Кузнецов")
    };

    @Override
    public String addEmployee(String firstName, String lastName) {
        if (findEmployee(firstName, lastName) != null) {
            throw new EmployeeAlreadyAddedException("В данном списке уже есть такой сотрудник.");
        } else {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = new Employee(firstName, lastName);
                    return String.valueOf(employees[i]);
                } else {
                    if (i == employees.length - 1) {
                        throw new EmployeeStorageIsFullException("Список сотрудников переполнен.");
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        Integer idEmployee = findIdEmployee(firstName, lastName);
        if (idEmployee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        } else {
            Employee r = employees[idEmployee];
            employees[idEmployee] = null;
            return String.valueOf(r);
        }
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        Integer idEmployee = findIdEmployee(firstName, lastName);
        if (idEmployee != null) {
            return String.valueOf(employees[idEmployee]);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден!");
    }

    @Override
    public Integer findIdEmployee(String firsName, String lastName) {
        System.out.println("Список сотрудников = " + Arrays.toString(employees));
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getFirstName().equals(firsName) && employees[i].getLastName().equals(lastName));
                return i;
            }
        }
        return null;
    }
}
