package com.example.vaccineapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.services.VaccinationReportService;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        // Lấy danh sách tỷ lệ tiêm chủng theo khu vực
        List<VaccinationRateByRegionDTO> report = vaccinationReportService.getVaccinationRateByRegion();
        
        // Thêm danh sách này vào model để hiển thị trên giao diện
        model.addAttribute("report", report);
        
        return "vaccination-rate"; // Trả về trang báo cáo tỷ lệ tiêm chủng
    }
}
