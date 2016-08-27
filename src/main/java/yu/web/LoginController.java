package yu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yu.service.DirService;
import yu.service.RegisterService;

import java.security.Principal;

@Controller
public class LoginController {

    private final RegisterService registerService;


    @Autowired
    public LoginController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        System.out.println("login");
        if(principal != null) {
            return "redirect:/id/0";
        }
        return "login";
    }


    @PostMapping("/register")
    public String register(Principal principal, Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        if(principal != null) {
            return "redirect:/id/0";
        }

        if(registerService.checkUsername(username)) {
            registerService.addUser(username, password);
            model.addAttribute("message", "注册成功");
        } else {
            model.addAttribute("message", "用户名已存在");
        }
        return "login";
    }
}
