package com.example.vaccineapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.District;
import com.example.vaccineapp.models.Province;
import com.example.vaccineapp.repositories.DistrictRepository;

@Service
public class DistrictService {
    
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceService provinceService;

    // Lấy các huyện theo tỉnh
    public List<District> getDistrictsByProvince(Province province) {
        return districtRepository.findByProvince(province);
    }

    // Lấy huyện theo ID
    public District findDistrictById(Long districtId) {
        return districtRepository.findById(districtId).orElse(null);
    }

    // Lưu hoặc cập nhật huyện
    public District save(District district) {
        return districtRepository.save(district);
    }

    // Cập nhật huyện
    public District update(Long districtId, District updatedDistrict) {
        District district = findDistrictById(districtId);
        if (district != null) {
            district.setName(updatedDistrict.getName()); // Cập nhật các thuộc tính khác nếu cần
            return districtRepository.save(district);
        }
        return null;
    }

    // Xóa huyện
    public void delete(Long districtId) {
        District district = findDistrictById(districtId);
        if (district != null) {
            districtRepository.delete(district);
        }
    }
}
