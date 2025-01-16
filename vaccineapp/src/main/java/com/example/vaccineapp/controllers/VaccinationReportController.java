package com.example.vaccineapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.services.VaccinationReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import com.example.vaccineapp.dto.VaccinationRateByRegionDTO;

@Controller
@RequestMapping("/reports")
public class VaccinationReportController {

    @Autowired
    private VaccinationReportService vaccinationReportService;

    @GetMapping("/vaccination-report")
    public String getVaccinationReport(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        if (startDate == null) {
            startDate = LocalDate.now().minusMonths(1);  // Giá trị mặc định là một tháng trước
        }
        if (endDate == null) {
            endDate = LocalDate.now();  // Giá trị mặc định là ngày hiện tại
        }
        
        List<VaccinationReportDTO> report = vaccinationReportService.getVaccinationReport(startDate, endDate);
        
        model.addAttribute("report", report);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        
        return "vaccination-report";
    }
   
    @GetMapping("/vaccination-rate")
    public String getVaccinationRateByRegion(Model model) {
        List<VaccinationRateByRegionDTO> report = vaccinationReportService.getVaccinationRateByRegion();

        // Convert list to JSON for front-end
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonReport = objectMapper.writeValueAsString(report);
            model.addAttribute("report", jsonReport);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "vaccination-rate"; // View to render the chart
    }

}
