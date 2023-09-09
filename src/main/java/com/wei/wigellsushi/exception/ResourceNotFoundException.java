package com.wei.wigellsushi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L; //versioncontroll
    private String name;
    private String field;
    private Object value;

    public ResourceNotFoundException(String name, String field,Object value) {
        super(String.format("%s not found with %s : '%s'", name, field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }


    public String getName() {return name;}

    public String getField() {return field;}

    public Object getValue() {return value;}
    }
