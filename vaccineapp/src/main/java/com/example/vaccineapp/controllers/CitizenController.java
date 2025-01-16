package com.example.vaccineapp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Citizen.TargetGroup;
import com.example.vaccineapp.models.Vaccination;
import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.services.CitizenService;
import com.example.vaccineapp.services.VaccinationService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/citizens")
public class CitizenController {
    
	@Autowired
    private CitizenService citizenService;
	
	@Autowired
    private VaccinationService vaccinationService;

    @GetMapping("/target-group/{group}")
    public ResponseEntity<List<Citizen>> getCitizensByTargetGroup(@PathVariable("group") TargetGroup targetGroup) {
        List<Citizen> citizens = citizenService.getCitizensByTargetGroup(targetGroup);
        System.out.println("Citizens: " + citizens); 
        return new ResponseEntity<>(citizens, HttpStatus.OK);
    }
    
    @GetMapping("/management")
    public String showCitizenManagementPage(@RequestParam(value = "group", required = false) String group, Model model) {
        List<Citizen> citizens;
        try {
            if (group != null && !group.isEmpty()) {
                TargetGroup targetGroup = TargetGroup.valueOf(group);
                citizens = citizenService.findCitizensByTargetGroup(targetGroup);
            } else {
                citizens = citizenService.findAllCitizens();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid target group: " + group);
            citizens = citizenService.findAllCitizens();
        }
        
        model.addAttribute("group", group);
        model.addAttribute("citizens", citizens);
        model.addAttribute("targetGroups", TargetGroup.values());
        return "citizen/citizen-management";
    }
    
    @GetMapping("/create")
    public String showCreateUserPage(Model model) {
    	model.addAttribute("targetGroups", TargetGroup.values());
        return "citizen/add-citizen"; 
    }
    
    @PostMapping("/create")
    public String createCitizen(@ModelAttribute Citizen citizen, Model model) {
    	Optional<Citizen> existingCitizen = citizenService.findCitizenByCccdD(citizen.getCccd());
    	if (existingCitizen.isEmpty()) {
    		model.addAttribute("error", "Số căn cước công dân đã tồn tại trong hệ thống!");
            return "citizens/create";
    	}
    	citizenService.addCitizen(citizen);
    	return "redirect:/citizens";
    }
    
    @GetMapping("/{citizenId}/vaccination-history")
    public String viewVaccinationHistory(@PathVariable Long citizenId, Model model) {
        Citizen citizen = citizenService.getCitizenById(citizenId);
        List<Vaccination> vaccinationHistory = vaccinationService.getVaccinationHistoryByCitizenId(citizenId);
        model.addAttribute("vaccinationHistory", vaccinationHistory);
        model.addAttribute("citizen", citizen);
        return "vaccination-history";
    }
}
