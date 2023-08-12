package Novoe.LoyaltySystem.controller;

import Novoe.LoyaltySystem.model.Status;
import Novoe.LoyaltySystem.model.User;
import Novoe.LoyaltySystem.service.ForgotService;
import Novoe.LoyaltySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/forgot")
public class ForgotController {

    @Autowired
    ForgotService forgotService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String index() {
        return "forgot/enteremail";
    }

    @PostMapping(value = "/email")
    public String email(
            Model model,
            @RequestParam("email") String email) {
        User response = userService.loadUserByUsername(email);
        if(response!=null){
            forgotService.sendLink(email);
            return "forgot/ok";
        }
        model.addAttribute("error",true);
        return "forgot/enteremail";
    }

    @GetMapping(value = "/change_password/{token}/{email}")
    public String changePassword(
            @PathVariable String token,
            @PathVariable String email,
            Model model){
    if(forgotService.outOfTime(token)== Status.SUCCESS){
        model.addAttribute("email", email);
        return "forgot/changepassword";
    } else {
        return "forgot/timeout";
    }
    }

    @PostMapping(value = "/new_password")
    public String newPassword(@RequestParam String password,
                              @RequestParam String email){
        userService.deletePassword(email,password);
        return "redirect:/";
    }
}
