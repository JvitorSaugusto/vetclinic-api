package com.jvitorsaugusto.vetclinic_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "typeOfPet")
public class SpeciesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
}
