package com.jvitorsaugusto.vetclinic_api.model;

import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;
import com.jvitorsaugusto.vetclinic_api.model.enums.Species;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;


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
    @Column(name = "pet_id")
    private Long id;

    private String petName;
    private String address;
    private int age;
    private BigDecimal weight;
    private String race;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updateDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Species typeOfPet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender genderOfPet;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private TutorModel tutorModel;
}
