package io.shock.bootlab.config;

import io.shock.bootlab.service.LocaleMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {
    private final LocaleMessageService localeMessageService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        localeMessageService.generateMessages();
    }
}
