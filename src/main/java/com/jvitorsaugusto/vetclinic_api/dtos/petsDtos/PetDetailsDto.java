package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;

public record PetDetailsDto(

        Long id,
        String petName,
        String address,
        int age,
        double weight,
        String race,
        String specieName,
        Gender genderOfPet
) {

    public PetDetailsDto (PetModel model){
        this(
                model.getId(),
                model.getPetName(),
                model.getAddress(),
                model.getAge(),
                model.getWeight(),
                model.getRace(),
                model.getTypeOfPet() != null ? model.getTypeOfPet().getNome() : "Não especificado",
                model.getGenderOfPet()
        );
    }
}
