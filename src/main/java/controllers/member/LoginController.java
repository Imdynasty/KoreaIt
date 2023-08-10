package controllers.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.members.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String login(@ModelAttribute LoginForm loginForm, @CookieValue(required = false) String saveId){
        if(saveId != null){//저장된 userId가 있는 경우
            loginForm.setSaveId(true);
            loginForm.setUserId(saveId);
        }
        return "member/login";
    }
    @PostMapping
    public String loginPs(@Valid LoginForm loginForm, Errors errors){

        loginService.login(loginForm,errors);

        if(errors.hasErrors()){
            return "member/login";
        }

        return "redirect:/";
    }
}
