package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "nm",required = false) String name, int num, boolean result) {
        System.out.println(name +"," + num+ "," +result);
        return "hello"; // /WEB-INF/view/hello.jsp
    }
}