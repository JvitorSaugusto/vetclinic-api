package com.jvitorsaugusto.vetclinic_api.controller;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDetailsDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetDto;
import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/register")
    public ResponseEntity<PetRegisterDtoResponse> criarPet(@RequestBody @Valid PetDto petDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.registrarPet(petDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PetDetailsDto> updatePet(@PathVariable(value = "id") Long id, @RequestBody PetDetailsDto petDetailsDto){
        return ResponseEntity.status(HttpStatus.OK).body(petService.updatePet(id, petDetailsDto));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<PetDetailsDto> detailsPet(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(petService.findPetById(id));
    }

    @GetMapping
    public ResponseEntity<List<PetRegisterDtoResponse>> listAllPets(){
        return ResponseEntity.status(HttpStatus.OK).body(petService.listAllPets());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable(value = "id") Long id){
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
