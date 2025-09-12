package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import com.jvitorsaugusto.vetclinic_api.model.SpeciesModel;
import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;
import jakarta.validation.constraints.*;

public record PetDto(

        @NotBlank(message = "O nome do pet é obrigatório")
        @Pattern(regexp = "[a-zA-Z ]+", message = "O nome completo não pode conter caracteres especiais ou números.")
        String petName,

        @NotBlank(message = "O endereço é obrigatório")
        String address,

        @Pattern(regexp = "^\\d+([.,]\\d+)?$", message = "O idade só pode conter números.")
        @Max(value = 20, message = "A idade deve ser no máximo 20")
        int age,

        @Pattern(regexp = "^\\d+([.,]\\d+)?$", message = "O peso só pode conter números.")
        @DecimalMin(value = "0.5", message = "O peso deve ser no mínimo 0.5kg.")
        @DecimalMax(value = "60.0", message = "O peso não pode exceder 60kg.")
        double weight,

        @NotNull
        String race,

        @NotNull
        SpeciesModel typeOfPet,

        @NotNull
        Gender gender) {
}
