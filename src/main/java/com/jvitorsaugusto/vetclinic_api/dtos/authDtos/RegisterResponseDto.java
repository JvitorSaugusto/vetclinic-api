package com.jvitorsaugusto.vetclinic_api.dtos.authDtos;

import com.jvitorsaugusto.vetclinic_api.model.UserModel;

public record RegisterResponseDto(String username, String email) {

    public RegisterResponseDto (UserModel model){

        this(
                model.getUserName(),
                model.getEmail()
        );
    }
}
