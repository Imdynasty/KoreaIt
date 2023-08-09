package controllers.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.members.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/join")
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping
    public String join(@ModelAttribute JoinForm form){

        return "/member/join";
    }

    @PostMapping
    public String joinPs(@Valid JoinForm form, Errors errors){

        joinService.join(form, errors);

        if(errors.hasErrors()){
            return "member/join";
        }

        return "redirect:/member/login";
    }

    //컨트롤러 수준의 공통 Validator()
    /*
    @InitBinder
    public void InitBinder(WebDataBinder binder){
        binder.setValidator(joinValidator);
    }*/
}
