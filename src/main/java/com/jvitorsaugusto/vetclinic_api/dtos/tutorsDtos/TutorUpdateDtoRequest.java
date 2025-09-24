package com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos;

import com.jvitorsaugusto.vetclinic_api.model.TutorModel;

public record TutorUpdateDtoRequest(

        String tutorName,
        String email,
        String cellPhone
) {

    public TutorUpdateDtoRequest(TutorModel model) {

        this (
                model.getTutorName(),
                model.getEmail(),
                model.getCellPhone()
        );
    }
}
