package com.almunia.netflix.exceptions;

import com.almunia.netflix.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;

public class NotFoundException extends NetflixException implements Serializable {
    @Serial
    private static final long serialVersionUID = -3331516709483089675L;

    public NotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException(final String message, final ErrorDto data) {
        super(HttpStatus.NOT_FOUND.value(), message, Collections.singletonList(data));
    }
}
