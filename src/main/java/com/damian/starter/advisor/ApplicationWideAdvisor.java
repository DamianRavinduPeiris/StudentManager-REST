package com.damian.starter.advisor;

import com.damian.starter.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationWideAdvisor {
    @Autowired
    private Response response;
    @ExceptionHandler({Exception.class})
    public Response catchAndThrowExceptions(Exception e){
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Server threw an exception : "+e.getLocalizedMessage());
        response.setData(null);
        return response;

    }
}
