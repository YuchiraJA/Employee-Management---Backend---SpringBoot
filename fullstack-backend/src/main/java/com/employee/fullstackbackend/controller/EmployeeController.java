package com.employee.fullstackbackend.controller;

import com.employee.fullstackbackend.exception.UserNotFoundException;
import com.employee.fullstackbackend.model.Employee;
import com.employee.fullstackbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//"@CrosOrigin" annotation use to connect spring boot(backend) with frontend(react)
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

    //Fot injecting we use auto wired annotaion
    @Autowired
    private EmployeeRepository employeeRepository;

    //For Posting the data we use post mapping annotaion
    @PostMapping("/employee")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employees")
    List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
//    When we using specific employee with id we have to use annotaion "@PathVariable"
    Employee getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id)
//                if somebody type invaild id that not in db and should display error messages. for that we write following codes.
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/employee/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee,@PathVariable Long id){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setAddress(newEmployee.getAddress());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setPnumber(newEmployee.getPnumber());
                    return employeeRepository.save(employee);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/employee/{id}")
    String deleteEmployee(@PathVariable Long id){
        if(!employeeRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        employeeRepository.deleteById(id);
        return "Employee with id "+id+" has been deleted success.";
    }

}
