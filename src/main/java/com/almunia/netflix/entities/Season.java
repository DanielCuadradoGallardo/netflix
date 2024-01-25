package com.almunia.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="seasons")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season implements Serializable {


    private static final long serialVersionUID = 6534272576890887232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="serie_id", referencedColumnName = "id")
    private Serie serie;

    @OneToMany(mappedBy = "season")
    private Set<Chapter> chapters;
}
