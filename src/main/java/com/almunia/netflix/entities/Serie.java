package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="series")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Serie implements Serializable {

    @Serial
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
    private List<Category> categories;

    @OneToMany(mappedBy = "serie")
    private List<Season> seasons;
}
