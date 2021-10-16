package com.demo.employees.controller;

import com.demo.employees.entity.Employee;
import com.demo.employees.service.EmployeeService;
import com.demo.employees.util.ExceptionCode;
import com.demo.employees.util.ServiceException;
import com.demo.employees.util.TracingExceptionUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@Tag(name = "employees", description = "the employees API with documentation annotations")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Employee> getAllEmployees(@Parameter(name = "where", required = false) String where) {
        return employeeService.getAllEmployees(where);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee createEmployee(@RequestBody Map requestMap) {
        try {
            return employeeService.createEmployee(requestMap);
        } catch (Exception ex) {
            TracingExceptionUtil.throwAppropriateException(ex);
            return null;
        }
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee getEmployeeById(@PathVariable(name = "id") long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new ServiceException(ExceptionCode.NOT_FOUND);
        }
        return employee;
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee updatePutEmployee(@PathVariable(name = "id") long id, @RequestBody Map requestMap) {
        Employee employee = employeeService.updatePutEmployee(requestMap, id);
        if (employee == null) {
            throw new ServiceException(ExceptionCode.NOT_FOUND);
        }
        return employee;
    }

    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee updatePatchEmployee(@PathVariable(name = "id") long id, @RequestBody Map requestMap) {
        Employee employee = employeeService.updatePatchEmployee(requestMap, id);
        if (employee == null) {
            throw new ServiceException(ExceptionCode.NOT_FOUND);
        }
        return employee;
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void inactivateEmployee(@PathVariable(name = "id") long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getResponseCode(), ex.getMessage());
        }
    }
}
