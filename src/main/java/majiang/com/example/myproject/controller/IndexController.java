package majiang.com.example.myproject.controller;

import majiang.com.example.myproject.dto.QuestionDTO;
import majiang.com.example.myproject.mapper.QuestionMapper;
import majiang.com.example.myproject.model.Question;
import majiang.com.example.myproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@Controller可以向浏览器传值
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(
            Model model
    ) {
        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions", questionDTOList);
        return "index";

    }

}
