package com.sisinfo.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
            new JwtGrantedAuthoritiesConverter();

    @Value("${server.jwt.auth.converter.principle-attribute}")
    private String principleAttribute;
    @Value("${server.jwt.auth.converter.resource-id}")
    private String resourceId;

    @Override//Usiamo questa classe per far si che avvenga la conversione del nostro token del ruolo
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(
                jwt,
                authorities,
                getPrincipleClaimName(jwt)
        );
    }
    //Con questa classe andrò a prendermi (sempre dal Token) il preferredUsername dell'utente in questione
    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null) {
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }

    // Creiamo una collezione dei nostri vari ruoli da dover convertire
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        //Di seguito faccio dei controlli
        //Prima di tutto controllo se il resource access del Token non sia nullo
        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        }
        //Successivamente controllo che abbia il ruolo adatto
        resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess.get(resourceId) == null) {
            return Set.of();
        }

        //In caso abbia superato tutti i controlli assegno alla variabile il tipo di accesso che ha il ruolo
        resource = (Map<String, Object>) resourceAccess.get(resourceId);

        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}

