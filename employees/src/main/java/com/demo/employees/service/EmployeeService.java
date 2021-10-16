package com.demo.employees.service;



import com.demo.employees.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees(String where);

    Employee getEmployeeById(long employeeId);

    Employee createEmployee(Map employeeBody);

    Employee updatePutEmployee(Map employeeBody, long employeeId);

    Employee updatePatchEmployee(Map employeeBody, long employeeId);

    void deleteEmployee(long employeeId);
}
