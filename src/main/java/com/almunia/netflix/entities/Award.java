package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="awards")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Award implements Serializable {

    private static final long serialVersionUID = -674375231608295739L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "awards")
    private Set<Actor> actores;

}
