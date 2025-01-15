package com.example.vaccineapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.District;
import com.example.vaccineapp.models.Ward;
import com.example.vaccineapp.repositories.WardRepository;

@Service
public class WardService {
    
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private DistrictService districtService;

    // Lấy các xã theo huyện
    public List<Ward> getWardsByDistrict(District district) {
        return wardRepository.findByDistrict(district);
    }

    // Lấy xã theo ID
    public Ward findWardById(Long wardId) {
        return wardRepository.findById(wardId).orElse(null);
    }

    // Lưu hoặc cập nhật xã
    public Ward save(Ward ward) {
        return wardRepository.save(ward);
    }

    // Cập nhật xã
    public Ward update(Long wardId, Ward updatedWard) {
        Ward ward = findWardById(wardId);
        if (ward != null) {
            ward.setName(updatedWard.getName()); // Cập nhật các thuộc tính khác nếu cần
            return wardRepository.save(ward);
        }
        return null;
    }

    // Xóa xã
    public void delete(Long wardId) {
        Ward ward = findWardById(wardId);
        if (ward != null) {
            wardRepository.delete(ward);
        }
    }
}
