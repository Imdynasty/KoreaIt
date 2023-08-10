package controllers.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberController {
    @GetMapping("/member/info/{id}/{num}")
    public String info(@PathVariable(name="id", required = false) String userId
    ,@PathVariable int num){
        System.out.printf("userId : %s%n , num =%d%n", userId,num);
        return "member/info";
    }

    @GetMapping("/member/list")
    public String list(@ModelAttribute SearchForm searchForm, Errors errors){
        String str =null;
        str.toUpperCase();
        return "member/list";
    }
/*
    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model){
        model.addAttribute("message",e.getMessage());
        e.printStackTrace();
        return "error/index";
    }*/


}
