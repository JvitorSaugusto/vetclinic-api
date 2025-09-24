package com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos;

import com.jvitorsaugusto.vetclinic_api.model.TutorModel;

public record TutorRegisterDtoResponse(

        Long id,
        String tutorName
){

    public TutorRegisterDtoResponse (TutorModel model){

        this(
                model.getId(),
                model.getTutorName()
        );
    }
}
