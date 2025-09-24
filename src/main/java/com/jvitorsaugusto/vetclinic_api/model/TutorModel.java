package com.jvitorsaugusto.vetclinic_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tutors")
public class TutorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutor_id")
    private Long id;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "O nome completo não pode conter números.")
    @Column(nullable = false)
    private String tutorName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^\\d+([.,]\\d+)?$", message = "O celular só pode conter números.")
    @Column(nullable = false, unique = true)
    private String cellPhone;

    @OneToMany(mappedBy = "tutorModel", fetch = FetchType.LAZY)
    List<PetModel> petModelList = new ArrayList<>();
}
