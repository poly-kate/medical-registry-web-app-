package medregistry.controller;

import medregistry.entity.User;
import medregistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(User user){
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String regUser(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            Model model
    )
    {
        if (bindingResult.hasErrors()){
            return "registration";
        }

        /*if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("confirmError", "Пароли не совпадают");
            return "registration";
        }*/

        if (!userService.saveUser(user)){
            model.addAttribute("usernameError",
                    "Пользователь с данным логином уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
