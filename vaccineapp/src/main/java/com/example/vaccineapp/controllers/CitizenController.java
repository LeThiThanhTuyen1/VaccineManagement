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
import com.example.vaccineapp.models.District;
import com.example.vaccineapp.models.Province;
import com.example.vaccineapp.models.Citizen.TargetGroup;
import com.example.vaccineapp.models.Vaccination;
import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.models.Ward;
import com.example.vaccineapp.services.CitizenService;
import com.example.vaccineapp.services.DistrictService;
import com.example.vaccineapp.services.ProvinceService;
import com.example.vaccineapp.services.VaccinationService;
import com.example.vaccineapp.services.WardService;

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
	public String showCitizenManagementPage(@RequestParam(value = "group", required = false) String group,
			Model model) {
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
	public String showCreateCitizenPage(Model model) {
		model.addAttribute("targetGroups", TargetGroup.values());
		return "citizen/add-citizen";
	}

	@PostMapping("/create")
	public String createCitizen(@ModelAttribute Citizen citizen, Model model) {		
		try {
			citizenService.addCitizen(citizen);
			return "redirect:/citizens/management";
	    } catch (RuntimeException e) {
	        // Thêm lỗi vào model để hiển thị
	        model.addAttribute("error", e.getMessage());
	        model.addAttribute("citizen", citizen);
	        model.addAttribute("targetGroups", TargetGroup.values());
	        return "citizen/add-citizen"; 
	    }
	}

	@GetMapping("/update/{id}")
	public String showUpdateCitizenForm(@PathVariable Long id, Model model) {
	    Optional<Citizen> citizenOpt = citizenService.findById(id);
	    if (citizenOpt.isPresent()) {
	        model.addAttribute("targetGroups", TargetGroup.values());
	        model.addAttribute("citizen", citizenOpt.get());
	        return "citizen/update-citizen";
	    }
	    return "redirect:/citizens/management";
	}


	@PostMapping("/update/{id}")
	public String updateCitizen(@PathVariable Long id, @ModelAttribute Citizen citizen, Model model) {
	    try {
	        citizenService.updateCitizen(id, citizen);
	        return "redirect:/citizens/management";
	    } catch (RuntimeException e) {
	        model.addAttribute("error", e.getMessage());
	        model.addAttribute("citizen", citizen);
	        model.addAttribute("targetGroups", TargetGroup.values());
	        return "citizen/update-citizen";
	    }
	}


	@PostMapping("/delete/{id}")
	public String deleteCitizen(@PathVariable Long id) {
		citizenService.deleteById(id);
		return "redirect:/citizens/management";
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