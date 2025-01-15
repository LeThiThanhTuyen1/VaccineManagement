package com.example.vaccineapp.services;

import com.example.vaccineapp.models.Province;
import com.example.vaccineapp.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }

    public void deleteProvince(Long id) {
        provinceRepository.deleteById(id);
    }

	public Province findProvinceById(Long id) {
		Optional<Province> optionalProvince = provinceRepository.findById(id);
        return optionalProvince.orElseThrow(() -> new RuntimeException("Province not found"));
    }

	// Cập nhật tỉnh
    public Province update(Long provinceId, Province updatedProvince) {
        Province province = findProvinceById(provinceId);
        if (province != null) {
            province.setName(updatedProvince.getName()); // Cập nhật các thuộc tính khác nếu cần
            return provinceRepository.save(province);
        }
        return null;
    }

    // Xóa tỉnh
    public void delete(Long provinceId) {
        Province province = findProvinceById(provinceId);
        if (province != null) {
            provinceRepository.delete(province);
        }
    }

}
