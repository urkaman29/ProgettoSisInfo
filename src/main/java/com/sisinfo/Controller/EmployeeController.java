package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeDTO(id);
    }

    @GetMapping("/name/{name}")
    public Employee getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        // Check if an employee with the same email, name, or telephone already exists
        if (employeeService.existsByEmail(employee.getEmail()) ||
                employeeService.existsByTelephone(employee.getTelephone())) {
            throw new IllegalArgumentException("An employee with the same email, name, or telephone already exists.");
        }

        return employeeService.createEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
