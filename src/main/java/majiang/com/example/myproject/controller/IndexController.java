package majiang.com.example.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//@Controller可以向浏览器传值
@Controller
public class IndexController {
    @GetMapping("/")
    public String hello() {
        return "index";
    }

}
