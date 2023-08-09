package com.example.tgbotshelter.model;

import com.pengrad.telegrambot.model.File;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "chat_id", nullable = false)
    private Long chatId;
    @Column(name = "date", nullable = false)
    private LocalDateTime localDateTime;
    @Column(name = "photo", nullable = false)
    private File photo;
    @Column(name = "report", nullable = false)
    private String report;

    public Report() {

    }

    public Report(Long id, Long chatId, LocalDateTime localDateTime, File photo, String report) {
        this.id = id;
        this.chatId = chatId;
        this.localDateTime = localDateTime;
        this.photo = photo;
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report1 = (Report) o;
        return Objects.equals(chatId, report1.chatId) && localDateTime.equals(report1.localDateTime) && photo.equals(report1.photo) && report.equals(report1.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTime, photo, report);
    }
}