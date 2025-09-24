package com.jvitorsaugusto.vetclinic_api.service;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDetailsResponseDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetUpdateDtoRequest;
import com.jvitorsaugusto.vetclinic_api.exceptions.NotFoundResource;
import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.model.TutorModel;
import com.jvitorsaugusto.vetclinic_api.model.enums.Gender;
import com.jvitorsaugusto.vetclinic_api.model.enums.Species;
import com.jvitorsaugusto.vetclinic_api.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void mustUpdateAPetSuccessfully() {
        TutorModel tutor = criarTutorDeTeste();
        PetModel existingPet = criarPetDeTeste(2L, "mel", tutor);
        PetUpdateDtoRequest updateDto = new PetUpdateDtoRequest("lili", "endereco novo", 4, 5.4, "teste raca 2");

        when(petRepository.findById(2L)).thenReturn(Optional.ofNullable(existingPet));
        when(petRepository.save(any(PetModel.class))).thenReturn(existingPet);

        PetDetailsResponseDto resultadoDto = petService.updatePet(2L, updateDto);

        assertThat(existingPet).isNotNull();
        assertThat(resultadoDto).isNotNull();
        assertThat(resultadoDto.petName()).isEqualTo("lili");
        assertThat(resultadoDto.id()).isEqualTo(2L);

        verify(petRepository, times(1)).findById(2L);
        verify(petRepository, times(1)).save(existingPet);
    }

    @Test
    void shouldNotUpdateAPetSuccessfully() {
        TutorModel tutor = criarTutorDeTeste();
        PetUpdateDtoRequest updateDto = new PetUpdateDtoRequest("pipoca", "endereco novo", 4, 5.4, "teste raca 2");

        when(petRepository.findById(88L)).thenReturn(Optional.empty());

        assertThrows(NotFoundResource.class, () -> {
            petService.updatePet(88L, updateDto);
        });

        verify(petRepository, never()).save(any(PetModel.class));
    }

    private PetModel criarPetDeTeste(Long id, String name,TutorModel tutor) {
        return PetModel.builder()
                .id(id)
                .petName(name)
                .address("teste endereco")
                .age(12)
                .weight(20)
                .race("Salsciha")
                .typeOfPet(Species.DOG)
                .genderOfPet(Gender.MACHO)
                .tutorModel(tutor)
                .build();
    }

    private TutorModel criarTutorDeTeste() {
        return TutorModel.builder()
                .id(1L)
                .tutorName("João Vitor Augusto")
                .email("joao.vitor@teste.com")
                .cellPhone("24998180935")
                .petModelList(new ArrayList<>())
                .build();
    }
}