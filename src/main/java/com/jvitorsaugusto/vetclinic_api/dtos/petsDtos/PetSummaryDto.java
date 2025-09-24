package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import com.jvitorsaugusto.vetclinic_api.model.PetModel;

public record PetSummaryDto(Long id, String nome) {

    public PetSummaryDto (PetModel model){

        this(
                model.getId(),
                model.getPetName()
        );
    }
}
