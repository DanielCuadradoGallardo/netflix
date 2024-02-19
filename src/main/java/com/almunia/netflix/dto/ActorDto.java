package com.almunia.netflix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2319110079797093034L;

    private int id;
    private String name;
    private LocalDate birth_date;
    private String birth_place;
    private String biography;
}
