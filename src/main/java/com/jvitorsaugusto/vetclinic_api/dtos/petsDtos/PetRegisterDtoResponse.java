package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import com.jvitorsaugusto.vetclinic_api.model.PetModel;

public record PetRegisterDtoResponse(
        Long id,
        String petName,
        String specieName
) {

    public PetRegisterDtoResponse (PetModel model){
        this(
                model.getId(),
                model.getPetName(),
                model.getTypeOfPet() != null ? model.getTypeOfPet().getNome() : "Não especificado"
        );

    }
}
