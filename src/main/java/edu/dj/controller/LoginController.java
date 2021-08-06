package edu.dj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/toLoginOut")
    public String toLoginOut(HttpSession session){
        session.removeAttribute("logined");
        return "redirect:/index.jsp";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session){
        if (username.equals("admin") && password.equals("123456")){
            session.setAttribute("logined",username);
            return "redirect:/book/allBook";
        }

        return "login";
    }
}
