package com.sisinfo.Service;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
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

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
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
