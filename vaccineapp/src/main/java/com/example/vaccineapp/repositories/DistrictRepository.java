package com.example.vaccineapp.repositories;

import com.example.vaccineapp.models.District;
import com.example.vaccineapp.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByProvince(Province province);
}
