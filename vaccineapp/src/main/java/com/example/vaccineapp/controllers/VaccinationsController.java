package com.example.vaccineapp.controllers;

import com.example.vaccineapp.models.Vaccinations;
import com.example.vaccineapp.repositories.VaccinationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class VaccinationsController {

    @Autowired
    private VaccinationsRepository vaccinationHistoryRepository;

    @GetMapping("/vaccination-history/search")
    public String searchVaccinationHistory(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            Model model) {
        if ((name == null || name.isEmpty()) && (phone == null || phone.isEmpty())) {
            model.addAttribute("error", "Vui lòng nhập ít nhất một trường tìm kiếm.");
            return "vaccination-history-search";
        }

        name = (name == null ? "" : name.trim());
        phone = (phone == null ? "" : phone.trim());

        List<Vaccinations> histories = vaccinationHistoryRepository.findDetailedVaccinationHistory(name, phone);

        if (histories.isEmpty()) {
            model.addAttribute("error", "Không tìm thấy kết quả phù hợp với thông tin tìm kiếm.");
            model.addAttribute("name", name);
            model.addAttribute("phone", phone);
            return "vaccination-history-search";
        }

        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("histories", histories);
        return "vaccination-history-search";
    }
}