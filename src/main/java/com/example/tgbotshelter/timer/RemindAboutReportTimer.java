package com.example.tgbotshelter.timer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.tgbotshelter.repositories.CatOwnerRepository;
import com.example.tgbotshelter.repositories.DogOwnerRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class RemindAboutReportTimer {
    private final DogOwnerRepository dogOwnerRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final TelegramBot telegramBot;

    public RemindAboutReportTimer(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, TelegramBot telegramBot) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void remind() {
        dogOwnerRepository.findAllByProbation(
                        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(dogOwner -> {
                    if (dogOwner.getProbation().isAfter(LocalDateTime.now())) {
                        telegramBot.execute(
                                new SendMessage(dogOwner.getChatId(), "Составьте отчёт!")
                        );
                    } else new SendMessage(dogOwner.getChatId(), "Поздравляем, вы прошли испытательный срок!");
                });
        catOwnerRepository.findAllByProbation(
                        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(catOwner -> {
                    if (catOwner.getProbation().isAfter(LocalDateTime.now())) {
                        telegramBot.execute(
                                new SendMessage(catOwner.getChatId(), "Составьте отчёт!")
                        );
                    } else new SendMessage(catOwner.getChatId(), "Поздравляем, вы прошли испытательный срок!");
                });
    }
}