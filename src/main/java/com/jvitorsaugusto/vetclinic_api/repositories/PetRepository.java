package com.jvitorsaugusto.vetclinic_api.repositories;

import com.jvitorsaugusto.vetclinic_api.model.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository <PetModel, Long>{
}
