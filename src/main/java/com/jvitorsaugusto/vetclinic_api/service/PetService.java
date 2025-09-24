package com.jvitorsaugusto.vetclinic_api.service;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetUpdateDtoRequest;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDetailsResponseDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.model.TutorModel;
import com.jvitorsaugusto.vetclinic_api.repositories.PetRepository;
import com.jvitorsaugusto.vetclinic_api.repositories.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public PetService(PetRepository petRepository, TutorRepository tutorRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
    }

    public List<PetRegisterDtoResponse> listAllPets(){
        return petRepository.findAll()
                .stream()
                .map(PetRegisterDtoResponse::new)
                .collect(Collectors.toList());
    }

    public PetDetailsResponseDto findPetById(Long id) {
        PetModel petModel = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet com id: " + id + " não encontrado"));
        return new PetDetailsResponseDto(petModel);
    }


    public PetDetailsResponseDto updatePet(Long id, PetUpdateDtoRequest petNovo) {
        PetModel petModel = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet com id: " + id + " não encontrado"));

        petModel.setPetName(petNovo.petName());
        petModel.setAddress(petNovo.address());
        petModel.setAge(petNovo.age());
        petModel.setWeight(petNovo.weight());
        petModel.setRace(petNovo.race());

        petRepository.save(petModel);

        return new PetDetailsResponseDto(petModel);
    }

    public void deletePet(Long id) {
        if (!petRepository.existsById(id)){
            throw new RuntimeException("Pet com id: " + id + " não encontrado");
        }
        petRepository.deleteById(id);
    }

    public PetRegisterDtoResponse registrarPet(PetDto petDto) {
        TutorModel tutorModel = tutorRepository.findById(petDto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor com id: " + petDto.tutorId() + " não encontrado"));

        PetModel novoPet = PetModel
                .builder()
                .petName(petDto.petName())
                .address(petDto.address())
                .age(petDto.age())
                .weight(petDto.weight())
                .race(petDto.race())
                .typeOfPet(petDto.typeOfPet())
                .genderOfPet(petDto.gender())
                .tutorModel(tutorModel)
                .build();

        tutorModel.getPetModelList().add(novoPet);
        PetModel petSave = petRepository.save(novoPet);
        return new PetRegisterDtoResponse(petSave);
    }
}
