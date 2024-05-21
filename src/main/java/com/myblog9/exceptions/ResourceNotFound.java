package com.myblog9.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String msg)
    {

        super(msg);//super keyword will automaticaly display msg in postman rsponse.
    }
}
