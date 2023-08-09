package com.example.tgbotshelter.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tgbotshelter.services.ReportService;

@RestController
@RequestMapping("/rep")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    /**
     * Удаление отчёта по chatId
     */
    @DeleteMapping("/del")
    public void deleteReport(@RequestParam(required = false, name = "chatId") Long chatId) {
        reportService.deleteByChatId(chatId);
    }
}