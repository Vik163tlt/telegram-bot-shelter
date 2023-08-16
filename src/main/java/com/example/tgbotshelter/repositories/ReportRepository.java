package com.example.tgbotshelter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tgbotshelter.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    void deleteByChatId(Long chatId);

    Report getByChatId(Long chatId);
}