package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;
import com.jvitorsaugusto.vetclinic_api.model.enums.Species;

import java.math.BigDecimal;

public record PetDetailsResponseDto(

        Long id,
        String petName,
        String address,
        int age,
        BigDecimal weight,
        String race,
        Species specie,
        Gender genderOfPet,
        TutorRegisterDtoResponse tutor
) {

    public PetDetailsResponseDto(PetModel model){
        this(
                model.getId(),
                model.getPetName(),
                model.getAddress(),
                model.getAge(),
                model.getWeight(),
                model.getRace(),
                model.getTypeOfPet(),
                model.getGenderOfPet(),
                new TutorRegisterDtoResponse(model.getTutorModel().getId(), model.getTutorModel().getTutorName())
        );
    }
}
