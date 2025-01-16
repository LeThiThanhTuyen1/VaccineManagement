package com.example.vaccineapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vaccineapp.models.Vaccinations;
import com.example.vaccineapp.models.VaccinationStatus;
import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.services.VaccinationService;
import com.example.vaccineapp.services.VaccineService;

@Controller
@RequestMapping("/")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;
    @Autowired
    private VaccineService vaccineService;
    
    @GetMapping("/vaccinations")
    public String getRegistrations(
            @RequestParam(required = false) String vaccineName,
            @RequestParam(required = false) String vaccinationDate,
            @RequestParam(required = false, defaultValue = "PENDING") String status,
            Model model) {

        // Lấy danh sách lịch tiêm đầy đủ
        List<Vaccinations> vaccinations = vaccinationService.getAllVaccinations();

        // Áp dụng bộ lọc nếu có tham số
        if (vaccineName != null && !vaccineName.isEmpty()) {
        	vaccinations = vaccinations.stream()
                    .filter(r -> r.getVaccine().getName().equalsIgnoreCase(vaccineName))
                    .collect(Collectors.toList());
        }
        if (vaccinationDate != null && !vaccinationDate.isEmpty()) {
        	vaccinations = vaccinations.stream()
                    .filter(r -> r.getVaccinationDate().toString().equals(vaccinationDate))
                    .collect(Collectors.toList());
        }
        if (status != null && !status.isEmpty()) {
            VaccinationStatus selectedStatus = VaccinationStatus.valueOf(status.toUpperCase());
            vaccinations = vaccinations.stream()
                    .filter(r -> r.getStatus() == selectedStatus)
                    .collect(Collectors.toList());
        }

        // Lấy danh sách vaccine để hiển thị trong dropdown
        List<Vaccine> vaccines = vaccineService.getAllVaccines();

        // Đưa dữ liệu vào Model
        model.addAttribute("vaccinations", vaccinations);
        model.addAttribute("vaccines", vaccines);
        model.addAttribute("vaccineName", vaccineName);
        model.addAttribute("vaccinationDate", vaccinationDate);
        model.addAttribute("status", status);
        return "vaccinations";
    }
    
    @PostMapping("/updateStatus")
    public String updateVaccinationStatus(
            @RequestParam Long vaccinationId,
            @RequestParam String action, 
            RedirectAttributes redirectAttributes) {

        // Cập nhật trạng thái dựa trên action
        VaccinationStatus newStatus = VaccinationStatus.valueOf(action.toUpperCase());
        vaccinationService.updateVaccinationStatus(vaccinationId, newStatus);
        
        redirectAttributes.addFlashAttribute("message", "Trạng thái đã được cập nhật thành công!");
        // Chuyển hướng về trang danh sách
        return "redirect:/vaccinations";
    }

}