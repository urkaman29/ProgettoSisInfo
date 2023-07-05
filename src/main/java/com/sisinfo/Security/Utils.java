package com.sisinfo.Security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;



import java.util.HashMap;
import java.util.Map;



/*Questa classe serve per estrapolare i dati dal token in maniera pulita e semplice */



@UtilityClass
@Log4j2
public class Utils {



    public Jwt getPrincipal() {
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



    public String getAuthServerId(){
        return getPrincipal().getClaims().get("sid").toString();
    }



    public String getName() {
        return getPrincipal().getClaims().get("preferred_username").toString();
    }



    public String getCognome(){return  getPrincipal().getClaims().get("family_name").toString();}



    public String getEmail() {
        return  getPrincipal().getClaims().get("email").toString();
    }



}
