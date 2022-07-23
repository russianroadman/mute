package ru.russianroadman.mute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.russianroadman.mute.Bot;
import ru.russianroadman.mute.service.tgapi.UpdateHandler;

@Configuration
public class BotConfiguration {

    private final ConfigCredentialsService conf;
    private final UpdateHandler updateHandler;


    public BotConfiguration(
        ConfigCredentialsService conf,
        /*
            Lazy injection to break cycle:
            -> BotConfiguration -> UpdateHandler -> MuteService ->
         */
        @Lazy UpdateHandler updateHandler
    ) {
        this.conf = conf;
        this.updateHandler = updateHandler;
    }

    @Bean
    public Bot bot(){
        return new Bot(
            conf.getBotUsername(),
            conf.getBotToken(),
            updateHandler
        );
    }

}