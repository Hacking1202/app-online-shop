package com.example.apponlineshop;

import com.example.apponlineshop.bot.BotSettings;
import com.example.apponlineshop.repository.UserRepository;
import com.example.apponlineshop.service.CategoryService;
import com.example.apponlineshop.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@CrossOrigin
public class AppOnlineShopApplication {

    public static void main(String[] args) throws TelegramApiException {
        ConfigurableApplicationContext run = SpringApplication.run(AppOnlineShopApplication.class, args);
        UserService userService = run.getBean(UserService.class);
        UserRepository userRepository = run.getBean(UserRepository.class);
        CategoryService categoryService = run.getBean(CategoryService.class);
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new BotSettings(userService, userRepository, categoryService));
    }

}
