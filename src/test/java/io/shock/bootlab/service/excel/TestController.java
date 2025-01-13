package io.shock.bootlab.service.excel;

import io.shock.bootlab.entity.Member;
import io.shock.bootlab.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final MemberService memberService;

    public TestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/test")
    public List<Member> test() {
        return memberService.getMembers();
    }
}
