package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import java.math.BigDecimal;

public record PetUpdateDtoRequest(

        String petName,
        String address,
        int age,
        BigDecimal weight,
        String race
) {

}
