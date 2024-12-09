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

import java.util.List;

import javax.swing.plaf.ViewportUI;

@Controller
public class VaccinationHistoryController {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private VaccinationHistoryRepository vaccinationHistoryRepository;

    @GetMapping("/vaccination-history/search")
    public String searchVaccinationHistory(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "phone", required = false) String phone,
                                           Model model) {
        List<Citizen> citizens;

        if (name != null && !name.isEmpty()) {
            citizens = citizenRepository.findByFullNameContainingIgnoreCase(name);
        } else if (phone != null && !phone.isEmpty()) {
            citizens = citizenRepository.findByPhoneNumberContaining(phone);
        } else {
            model.addAttribute("error", "Vui lòng nhập thông tin tìm kiếm.");
            return "vaccination-history-search";
        }

        citizens.forEach(citizen -> {
            List<VaccinationHistory> history = vaccinationHistoryRepository.findByCitizenId(citizen.getId());
            citizen.setVaccinationHistory(history);
        });

        model.addAttribute("citizens", citizens);
        return "vaccination-history-search";
    }
}