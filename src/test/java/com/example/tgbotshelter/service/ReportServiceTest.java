package com.example.tgbotshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tgbotshelter.model.Report;
import com.example.tgbotshelter.repositories.ReportRepository;
import com.example.tgbotshelter.services.ReportService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @InjectMocks
    private ReportService reportService;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private Report report;

    @Test
    @DisplayName("Проверка создания отчёта")
    void createReportTest() {
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        verify(reportRepository, verificationData -> reportService.createReport(update)).saveAndFlush(report);
    }

    @Test
    @DisplayName("Проверка получения всех отчётов")
    void getAllTest() {
        when(reportRepository.findAll()).thenReturn(List.of(report));
        assertEquals(List.of(report), reportService.getAll());
    }
    @Test
    @DisplayName("Проверка удаления отчёта")
    void deleteByChatIdTest() {
        when(reportRepository.getByChatId(0L)).thenReturn(report);
        verify(reportRepository, verificationData -> reportService.deleteByChatId(0L)).deleteByChatId(0L);
    }
}