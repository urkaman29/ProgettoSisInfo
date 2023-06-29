package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PreAuthorize("hasRole('client admin')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PreAuthorize ("hasRole('client admin')")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeDTO(id);
    }

    @PreAuthorize ("hasRole('client admin')")
    @GetMapping("/name/{name}")
    public Employee getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @PreAuthorize ("hasRole('client admin')")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        // Check if an employee with the same email, name, or telephone already exists
        if (employeeService.existsByEmail(employee.getEmail()) ||
                employeeService.existsByTelephone(employee.getTelephone())) {
            throw new IllegalArgumentException("An employee with the same email, name, or telephone already exists.");
        }

        return employeeService.createEmployee(employee);
    }

    @PreAuthorize ("hasRole('client admin')")
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PreAuthorize ("hasRole('client admin')")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
