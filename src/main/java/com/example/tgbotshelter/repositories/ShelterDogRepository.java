package com.example.tgbotshelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tgbotshelter.model.Dog;

@Repository
public interface ShelterDogRepository extends JpaRepository<Dog, Long> {
}