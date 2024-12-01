package com.example.vaccineapp.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.services.CitizenService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CitizenController {
    
    @Autowired
    private CitizenService citizenService;
    private static final String citizenTemplate = "Hello";
    private final AtomicLong couter = new AtomicLong();

    @GetMapping("/citizens")
    public String listCitizens(Model model) {
        List<Citizen> citizens = citizenService.getAllCitizens();
        model.addAttribute("citizens", citizens);
        return "citizenList"; // Trang hiển thị danh sách công dân
    }
    
    @GetMapping("/test2")
    public String citizenTest2(Model model) {
        // Thêm thông tin vào model nếu cần
        model.addAttribute("message", "Đây là trang thử nghiệm thứ 2!");
        return "testPage2"; // Trả về trang testPage2.html
    }
    
    @GetMapping("/test1")
    @ResponseBody
    public String citizenTest(@RequestParam String param) {
        // Trả về kết quả trực tiếp dưới dạng chuỗi
        return "Bạn đã truyền tham số: " + param;
    }
}
