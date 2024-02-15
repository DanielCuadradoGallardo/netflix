package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="awards")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Award implements Serializable {

    private static final long serialVersionUID = -674375231608295739L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "awards")
    private List<Actor> actores;
}
