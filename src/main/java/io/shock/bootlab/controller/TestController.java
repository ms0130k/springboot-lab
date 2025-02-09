package io.shock.bootlab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @GetMapping("/log")
    public String log(@RequestParam String index) {
        for (int i = 0; i < 10000; i++) {  // 빠르게 로그 생성
            log.error(index + "테스트 로그 메시지: " + i);
        }
        System.out.println("로그 생성 완료!");
        return "log";
    }
}
