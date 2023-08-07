package controllers.member;


import models.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("title","변경된 제목!");
        return "member/join"; // /WEB-INF/templates/member/join.html
    }
    @PostMapping("/join")
    public String joinPs(JoinForm joinForm){
        System.out.println(joinForm);
        return "redirect:/member/login"; //페이지 이동 응답헤더 - location : 주소
    }


     @GetMapping("/info")
    public String info(Model model){
    Member member= Member.builder()
                 .userNo(1)
                 .userId("user01")
                 .userPw("12345678")
                 .userNm("사용자10")
                 .email("user01@test.org")
                 .mobile("010000000000")
                 .regDt(LocalDateTime.now())
                 .build();
        model.addAttribute("member", member);
        return "member/info"; // /WEB-INF/templates/member/info.html
     }
}
