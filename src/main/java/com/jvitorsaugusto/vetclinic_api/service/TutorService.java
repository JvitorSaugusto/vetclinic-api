package com.jvitorsaugusto.vetclinic_api.service;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetSummaryDto;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorDetailsResponseDto;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorDto;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorUpdateDtoRequest;
import com.jvitorsaugusto.vetclinic_api.model.TutorModel;
import com.jvitorsaugusto.vetclinic_api.repositories.TutorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<PetSummaryDto> listAllOfTheTutorsPets(Long id){
        TutorModel tutorModel = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor com id: " + id + " não encontrado"));

        return tutorModel.getPetModelList().stream().map(PetSummaryDto::new).collect(Collectors.toList());
    }

    public List<TutorRegisterDtoResponse> listAllTutors(){
        return tutorRepository.findAll()
                .stream()
                .map(TutorRegisterDtoResponse::new)
                .collect(Collectors.toList());
    }

    public TutorDetailsResponseDto findTutorById(Long id) {
        TutorModel tutorModel = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor com id: " + id + " não encontrado"));
        return new TutorDetailsResponseDto(tutorModel);
    }


    public TutorDetailsResponseDto updateTutor(Long id, TutorUpdateDtoRequest tutorNovo) {
        TutorModel tutorModel = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor com id: " + id + " não encontrado"));

        tutorModel.setTutorName(tutorNovo.tutorName());
        tutorModel.setEmail(tutorNovo.email());
        tutorModel.setCellPhone(tutorNovo.cellPhone());

        tutorRepository.save(tutorModel);

        return new TutorDetailsResponseDto(tutorModel);
    }

    public void deleteTutor(Long id) {
        if (!tutorRepository.existsById(id)){
            throw new RuntimeException("Tutor com id: " + id + " não encontrado");
        }
        tutorRepository.deleteById(id);
    }

    public TutorRegisterDtoResponse registrarTutor(TutorDto tutorDto) {

        TutorModel novoTutor = TutorModel
                .builder()
                .tutorName(tutorDto.tutorName())
                .email(tutorDto.email())
                .cellPhone(tutorDto.telefone())
                .build();
        TutorModel tutorSave = tutorRepository.save(novoTutor);
        return new TutorRegisterDtoResponse(tutorSave);
    }
}
