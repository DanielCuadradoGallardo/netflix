package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="chapters")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Chapter implements Serializable {


    private static final long serialVersionUID = 1168360098449385600L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="duration")
    private int duration;

    @Column(name="release_date")
    private LocalDate release_date;

    @ManyToOne
    @JoinColumn(name="season_id", referencedColumnName = "id")
    private Season season;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="chapters_actors",
            joinColumns=@JoinColumn(name="chapter_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    private List<Actor> actors;
}
