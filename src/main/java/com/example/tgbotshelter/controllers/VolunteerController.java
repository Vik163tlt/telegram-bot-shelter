package com.example.tgbotshelter.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.tgbotshelter.model.Volunteer;
import com.example.tgbotshelter.services.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/v")
public class VolunteerController {
    private final VolunteerService volunteerService;
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * Получение экземпляра класса Volunteer
     * @param phoneNumber номер телефона
     * @return Volunteer instance
     */
    @GetMapping("/get")
    public Volunteer getVolunteer(String phoneNumber) {
        return volunteerService.getVolunteer(phoneNumber);
    }

    /**
     * Получение списка волонтёров
     * @return List of Volunteer
     */
    @GetMapping("/all")
    public List<Volunteer> getAll() {
        return volunteerService.getAll();
    }

    /**
     * Изменение экземпляра класса Volunteer
     */
    @PutMapping("/put")
    public void updateVolunteer(@RequestBody Volunteer volunteer) {
        volunteerService.updateVolunteer(volunteer);
    }

    /**
     * Удаление экземпляра класса Volunteer
     */
    @DeleteMapping("/del")
    public void deleteVolunteer(@RequestParam(required = false, name = "id") Long id) {
        volunteerService.deleteVolunteer(id);
    }
}