package com.example.vaccineapp.controllers;

import com.example.vaccineapp.models.District;
import com.example.vaccineapp.models.Province;
import com.example.vaccineapp.models.Ward;
import com.example.vaccineapp.services.DistrictService;
import com.example.vaccineapp.services.ProvinceService;
import com.example.vaccineapp.services.WardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {
    
    @Autowired
    private ProvinceService provinceService;
    
    @Autowired
    private DistrictService districtService;
    
    @Autowired
    private WardService wardService;

    @GetMapping("/provinces")
    public String showProvinces(Model model) {
        List<Province> provinces = provinceService.getAllProvinces();
        model.addAttribute("provinces", provinces);
        return "location";
    }
    
    @GetMapping("/districts")
    @ResponseBody
    public List<District> getDistrictsByProvince(@RequestParam("provinceId") Long provinceId) {
        Province province = provinceService.findProvinceById(provinceId);
        List<District> districts = districtService.getDistrictsByProvince(province);
        return districts;
    }

    @GetMapping("/wards")
    @ResponseBody
    public List<Ward> getWardsByDistrict(@RequestParam("districtId") Long districtId) {
        District district = districtService.findDistrictById(districtId);
        return wardService.getWardsByDistrict(district);
    }
}
