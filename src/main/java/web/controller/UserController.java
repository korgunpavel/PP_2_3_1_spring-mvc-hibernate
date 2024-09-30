package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(ModelMap model) {
        model.addAttribute("users", userService.index());

        return "index";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());

        return "show";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "show";
        }
        if (user.getId() == null) {
            userService.save(user);
        } else {
            userService.update(user.getId(), user);
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, ModelMap model) {
        User user = userService.show(id);
        model.addAttribute("user", user);
        return "show";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }

}