package com.demo.employees.repository;

import com.demo.employees.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e where (:firstName = null OR e.firstName = :firstName) AND (:lastName = null OR e.lastName = :lastName)")
    List<Employee> findAll(@Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName);

    @Query("select e from Employee e where e.employeeId=:employeeId")
    Employee findById(@Param(value = "employeeId") long employeeId);
}