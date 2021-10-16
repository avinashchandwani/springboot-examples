package com.demo.employees.service;

import com.demo.employees.entity.Employee;
import com.demo.employees.repository.EmployeeRepository;
import com.demo.employees.util.JacksonUtil;
import com.demo.employees.util.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees(String where) {
        Map queryParams = QueryParser.parseQuery(where.trim());
        String firstName = null, lastName = null;
        if (!CollectionUtils.isEmpty(queryParams)) {
            firstName = queryParams.containsKey("firstName") ? (String) queryParams.get("firstName") : null;
            lastName = queryParams.containsKey("lastName") ? (String) queryParams.get("lastName") : null;
        }
        return employeeRepository.findAll(firstName, lastName);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Employee createEmployee(Map employeeBody) {
        Employee employee = (Employee) JacksonUtil.convertMapToObject(employeeBody, Employee.class);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updatePutEmployee(Map employeeBody, long employeeId) {
        Employee employee = (Employee) JacksonUtil.convertMapToObject(employeeBody, Employee.class);
        employee.setEmployeeId(employeeId);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updatePatchEmployee(Map employeeBody, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId);
        if (employeeBody.containsKey("firstName")) {
            employee.setFirstName((String) employeeBody.get("firstName"));
        }
        if (employeeBody.containsKey("lastName")) {
            employee.setFirstName((String) employeeBody.get("lastName"));
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
