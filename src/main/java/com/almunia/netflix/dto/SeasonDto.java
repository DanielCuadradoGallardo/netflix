package com.almunia.netflix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 340145897383734853L;

    private int id;
    private String title;
    private String description;
    private SerieDto serie;
}
