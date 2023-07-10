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


    @PreAuthorize("hasRole('titolare')")
    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
    @PreAuthorize("hasRole('titolare')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PreAuthorize("hasRole('titolare')")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeDTO(id);
    }

    @PreAuthorize("hasRole('titolare')")
    @GetMapping("/{identifier}")
    public Employee getEmployeeByIdentifier(@PathVariable String identifier) {
        // Se l'identificatore è un numero, assume che sia un ID
        if (identifier.matches("\\d+"))
            return employeeService.getEmployeeById(Long.parseLong(identifier));

        // Se l'identificatore contiene una @, assume che sia un'email
        if (identifier.contains("@")) return employeeService.getEmployeeByEmail(identifier);

        // Altrimenti, assume che sia il nome
        return employeeService.getEmployeeByName(identifier);
    }

    @GetMapping("/{employeeId}/worked-hours")
    public int getBaseSalary(@PathVariable Long employeeId) {
        return employeeService.getBaseSalary(employeeId);
    }


    @PostMapping
    @PreAuthorize("hasRole('titolare')")
    public Employee createEmployee(@RequestBody Employee employee) {
        // Check if an employee with the same email, name, or telephone already exists
        if (employeeService.existsByEmail(employee.getEmail()) ||
                employeeService.existsByTelephone(employee.getTelephone()))
            throw new IllegalArgumentException("An employee with the same email, name, or telephone already exists.");

        return employeeService.createEmployee(employee);
    }


    @PutMapping
    public Employee updateSalaryEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployeeSalary(employee);
    }


    @PreAuthorize("hasRole('titolare')")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
