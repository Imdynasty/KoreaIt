package controllers.member;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/login")
public class LoginController {

    @GetMapping()
    public String login() {

        return "member/login";
    }

    @PostMapping()
    public String loginPs(){

        return "redirect:/mypage"; //페이지 이동 응답헤더 - location : 주소
    }
}
