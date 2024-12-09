package com.example.vaccineapp.controllers;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.VaccinationHistory;
import com.example.vaccineapp.repositories.CitizenRepository;
import com.example.vaccineapp.repositories.VaccinationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

import javax.swing.plaf.ViewportUI;

@Controller
public class VaccinationHistoryController {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private VaccinationHistoryRepository vaccinationHistoryRepository;

    @GetMapping("/vaccination-history/search")
    public String searchVaccinationHistory(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            Model model) {

        if ((name == null || name.isEmpty()) && (phone == null || phone.isEmpty())) {
            model.addAttribute("error", "Vui lòng nhập ít nhất một trường tìm kiếm.");
            return "vaccination-history-search";
        }

        List<Citizen> citizens;
        if (name != null && !name.isEmpty() && phone != null && !phone.isEmpty()) {
            citizens = citizenRepository.findByNameAndPhone(name, phone);
        } else if (name != null && !name.isEmpty()) {
            citizens = citizenRepository.findByFullNameContainingIgnoreCase(name);
        } else {
            citizens = citizenRepository.findByPhoneNumberContaining(phone);
        }

        if (citizens.isEmpty()) {
            model.addAttribute("error", "Không tìm thấy kết quả phù hợp với thông tin tìm kiếm.");
            model.addAttribute("name", name);
            model.addAttribute("phone", phone);
            return "vaccination-history-search";
        }

        citizens.forEach(citizen -> {
            List<VaccinationHistory> history = vaccinationHistoryRepository.findByCitizenId(citizen.getId());
            citizen.setVaccinationHistory(history);
        });

        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("citizens", citizens);
        return "vaccination-history-search";
    }
}