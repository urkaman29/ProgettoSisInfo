package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Extra.AddUsersKeycloak;
import com.sisinfo.Service.CalendarService;
import com.sisinfo.Service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

class UserRegistrationDTO {
    @NotNull(message = "Name can't be null")
    @Size(min = 4, max = 255, message = "Name must be between 4 and 255 chars")
    public String name;

    @NotNull(message = "E-mail can't be null")
    @Size(min = 8, max = 512, message = "Name must be between 8 and 512 chars")
    public String email;

    @NotNull(message = "Password can't be null")
    @Size(min = 12, max = 512, message = "Name must be between 12 and 512 chars")
    public String password;

    @NotNull(message = "Role can't be null")
    @Size(min = 2, max = 512, message = "Role must be between 2 and 512 chars")
    public String role;
}


@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final EmployeeService employeeService;

    @Autowired
    public RegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> registerEmployee(
            @Valid @RequestBody UserRegistrationDTO newUserDTO
    ) {

        if (employeeService.userExists(newUserDTO.name, newUserDTO.email)) {
            return new ResponseEntity<>(Map.of("msg", "User already exist"), HttpStatus.CONFLICT);
        }

        var keycloakManager = new AddUsersKeycloak();
        // Aggiunge l'utente a Keycloak
        var success = keycloakManager.addUser(
                newUserDTO.name,
                newUserDTO.email,
                newUserDTO.password,
                newUserDTO.role
        );

        var newUser = new Employee();

        newUser.setName(newUserDTO.name);
        newUser.setEmail(newUserDTO.email);
        //newUser.setCalendar(new);

        employeeService.createEmployee(newUser);

        return success == HttpStatus.OK
                ? new ResponseEntity<>(Map.of("msg", "User created successfully"), success)
                : new ResponseEntity<>(Map.of("msg", "User created failed"), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}