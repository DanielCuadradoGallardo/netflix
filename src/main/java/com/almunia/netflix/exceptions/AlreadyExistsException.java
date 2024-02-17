package com.almunia.netflix.exceptions;

import com.almunia.netflix.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.Collections;

public class AlreadyExistsException extends NetflixException{
    @Serial
    private static final long serialVersionUID = -8389198789098689936L;

    public AlreadyExistsException(final String message) {
        super(HttpStatus.CONFLICT.value(), message);
    }

    public AlreadyExistsException(final String message, final ErrorDto data) {
        super(HttpStatus.CONFLICT.value(), message, Collections.singletonList(data));
    }
}
