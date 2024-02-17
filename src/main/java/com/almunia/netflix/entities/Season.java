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
@Table(name="seasons")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Season implements Serializable {

    @Serial
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

    @OneToMany(mappedBy = "season", cascade = CascadeType.REMOVE)
    private List<Chapter> chapters;
}
