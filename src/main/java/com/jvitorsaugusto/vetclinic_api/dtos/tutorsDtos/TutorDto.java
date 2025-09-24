package com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record TutorDto(
        @NotBlank(message = "O nome do tutor é obrigatório")
        @Pattern(regexp = "^[\\p{L} ]+$", message = "O nome completo não pode conter caracteres especiais ou números.")
        String tutorName,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Por favor, insira um formato de e-mail válido.")
        String email,

        @Pattern(regexp = "^\\d+([.,]\\d+)?$", message = "O telefone só pode conter números.")
        @NotBlank(message = "O telefone é obrigatório.")
        String telefone,

        @Valid
        List<PetDto> petDtoList
){
}
