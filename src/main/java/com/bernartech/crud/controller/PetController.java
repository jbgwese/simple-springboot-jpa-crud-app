package com.bernartech.crud.controller;

import com.bernartech.crud.entities.Pet;
import com.bernartech.crud.repository.PeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
public class PetController {
    @Autowired
    PeterRepository petRepository;
    @RequestMapping("/")
    public  String home(){
        return "home";
    }

    @GetMapping("/form")
    public  String showForm(Pet pet){
        return "add-pet";
    }

    @PostMapping("/addpet")
    public String addPet(@Valid Pet pet, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-pet";
        }

        petRepository.save(pet);
        model.addAttribute("pets", petRepository.findAll());
        //return "redirect:/home";
        return "home";
    }

    @GetMapping("/edit/{id}")
    public  String updateForm(@PathVariable("id") long id , Model model){
        Pet pet = petRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id: "+ id));
        model.addAttribute("pet",pet);
        return "update-pet";

    }

    @GetMapping("/delete/{id}")
    public  String deletePet(@PathVariable("id") long id, Model model){
        Pet pet= petRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid pet: "+ id));
        petRepository.delete(pet);
        model.addAttribute("pets",petRepository.findAll());
        return "redirect:/home";
    }
}
