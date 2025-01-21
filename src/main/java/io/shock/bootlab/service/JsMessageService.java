package io.shock.bootlab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JsMessageService {
    private static final String PROPERTIES_DIR = "classpath:messages/**/*.properties";
    private static final String MESSAGE_DIR = System.getProperty("java.io.tmpdir") + "/messages/";
    private final ResourcePatternResolver resolver;
    private final ObjectMapper objectMapper;

    public JsMessageService(ResourcePatternResolver resolver, ObjectMapper objectMapper) {
        this.resolver = resolver;
        this.objectMapper = objectMapper;
    }

    public void generateMessage() {
        try {
            Resource[] resources = resolver.getResources(PROPERTIES_DIR);
            Map<String, List<Resource>> grouped = groupResourcesByLocale(resources);
            for (Map.Entry<String, List<Resource>> entry : grouped.entrySet()) {
                HashMap<String, Object> messages = new HashMap<>();
                for (Resource resource : entry.getValue()) {
                    InputStream is = resource.getInputStream();
                    Properties properties = new Properties();
                    properties.load(is);
                    properties.forEach((key, value) -> messages.put((String) key, value));
                    saveToJsFile(messages, entry.getKey());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, List<Resource>> groupResourcesByLocale(Resource[] resources) {
        Pattern pattern = Pattern.compile("^.*_(\\w+)\\.properties$");
        return Arrays.stream(resources)
                .filter(rs -> {
                    String filename = rs.getFilename();
                    if (filename == null) return false;
                    return pattern.matcher(filename).matches();
                })
                .collect(Collectors.groupingBy(file -> {
                    String filename = file.getFilename();
                    Matcher matcher = pattern.matcher(filename);
                    matcher.find();
                    return matcher.group(1);
                }));
    }

    private void saveToJsFile(HashMap<String, Object> messages, String locale) {
        File file = new File(MESSAGE_DIR + "message_" + locale + ".js");
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("const MESSAGE_" + locale.toUpperCase() + " = ");
            writer.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messages));
            writer.write(";");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
