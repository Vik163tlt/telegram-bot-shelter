package com.example.tgbotshelter.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.tgbotshelter.listener.TelegramBotUpdatesListener;

@Service
public class KeyBoardService {
    private final TelegramBot telegramBot;
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    public KeyBoardService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void mainMenu(Long chatId) {
        logger.info("sendMessage: {}, {}", chatId, "Главное меню ");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("Информация о приюте"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Взять питомца"), new KeyboardButton("Отчет"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"));
        returnResponseReplyKeyboard(replyKeyboardMarkup, chatId, "Главное меню");
    }

    public void chooseMenu(Long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "меню выбора ");
        String emoji_cat = EmojiParser.parseToUnicode(":cat:");
        String emoji_dog = EmojiParser.parseToUnicode(":dog:");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton(emoji_cat + " CAT"));
        replyKeyboardMarkup.addRow(new KeyboardButton(emoji_dog + " DOG"));
        returnResponseReplyKeyboard(replyKeyboardMarkup, chatId, "Скорее выбирай питомца");
    }

    public void infoMenu(Long chatId) {
        logger.info("sendMenuInfo: {}, {}", chatId, "Информация о приюте");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Рассказать о нашем приюте"),
                new KeyboardButton("Правила ухода за животными"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Вернуться в главное меню"));
        returnResponseReplyKeyboard(replyKeyboardMarkup, chatId, "Информация о приюте");
    }

    public void volunteerMenu(Long chatId) {
        logger.info("sendMessage: {}, {}", chatId, "Меню волонтёра");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Отправить предупреждение"), new KeyboardButton("Проверить отчет"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Вернуться в главное меню"));
        returnResponseReplyKeyboard(replyKeyboardMarkup, chatId, "Вы стали волонтёром");
    }

    public void returnResponseReplyKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String text) {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(true);
        SendMessage request = new SendMessage(chatId, text).replyMarkup(replyKeyboardMarkup).parseMode(ParseMode.HTML).disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);
        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("Код ошибки: {}", codeError);
            logger.info("Описание -: {}", description);
        }
    }

    public void getContact(Long chatId) {
        logger.info("sendMessage: {}, {}", chatId, "Отправка номера телефона клиентом");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton("Отправить >").requestContact(true)
        );
        returnResponseReplyKeyboard(keyboard, chatId, "Отправьте номер телефона");
    }
}