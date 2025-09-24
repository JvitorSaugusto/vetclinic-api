package com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetSummaryDto;
import com.jvitorsaugusto.vetclinic_api.model.TutorModel;

import java.util.List;
import java.util.stream.Collectors;

public record TutorDetailsResponseDto(

        Long id,
        String tutorName,
        String email,
        String cellPhone,
        List<PetSummaryDto> petList
) {

    public TutorDetailsResponseDto(TutorModel model) {

        this (
                model.getId(),
                model.getTutorName(),
                model.getEmail(),
                model.getCellPhone(),
                model.getPetModelList().stream()
                        .map(petModel -> new PetSummaryDto(petModel.getId(), petModel.getPetName()))
                        .collect(Collectors.toList())
        );
    }
}
