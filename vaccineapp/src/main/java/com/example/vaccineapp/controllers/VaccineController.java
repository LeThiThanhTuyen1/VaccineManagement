package com.example.vaccineapp.controllers;

import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.services.VaccineService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    // Display the list of vaccines
    @GetMapping
    public String listVaccines(Model model) {
        model.addAttribute("vaccines", vaccineService.findAll());
        return "vaccine/list";
    }

    // Show form to add a new vaccine
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("vaccine", new Vaccine());
        return "vaccine/create";
    }

    // Handle saving a new vaccine
    @PostMapping("/save")
    public String saveVaccine(@ModelAttribute Vaccine vaccine, Model model) {
    	List<Vaccine> existingVaccines = vaccineService.findByName(vaccine.getName());
    	if (!existingVaccines.isEmpty()) {
    	    for (Vaccine existingVaccine : existingVaccines) {
    	        if (existingVaccine.getManufacturer().equals(vaccine.getManufacturer())) {
    	            model.addAttribute("error", "Vaccine với tên và nhà sản xuất này đã tồn tại.");
    	            return "vaccine/create";
    	        }
    	    }
    	}
    	vaccineService.save(vaccine);
    	return "redirect:/vaccines";

    }

    // Show form to edit an existing vaccine
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Vaccine> vaccineOpt = vaccineService.findById(id);
        if (vaccineOpt.isPresent()) {
            model.addAttribute("vaccine", vaccineOpt.get());
            return "vaccine/edit";
        } else {
            return "redirect:/vaccines";
        }
    }

    // Handle updating a vaccine
    @PostMapping("/update/{id}")
    public String updateVaccine(@PathVariable Long id, @ModelAttribute Vaccine vaccine) {
        vaccine.setId(id);
        vaccineService.save(vaccine);
        return "redirect:/vaccines";
    }

    // Delete a vaccine
    @PostMapping("/delete/{id}")
    public String deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteById(id);
        return "redirect:/vaccines";
    }
}
