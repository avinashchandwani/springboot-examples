package com.service.tracing.controller;

import com.service.tracing.entity.Employee;
import com.service.tracing.service.EmployeeService;
import com.service.tracing.util.ExceptionCode;
import com.service.tracing.util.ServiceException;
import com.service.tracing.util.TracingExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
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
        Employee schoolUser = employeeService.getEmployeeById(id);
        if (schoolUser == null) {
            throw new ServiceException(ExceptionCode.NOT_FOUND);
        }
        return schoolUser;
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void inactivateEmployee(@PathVariable(name = "id") long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
        } catch (Exception ex) {
            TracingExceptionUtil.throwAppropriateException(ex);
        }
    }
}
