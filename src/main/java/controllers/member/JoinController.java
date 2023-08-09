package controllers.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/join")
@RequiredArgsConstructor
public class JoinController {
    private final JoinValidator joinValidator;
    @GetMapping
    public String join(@ModelAttribute JoinForm form){

        return "/member/join";
    }

    @PostMapping
    public String joinPs(JoinForm joinForm, Errors errors){

        joinValidator.validate(joinForm, errors);

        if(errors.hasErrors()){
            return "member/join";
        }

        return "redirect:/member/login";
    }
}
