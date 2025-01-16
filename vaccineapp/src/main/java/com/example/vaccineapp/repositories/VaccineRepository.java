package com.example.vaccineapp.repositories;

import com.example.vaccineapp.models.Vaccine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
	List<Vaccine> findByName(String name);
}