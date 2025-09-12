package com.jvitorsaugusto.vetclinic_api.dtos.petsDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SpeciesDto(
        @NotBlank(message = "O nome da espécie é obrigatório")
        @Pattern(regexp = "[a-zA-Z ]+", message = "O nome da espécie não pode conter caracteres especiais ou números.")
        String nome) {
}
