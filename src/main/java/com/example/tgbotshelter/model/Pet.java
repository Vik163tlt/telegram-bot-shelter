package com.example.tgbotshelter.model;

public abstract class Pet {
    private Long id;

    public Pet(Long id) {
        this.id = id;
    }

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return " id " + id;
    }
}