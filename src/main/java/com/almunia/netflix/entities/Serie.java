package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="series")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serie implements Serializable {

    private static final long serialVersionUID = 8836005559441603468L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="recomended_age")
    private int recommended_age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="series_categories",
            joinColumns=@JoinColumn(name="serie_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "serie")
    private Set<Season> seasons;
}
