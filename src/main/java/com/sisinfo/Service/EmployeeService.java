package com.sisinfo.Service;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee getEmployeeDTO(Long id){
        Employee employee= getEmployeeById(id);
        Employee dto= new Employee();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setTelephone(employee.getTelephone());
        return dto;
    }

    public Employee getEmployeeByName(String name) {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return getEmployeeDTO(employee.getId());
            }
        }

        throw new IllegalArgumentException("Employee not found");
    }

    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    public boolean existsByTelephone(long telephone) {
        return employeeRepository.existsByTelephone(telephone);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setTelephone(employee.getTelephone());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setBaseSalary(employee.getBaseSalary());
        existingEmployee.setWorkedHours(employee.getWorkedHours());
        existingEmployee.setVacationHours(employee.getVacationHours());
        existingEmployee.setPermissionHours(employee.getPermissionHours());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
