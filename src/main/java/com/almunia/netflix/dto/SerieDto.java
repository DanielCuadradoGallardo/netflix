package com.almunia.netflix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SerieDto implements Serializable {
    private static final long serialVersionUID = -9113398478880389649L;

    private int id;
    private String name;
    private String description;
    private int recommended_age;
    private Set<CategoryDto> categories;
    //private Set<SeasonDto> seasons;
}
