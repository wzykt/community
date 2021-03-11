package wzy.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wzy.com.demo.dto.AccessTokenDTO;
import wzy.com.demo.dto.GitHubUser;
import wzy.com.demo.pojo.User;
import wzy.com.demo.provider.GithubProvider;
import wzy.com.demo.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    //HttpServletRequest由SpringBoot，从上下文中拿取到，并放入方法参数中
    @RequestMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = githubProvider.getGitHubUser(accessToken);
        System.out.println(gitHubUser);
        if (gitHubUser != null) {
            User user = userService.insertUser(gitHubUser.getName(), String.valueOf(gitHubUser.getId()));
            response.addCookie(new Cookie("token",user.getToken()));
            //重定向到首页
            return "redirect:/";
        }
        return "redirect:/";
    }
}
