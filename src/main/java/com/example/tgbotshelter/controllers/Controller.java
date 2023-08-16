package com.example.tgbotshelter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tgbotshelter.services.ClientService;

@RestController
public class Controller {
    private final ClientService clientService;

    public Controller(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Проверка запущен ли бот
     */
    @GetMapping
    public String hello() {
        return "Бот запущен!";
    }

    /**
     * Получение информации о приюте кошек
     * Считывается из файла .txt
     * @return String info
     */
    @GetMapping("/info/shelter/cat")
    public String infoShelterCat() {
        return clientService.readFile("src/main/resources/draw/info_shelter_cat.txt");
    }

    /**
     * Получение информации о приюте собак
     * Считывается из файла .txt
     * @return String info
     */
    @GetMapping("/info/shelter/dog")
    public String infoShelterDog() {
        return clientService.readFile("src/main/resources/draw/info_shelter_dog.txt");
    }
}