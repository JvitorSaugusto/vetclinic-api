package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.model.enums.Species;

public record PetRegisterDtoResponse(
        Long id,
        String petName,
        Species specie,
        TutorRegisterDtoResponse tutor

) {

    public PetRegisterDtoResponse (PetModel model){
        this(
                model.getId(),
                model.getPetName(),
                model.getTypeOfPet(),
                new TutorRegisterDtoResponse(model.getTutorModel().getId(), model.getTutorModel().getTutorName())
        );

    }
}
