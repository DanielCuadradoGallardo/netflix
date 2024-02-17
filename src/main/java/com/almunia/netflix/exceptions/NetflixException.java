package com.almunia.netflix.exceptions;


import com.almunia.netflix.dto.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class NetflixException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 6094991641346150808L;

    private final int code;

    private final List<ErrorDto> errorList = new ArrayList<>();

    public NetflixException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public NetflixException(final int code, final String message, final List<ErrorDto> errorList) {
        super(message);
        this.code = code;
        this.errorList.addAll(errorList);
    }
}