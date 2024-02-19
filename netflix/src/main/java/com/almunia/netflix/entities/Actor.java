package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="actors")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Actor implements Serializable {

    @Serial
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
    private List<Award> awards;

    @ManyToMany(mappedBy = "actors")
    private List<Chapter> chapters;
}
