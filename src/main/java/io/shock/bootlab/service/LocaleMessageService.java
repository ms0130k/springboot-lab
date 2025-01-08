package io.shock.bootlab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocaleMessageService {
    private static final String PROPERTIES_DIR = "classpath:messages/**/*.properties";
    private static final String OUTPUT_DIR = System.getProperty("java.io.tmpdir") + "/messages/";
    private final ResourcePatternResolver resourcePatternResolver;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void generateMessages() {
        //리소스 가져오기
        try {
            Resource[] resources = resourcePatternResolver.getResources(PROPERTIES_DIR);
            Map<String, List<Resource>> grouped = groupingByLocale(resources);

            extracted(grouped);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void extracted(Map<String, List<Resource>> grouped) throws IOException {
        for (Map.Entry<String, List<Resource>> entry : grouped.entrySet()) {
            Map<String, String> messages = new HashMap<>();
            List<Resource> resources = entry.getValue();
            for (Resource resource : resources) {
                Properties properties = new Properties();
                try (InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);) {
                    properties.load(inputStreamReader);
                }
                properties.forEach((key, value) -> messages.put((String) key, (String) value));
            }

            String locale = entry.getKey();
            File file = new File(OUTPUT_DIR + "message_" + locale + ".js");
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("const MESSAGES_" + locale.toUpperCase() + " = ");
                writer.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messages));
                writer.write(";");
            }
        }
    }

    private static Map<String, List<Resource>> groupingByLocale(Resource[] resources) {
        return Arrays.stream(resources)
                .filter(Resource::isReadable)
                .filter(rs -> Pattern.compile("^.*_(\\w+)\\.properties$").matcher(rs.getFilename()).matches())
                .collect(Collectors.groupingBy(rs -> {
                    Matcher matcher = Pattern.compile("^.*_(\\w+)\\.properties$").matcher(rs.getFilename());
                    matcher.find();
                    return matcher.group(1);
                }));
    }

    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("^.*_(\\w+)\\.properties$");
//        String fileName = "gogogo_messages_en.properties";
//        Matcher matcher = pattern.matcher(fileName);
//        boolean b = matcher.find();
//        System.out.println(b);
//        String group = pattern.matcher(fileName).group(1);
//        System.out.println(group);
        File file = new File(Paths.get("C:\\Shock\\workspace\\springboot-lab\\src\\main\\resources\\messages\\default_ko.json").toString());
        boolean exists = file.exists();
        System.out.println(exists);
    }
}
