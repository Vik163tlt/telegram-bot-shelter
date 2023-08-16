package com.example.tgbotshelter.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import com.example.tgbotshelter.dto.CatDto;
import com.example.tgbotshelter.services.CatService;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    /**
     * Создание нового питомца типа Cat
     */
    @PostMapping("/new")
    public void createCat(@NotNull @RequestBody CatDto catDto) {
        catService.createCat(catDto.getName(), catDto.getBreed(), catDto.getDateOfBirth(), catDto.getSuit(), catDto.getGender());
    }

    /**
     * Получение экземпляра класса
     *
     * @return CatDto instance
     */
    @GetMapping("/get")
    public CatDto getCat(Long id) {
        return catService.getCat(id);
    }

    /**
     * Получение всех имеющихся CatDto
     *
     * @return Список CatDto объектов
     */
    @GetMapping("/all")
    public List<CatDto> getAll() {
        return catService.getAllCats();
    }

    /**
     * Получение всех питомцев владельца
     *
     * @return List of CatDto
     */
    @GetMapping("/oid")
    public List<CatDto> getAllCatsByOwnerId(Long ownerId) {
        return catService.getAllCats(ownerId);
    }

    /**
     * Удаление Cat instance
     */
    @DeleteMapping("/del")
    public void deleteCat(@RequestParam(required = false, name = "id") Long id) {
        catService.deleteCat(id);
    }
}