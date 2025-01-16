package com.example.vaccineapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.repositories.VaccineRepository;

@Service
public class VaccineService {
	@Autowired
	private VaccineRepository vaccineRepository;
	
	// Lưu vaccine vào cơ sở dữ liệu
    public void save(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
    }

    // Tìm vaccine theo tên
    public List<Vaccine> findByName(String name) {
        return vaccineRepository.findByName(name);
    }

    // Lấy danh sách tất cả vaccine
    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }

	// Tìm vaccine theo ID
    public Optional<Vaccine> findById(Long id) {
        // Trả về vaccine nếu tìm thấy theo id, hoặc Optional.empty() nếu không tìm thấy
        return vaccineRepository.findById(id);
    }

    // Xóa vaccine theo ID
    public void deleteById(Long id) {
        // Kiểm tra xem vaccine có tồn tại không trước khi xóa
        if (vaccineRepository.existsById(id)) {
            vaccineRepository.deleteById(id);  // Xóa vaccine theo ID
        } else {
            // Có thể log thông báo hoặc ném ngoại lệ nếu không tìm thấy vaccine
            System.out.println("Vaccine không tồn tại với ID: " + id);
        }
    }
}
