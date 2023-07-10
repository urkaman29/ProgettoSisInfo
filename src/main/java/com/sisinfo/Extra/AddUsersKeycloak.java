package com.sisinfo.Extra;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class AddUsersKeycloak {
    String usernameAdmin = "rocco";
    String passwordAdmin = "Vieh12468";
    //String role = "user";
    String serverUrl = "http://192.168.0.135:8088";
    String realm = "SecurityPneuservice";
    String clientId = "client_rest_api";


    public HttpStatus addUser(String name, String mail, String password, String role) {
        var keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .username(usernameAdmin)
                .password(passwordAdmin)
                .build();

        var user = new UserRepresentation();

        user.setEnabled(true);
        user.setUsername(name);
        user.setEmail(mail);
        user.setFirstName(name);
        user.setEmailVerified(true);

        user.setAttributes(Collections.singletonMap("origin", List.of("demo")));

        // Get realm
        var realmResource = keycloak.realm(realm);
        var usersRessource = realmResource.users();

        // Create user (requires manage-users role)
        var response = usersRessource.create(user);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);
        System.out.printf("User created with userId: %s%n", userId);

        // Define password credential
        var passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        var userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);

        // Get client
        var app1Client = realmResource.clients().findByClientId(clientId).get(0);

        // Get client level role (requires view-clients role)
        var userClientRole = realmResource.clients().get(app1Client.getId()).roles().get(role).toRepresentation();

        //var client level role to user
        userResource.roles().clientLevel(app1Client.getId()).add(Collections.singletonList(userClientRole));

        return HttpStatus.OK;
    }
}