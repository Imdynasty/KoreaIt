package controllers.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/join")
public class JoinController {
    @GetMapping
    public String join(){
        return "/member/join";
    }

    @PostMapping
    public String joinPs(){
        return "redirect:/member/login";
    }
}
