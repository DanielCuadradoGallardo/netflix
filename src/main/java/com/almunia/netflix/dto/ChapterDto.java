package com.almunia.netflix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDto implements Serializable {


    private static final long serialVersionUID = -3858655192913199595L;

    private int id;
    private String title;
    private String description;
    private int duration;
    private LocalDate release_date;
    private List<ActorDto> actors;
}
