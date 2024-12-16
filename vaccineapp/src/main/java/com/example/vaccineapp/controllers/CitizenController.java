package com.example.vaccineapp.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Citizen.TargetGroup;
import com.example.vaccineapp.services.CitizenService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/citizens")
public class CitizenController {
    
	@Autowired
    private CitizenService citizenService;

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
        return "citizen-management";
    }
}
