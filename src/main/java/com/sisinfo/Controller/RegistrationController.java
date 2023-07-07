package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Extra.addUsersKeycloak;
import org.hibernate.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
@CrossOrigin("*")
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @PostMapping("/new")
    public ResponseEntity<String> registerEmployee(@RequestParam (name = "name") String name,
                                                   @RequestParam (name= "email") String email,
                                                   @RequestParam (name= "password") String password,
                                                   @RequestParam(name = "role") String role){
        //String password = generateRandomPassword();
        addUsersKeycloak keycloakManager = new addUsersKeycloak();
        keycloakManager.addUser(name, email, password, role); // Aggiunge l'utente a Keycloak
        return new ResponseEntity<>("L'utente è stato registrato con successo!", HttpStatus.OK);
    }

    /*private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        Integer lenght = 5;

        for (int i = 0; i < lenght; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }*/
}