package io.shock.bootlab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String tempDir = System.getProperty("java.io.tmpdir");

        registry.addResourceHandler("/messages/**")
                .addResourceLocations("file:" + tempDir + "/messages/");
    }
}