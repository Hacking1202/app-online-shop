package com.example.apponlineshop.bot;

import com.example.apponlineshop.service.UserService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Service
public class BotSettings extends TelegramLongPollingBot {

    private static final InlineButton IB = new InlineButton();
    private final Map<Long, String> isTan = new HashMap<>();

    final UserService userService;

    public BotSettings(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        SendPhoto sendPhoto = new SendPhoto();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                Long chatId = message.getChatId();
                sendMessage.setChatId(chatId.toString());
                if (text.equals("/start")) {
                    if (userService.isRegister(chatId)) {
                        //TODO category list
                    } else {
                        sendMessage.setReplyMarkup(new InlineKeyboardMarkup(getInlineButtonRows(Template.START_BUTTON)));
                        sendMSG(sendMessage, text, chatId.toString());
                    }
                }

                if (isTan.get(chatId).equals("phoneNumber")) {
                    sendMSG(sendMessage, "username kiriting", chatId.toString());
                    isTan.put(chatId, "username");
                }else if (isTan.get(chatId).equals("username")) {
                    sendMSG(sendMessage, "password kiriting", chatId.toString());
                }
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long id = update.getCallbackQuery().getFrom().getId();
            if (data.equals("Register")) {
                buttonPhoneNumber(sendMessage, id.toString());
                isTan.put(id, "phoneNumber");
            }
        }
    }

    //Message
    public void delMsg(Message message) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId().toString());
        deleteMessage.setMessageId(message.getMessageId());
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            System.err.println("no del");
        }
    }

    public void buttonPhoneNumber(SendMessage message, String chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Telefon raqam");
        keyboardButton.setRequestContact(true);
        keyboardFirstRow.add(keyboardButton);
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMSG(message, "Telefon raqamingizni faqat chiqib turgan tugma orqalli yuboring", chatId);
    }

    public void sendMSG(SendMessage sendMessage, String text, String chatId) {
        try {
            sendMessage.setChatId(chatId);
            sendMessage.setText(text);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("not execute");
        }
    }

    //Inline button
    public List<List<InlineKeyboardButton>> getInlineButtonRows(List<String> data) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        int length = data.size() % 2 != 0 ? data.size() - 1 : data.size();
        for (int i = 0; i < length; i += 2) {
            List<InlineKeyboardButton> inlineButton = new ArrayList<>();
            inlineButton.add(IB.getInlineButton(data.get(i), data.get(i)));
            inlineButton.add(IB.getInlineButton(data.get(i + 1), data.get(i + 1)));
            rows.add(inlineButton);
        }
        if (data.size() % 2 != 0) {
            String text = data.get(data.size() - 1);
            rows.add(Collections.singletonList(IB.getInlineButton(text, text)));
        }
        return rows;
    }

    @Override
    public String getBotUsername() {
        return Template.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Template.BOT_TOKEN;
    }
}
