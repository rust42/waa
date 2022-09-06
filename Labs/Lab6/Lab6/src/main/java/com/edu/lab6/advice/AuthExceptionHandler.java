package com.edu.lab6.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.Optional;

@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InsufficientAuthenticationException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {
        return new ResponseEntity<>(new RestError("Could not authenticate", InsufficientAuthenticationException.class.getSimpleName()), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler({UsernameNotFoundException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleUsernameNotFoundException(Exception ex) {
        return new ResponseEntity<>(new RestError("No such user exists", UsernameNotFoundException.class.getSimpleName()), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler( {AuthenticationException.class} )
    @ResponseBody
    public ResponseEntity<RestError> handleExceptions(Exception ex) {
        return ResponseEntity.of(Optional.empty());
    }



}
