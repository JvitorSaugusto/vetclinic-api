package com.jvitorsaugusto.vetclinic_api.dtos.authDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(

        @NotBlank(message = "O nome do tutor é obrigatório")
        @Pattern(regexp = "[a-zA-Z ]+", message = "O nome completo não pode conter caracteres especiais ou números.")
        String username,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        String password,

        @Pattern(regexp = "^\\d+([.,]\\d+)?$", message = "O telefone só pode conter números.")
        @NotBlank(message = "O telefone é obrigatório.")
        String cellPhone) {
}
