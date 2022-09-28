package com.employee.fullstackbackend.repository;

import com.employee.fullstackbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
