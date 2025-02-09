package io.shock.bootlab.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class MessageSourceConfig {

    private static final String MESSAGE_PATH = "classpath*:messages/**/*.properties";

    @Bean
    public MessageSource messageSource() throws IOException {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        // messages 디렉토리 아래의 모든 .properties 파일 검색
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(MESSAGE_PATH);

        // baseName 리스트 생성
        Set<String> baseNames = new HashSet<>();
        for (Resource resource : resources) {
            String path = resource.getURI().toString();

            // messages 디렉토리 이하의 경로 추출
            String baseName = path.substring(path.indexOf("messages/"), path.lastIndexOf(".properties"));

            // Windows 경로 대응 (백슬래시 → 슬래시 변경)
            baseName = baseName.replace("\\", "/");

            // ResourceBundleMessageSource는 classpath prefix를 사용하지 않음
            baseNames.add(baseName);
        }

        // baseNames 설정
        messageSource.setBasenames(baseNames.toArray(new String[0]));
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}