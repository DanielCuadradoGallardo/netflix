package com.almunia.netflix.exceptions;

import com.almunia.netflix.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;

public class InternalServerErrorException extends NetflixException implements Serializable{
    @Serial
    private static final long serialVersionUID = -4555393160745467353L;

    public InternalServerErrorException(final String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerErrorException(final String message, final ErrorDto data) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Collections.singletonList(data));
    }
}
