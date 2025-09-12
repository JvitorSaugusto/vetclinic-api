package com.jvitorsaugusto.vetclinic_api.repositories;

import com.jvitorsaugusto.vetclinic_api.model.SpeciesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository <SpeciesModel, Long> {
}
