package majiang.com.example.myproject.controller;

import majiang.com.example.myproject.dto.AccessTokenDTO;
import majiang.com.example.myproject.dto.GithubUser;
import majiang.com.example.myproject.mapper.UserMapper;
import majiang.com.example.myproject.model.User;
import majiang.com.example.myproject.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state) {
        AccessTokenDTO accessTikenDTO = new AccessTokenDTO();
        accessTikenDTO.setClient_id("7fa453e944eff08606b4");
        accessTikenDTO.setClient_select("4430c4d60ff4e6f56b8fd7233b3bda98d45bbb22");
        accessTikenDTO.setCode(code);
        accessTikenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTikenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTikenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        User modelUser = new User();
        modelUser.setToken(UUID.randomUUID().toString());
        modelUser.setName("fudejun");
        modelUser.setGmtCreate(System.currentTimeMillis());
        modelUser.setGmtModified(modelUser.getGmtCreate());
        modelUser.setAccountId("accounted");
        userMapper.insert(modelUser);
        System.out.println();

//        System.out.println("authorize" + user.getName());
        return "index";
    }
}
