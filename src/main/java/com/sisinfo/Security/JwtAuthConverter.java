package com.sisinfo.Security;

import net.bytebuddy.asm.Advice;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static String CLIENT_NAME = "client_rest_api";

    @Override
    public AbstractAuthenticationToken convert(final Jwt source) {
        Map<String, Map<String, ArrayList<String>>> resourceAccess = source.getClaim("resource_access");

        var authorities = resourceAccess
                .get(CLIENT_NAME)
                .get("roles").stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toCollection(HashSet::new));


        return new JwtAuthenticationToken(source, authorities);
    }
}