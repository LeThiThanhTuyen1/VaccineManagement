package com.example.vaccineapp.repositories;

import com.example.vaccineapp.models.District;
import com.example.vaccineapp.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findByDistrict(District district);
}
