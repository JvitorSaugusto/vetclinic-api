package com.jvitorsaugusto.vetclinic_api.repositories;

import com.jvitorsaugusto.vetclinic_api.model.TutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository <TutorModel, Long>{
}
