package com.jvitorsaugusto.vetclinic_api.service;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDetailsDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import com.jvitorsaugusto.vetclinic_api.repositories.PetRepository;
import com.jvitorsaugusto.vetclinic_api.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository, SpeciesRepository speciesRepository) {
        this.petRepository = petRepository;
    }

    public List<PetRegisterDtoResponse> listAllPets(){
        return petRepository.findAll()
                .stream()
                .map(PetRegisterDtoResponse::new)
                .collect(Collectors.toList());
    }

    public PetDetailsDto findPetById(Long id) {
        PetModel petModel = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet com id: " + id + " não encontrado"));
        return new PetDetailsDto(petModel);
    }


    public PetDetailsDto updatePet(Long id, PetDetailsDto petNovo) {
        PetModel petModel = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet com id: " + id + " não encontrado"));

        petModel.setPetName(petNovo.petName());
        petModel.setAddress(petNovo.petName());
        petModel.setAge(petNovo.age());
        petModel.setWeight(petNovo.weight());
        petModel.setRace(petNovo.race());

        petRepository.save(petModel);

        return new PetDetailsDto(petModel);
    }

    public void deletePet(Long id) {
        if (!petRepository.existsById(id)){
            throw new RuntimeException("Pet com id: " + id + " não encontrado");
        }
        petRepository.deleteById(id);
    }

    public PetRegisterDtoResponse registrarPet(PetDto petDto) {

        PetModel novoPet = PetModel
                .builder()
                .petName(petDto.petName())
                .address(petDto.address())
                .age(petDto.age())
                .weight(petDto.weight())
                .race(petDto.race())
                .typeOfPet(petDto.typeOfPet())
                .genderOfPet(petDto.gender())
                .build();
        PetModel petSave = petRepository.save(novoPet);
        return new PetRegisterDtoResponse(petSave);
    }
}
