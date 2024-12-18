package com.example.vaccineapp.controllers;

import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vaccines")
public class VaccineController {

    @Autowired
    private VaccineRepository vaccineRepository;

    // Display the list of vaccines
    @GetMapping
    public String listVaccines(Model model) {
        model.addAttribute("vaccines", vaccineRepository.findAll());
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
    public String saveVaccine(@ModelAttribute Vaccine vaccine) {
        vaccineRepository.save(vaccine);
        return "redirect:/vaccines";
    }

    // Show form to edit an existing vaccine
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vaccine vaccine = vaccineRepository.findById(id).orElseThrow();
        model.addAttribute("vaccine", vaccine);
        return "vaccine/edit";
    }

    // Handle updating a vaccine
    @PostMapping("/update/{id}")
    public String updateVaccine(@PathVariable Long id, @ModelAttribute Vaccine vaccine) {
        vaccine.setId(id);
        vaccineRepository.save(vaccine);
        return "redirect:/vaccines";
    }

    // Delete a vaccine
    @PostMapping("/delete/{id}")
    public String deleteVaccine(@PathVariable Long id) {
        vaccineRepository.deleteById(id);
        return "redirect:/vaccines";
    }
}
