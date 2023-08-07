package controllers.member;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/join")
    public String join(){

        return "member/join"; // /WEB-INF/templates/member/join.html
    }
    @PostMapping("/join")
    public String joinPs(JoinForm joinForm){
        System.out.println(joinForm);
        return "redirect:/member/login"; //페이지 이동 응답헤더 - location : 주소
    }
   /* @GetMapping("/login")
    public String login(Model model){
       // model.addAttribute("userId","user01");
        //model.addAttribute("userNm", "JACK");

        Map<String , Object> attrs = new HashMap<>();
        attrs.put("userId","user02");
        attrs.put("userNm","osm");
                model.addAllAttributes(attrs);
        return "member/login";
    }*/
}
