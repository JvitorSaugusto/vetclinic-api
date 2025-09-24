package com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetSummaryDto;
import com.jvitorsaugusto.vetclinic_api.model.TutorModel;

import java.util.List;
import java.util.stream.Collectors;

public record TutorDetailsRequestDto(

        Long id,
        String tutorName,
        String email,
        String cellPhone,
        List<PetSummaryDto> petList
) {

}
