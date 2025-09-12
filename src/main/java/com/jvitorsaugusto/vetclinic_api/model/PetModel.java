package com.jvitorsaugusto.vetclinic_api.model;

import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
@EntityListeners(AuditingEntityListener.class)
public class PetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String petName;
    private String address;
    private int age;
    private double weight;
    private String race;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "typeOfPet", nullable = false)
    private SpeciesModel typeOfPet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender genderOfPet;
}
