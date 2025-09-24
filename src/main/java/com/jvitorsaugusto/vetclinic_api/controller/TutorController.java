package com.jvitorsaugusto.vetclinic_api.controller;

import com.jvitorsaugusto.vetclinic_api.dtos.petsDtos.PetSummaryDto;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorDetailsResponseDto;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorDto;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorRegisterDtoResponse;
import com.jvitorsaugusto.vetclinic_api.dtos.tutorsDtos.TutorUpdateDtoRequest;
import com.jvitorsaugusto.vetclinic_api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/register")
    public ResponseEntity<TutorRegisterDtoResponse> tutorRegister(@RequestBody @Valid TutorDto tutorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.registrarTutor(tutorDto));
    }

    @GetMapping
    public ResponseEntity<List<TutorRegisterDtoResponse>> tutorList(){
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.listAllTutors());
    }

    @GetMapping("/tutor/{id}/pets")
    public ResponseEntity<List<PetSummaryDto>> listAllTheTutorPets(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.listAllOfTheTutorsPets(id));
    }

    @GetMapping("/tutor/{id}")
    public ResponseEntity<TutorDetailsResponseDto> tutorDetails(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.findTutorById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TutorDetailsResponseDto> updateTutor(@PathVariable(value = "id") Long id, @RequestBody TutorUpdateDtoRequest tutorUpdateDtoRequest){
        return ResponseEntity.status(HttpStatus.OK).body(tutorService.updateTutor(id, tutorUpdateDtoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable(value = "id") Long id){
        tutorService.deleteTutor(id);
        return ResponseEntity.noContent().build();
    }
}
