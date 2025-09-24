package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;


import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;
import com.jvitorsaugusto.vetclinic_api.model.enums.Species;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PetDto(

        @NotBlank(message = "O nome do pet é obrigatório")
        @Pattern(regexp = "^[\\p{L} ]+$", message = "O nome completo não pode conter caracteres especiais ou números.")
        String petName,

        @NotBlank(message = "O endereço é obrigatório")
        String address,

        @Max(value = 20, message = "A idade deve ser no máximo 20")
        int age,

        @DecimalMin(value = "0.5", message = "O peso deve ser no mínimo 0.5kg.")
        @DecimalMax(value = "60.0", message = "O peso não pode exceder 60kg.")
        BigDecimal weight,

        @NotNull
        String race,

        @NotNull
        Species typeOfPet,

        @NotNull
        Gender gender,

        @NotNull
        Long tutorId
) {
}
