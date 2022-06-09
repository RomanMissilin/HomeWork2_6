package com.example.homework2_6.interface1;

public interface EmployeeInterface {

    String addEmployee(String firstName, String lastName);
    String removeEmployee(String firstName, String lastName);
    String findEmployee(String firstName, String lastName);

    Integer findIdEmployee(String firsName, String lastName);
}
