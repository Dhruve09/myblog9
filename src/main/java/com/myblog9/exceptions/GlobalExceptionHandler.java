package com.myblog9.exceptions;

import com.myblog9.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice //By using this annonation we r telling springboot if any od the exception occurs in the project, take that exception and give it to this class
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler //By adding  ResponseEntityExceptionHandler this becomes an custom class to handle exception
{
@ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFound exception,   //this is like catch block (Exception e), whn an exception occurs the exceptions obj adsress wil automaticaly comes to the (ResourceNotFound exception,
                                                                        WebRequest webRequest){       //WebRequst has a lot of information abour exceptions

    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorDetails> handleResourceNotFoundException(Exception exception,   //this is like catch block (Exception e), whn an exception occurs the exceptions obj adsress wil automaticaly comes to the (ResourceNotFound exception,
                                                                    WebRequest webRequest){       //WebRequst has a lot of information abour exceptions

    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
}


}
