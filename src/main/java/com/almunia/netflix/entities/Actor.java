package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="actors")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor implements Serializable {

    private static final long serialVersionUID = 3409398110703416421L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="birth_date")
    private LocalDate birth_date;

    @Column(name="birth_place")
    private String birth_place;

    @Column(name="biography")
    private String biography;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="actors_awards",
            joinColumns=@JoinColumn(name="actor_id"),
            inverseJoinColumns = @JoinColumn(name="award_id")
    )
    private Set<Award> awards;

    @ManyToMany(mappedBy = "actors")
    private Set<Chapter> chapters;
}
