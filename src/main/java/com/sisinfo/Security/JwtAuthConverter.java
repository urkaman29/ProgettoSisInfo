package prova.authentication;



import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;



import java.util.*;



@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {



    private static String CLIENT_NAME = "client_rest_api";



    @Override
    @SuppressWarnings("unchecked")
    public AbstractAuthenticationToken convert(final Jwt source) {
        Map<String, Object> resourceAccess = source.getClaim("resource_access");
        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(CLIENT_NAME);
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role : resourceRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        return new JwtAuthenticationToken(source,authorities);
    }
}