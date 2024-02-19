package com.almunia.netflix.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NetflixResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3844050829851426054L;

    private int statusCode;
    private String code;
    private String message;
    private T data;
}
