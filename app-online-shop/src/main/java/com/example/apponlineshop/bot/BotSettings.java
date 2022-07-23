package com.example.apponlineshop.bot;

import com.example.apponlineshop.entity.Category;
import com.example.apponlineshop.payload.DtoUser;
import com.example.apponlineshop.payload.ResCategory;
import com.example.apponlineshop.repository.UserRepository;
import com.example.apponlineshop.service.CategoryService;
import com.example.apponlineshop.service.UserService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

    Map<Long, String> phoneNumber = new HashMap<>();
    Map<Long, String> username = new HashMap<>();
    List<String> categoryNameList = new ArrayList<>();
    List<String> categoryOneNameList = new ArrayList<>();


    final UserService userService;
    final UserRepository userRepository;
    final CategoryService categoryService;

    public BotSettings(UserService userService, UserRepository userRepository, CategoryService categoryService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
    }


    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            if (message.hasText()) {
                String text = message.getText();
                sendMessage.setChatId(chatId.toString());
                if (text.equals("/start")) {
                    if (userService.isRegister(chatId)) {
                        sendMessage.setReplyMarkup(getInlineButton(getCourseOne()));
                        sendMSG(sendMessage, "category tan", message);
                    } else {
                        sendMessage.setReplyMarkup(getInlineButton(Template.START_BUTTON));
                        sendMSG(sendMessage, "Assalomu alaykum " + message.getFrom().getFirstName() + " botimizga hush kelipsiz bulimni tanlang", message);
                    }
                }
                if (isTan.get(chatId) != null) {
                    switch (isTan.get(chatId)) {
                        case "phoneNumber":
                            sendMSG(sendMessage, "phone numberni pastdagi tugmani bosinsh orqali kiriting ", message);
                            break;
                        case "username":
                            username.put(chatId, text);
                            isTan.put(chatId, "password");
                            sendMSG(sendMessage, "password kiriting", message);
                            break;
                        case "password":
                            DtoUser dtoUser = new DtoUser(chatId, username.get(chatId), phoneNumber.get(chatId), text);
                            userService.saveUser(dtoUser);
                            isTan.remove(chatId);
                            sendMessage.setReplyMarkup(getInlineButton(getCourseOne()));
                            sendMSG(sendMessage, "siz muaffaqiyatle ruyxatdan utdingiz category tanlang", message);
                            break;
                        case "LoginUsername":
                            if (userService.isLoginUsername(text)) {
                                sendMSG(sendMessage, "passwordingizni kiriting", message);
                                isTan.put(chatId, "LoginPassword");
                            } else {
                                sendMSG(sendMessage, "username topilmadi!", message);
                            }
                            break;
                        case "LoginPassword":
                            if (userService.isLoginPassword(text)) {
                                sendMessage.setReplyMarkup(getInlineButton(getCourseOne()));
                                sendMSG(sendMessage, "category tan", message);
                            } else {
                                sendMSG(sendMessage, "password notugri!", message);
                            }
                            break;
                    }
                }
            } else if (message.hasContact()) {
                if (isTan.get(chatId).equals("phoneNumber")) {
                    phoneNumber.put(chatId, message.getContact().getPhoneNumber());
                    isTan.put(chatId, "username");
                    sendMSG(sendMessage, "username kiriting", message);
                }
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long id = update.getCallbackQuery().getFrom().getId();
            for (String category : getCourseOne()) {
                if (data.equals(category)) {
                    sendMessage.setReplyMarkup(getInlineButton(getCategory(data)));
                    sendMSG(sendMessage, "maxsulotni tanlang", update.getCallbackQuery().getMessage());
                }
            }
            if (data.equals("Register")) {
                buttonPhoneNumber(sendMessage, update.getCallbackQuery().getMessage());
                isTan.put(id, "phoneNumber");
            } else if (data.equals("Login")) {
                sendMSG(sendMessage, "usenamengizni kiriting", update.getCallbackQuery().getMessage());
                isTan.put(id, "LoginUsername");
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

    public void buttonPhoneNumber(SendMessage sendMessage, Message message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
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
        sendMSG(sendMessage, "Telefon raqamingizni faqat chiqib turgan tugma orqalli yuboring", message);
    }

    public void sendMSG(SendMessage sendMessage, String text, Message message) {
        delMsg(message);
        try {
            sendMessage.setChatId(message.getChatId().toString());
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

    public List<String> getCategory(String name) {
        for (Category category : categoryService.getCategory(name)) {
            categoryNameList.add(category.getName());
        }
        return categoryNameList;
    }

    public List<String> getCourseOne() {
        for (ResCategory resCategory : categoryService.getListCategory()) {
            if (resCategory.getCategoryId() == null) {
                categoryOneNameList.add(resCategory.getName());
            }
        }
        return categoryOneNameList;
    }

    public InlineKeyboardMarkup getInlineButton(List<String> list) {
        return new InlineKeyboardMarkup(getInlineButtonRows(list));
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
