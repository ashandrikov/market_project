package com.shandrikov.market.market_project.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public static String getUsernameOfAuthenticatedUser (HttpServletRequest request){
        Object principal = request.getUserPrincipal();
        if (principal == null) return null;
        String username = null;

        if (principal instanceof UsernamePasswordAuthenticationToken){
            username = request.getUserPrincipal().getName();
        }

        return username;
    }
}
