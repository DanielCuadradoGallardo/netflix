package com.almunia.netflix.exceptions;


import com.almunia.netflix.dto.ErrorDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class NetflixRestExceptionHandler {

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public NetflixResponse<String> unhandledErrors(HttpServletRequest req, Exception ex) {
        return new NetflixResponse<>(500, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ExceptionConstants.ERROR, ex.getMessage());
    }

    @ExceptionHandler({ NetflixException.class })
    @ResponseBody
    public NetflixResponse<List<ErrorDto>> handleLmException(final HttpServletRequest request, final HttpServletResponse response,
                                        final NetflixException ex) {
        response.setStatus(ex.getCode());
        return new NetflixResponse<>(ex.getCode(), HttpStatus.valueOf(ex.getCode()).toString(), ex.getMessage(),ex.getErrorList());
    }
}
