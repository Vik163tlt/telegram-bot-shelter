package com.example.tgbotshelter.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.tgbotshelter.dto.CatOwnerDto;
import com.example.tgbotshelter.dto.DogOwnerDto;
import com.example.tgbotshelter.model.CatOwner;
import com.example.tgbotshelter.model.DogOwner;
import com.example.tgbotshelter.services.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Создание DogOwner
     */
    @PostMapping("/dog/new")
    public void createDogOwner(@RequestParam Long clientChatId, @RequestParam Long dogId) {
        ownerService.createDogOwner(clientChatId, dogId);
    }

    /**
     * Создание CatOwner
     */
    @PostMapping("/cat/new")
    public void createCatOwner(@RequestParam Long clientChatId, @RequestParam Long catId) {
        ownerService.createCatOwner(clientChatId, catId);
    }

    /**
     * Получение DogOwner
     *
     * @return DogOwner
     */
    @GetMapping("/dog/get")
    public DogOwnerDto getDogOwner(@RequestParam(required = false, name = "id") Long id) {
        return ownerService.getDogOwner(id);
    }

    /**
     * Получение CatOwner
     *
     * @return CatOwner
     */
    @GetMapping("/cat/get")
    public CatOwnerDto getCatOwner(@RequestParam(required = false, name = "id") Long id) {
        return ownerService.getCatOwner(id);
    }

    /**
     * Изменение DogOwner
     */
    @PutMapping("/dog/put")
    public void updateDogOwner(@RequestBody DogOwner dogOwner) {
        ownerService.updateDogOwner(dogOwner.getId(), dogOwner.getChatId(), dogOwner.getFirstName(),
                dogOwner.getLastName(), dogOwner.getPhoneNumber(), dogOwner.getProbation());
    }

    /**
     * Изменение CatOwner
     */
    @PutMapping("/cat/put")
    public void updateCatOwner(@RequestBody CatOwner catOwner) {
        ownerService.updateCatOwner(catOwner.getId(), catOwner.getChatId(), catOwner.getFirstName(),
                catOwner.getLastName(), catOwner.getPhoneNumber(), catOwner.getProbation());
    }

    /**
     * Удаление DogOwner
     */
    @DeleteMapping("/dog/del")
    public void deleteDogOwner(Long id) {
        ownerService.deleteDogOwner(id);
    }

    /**
     * Удаление CatOwner
     */
    @DeleteMapping("/cat/del")
    public void deleteCatOwner(Long id) {
        ownerService.deleteCatOwner(id);
    }
}