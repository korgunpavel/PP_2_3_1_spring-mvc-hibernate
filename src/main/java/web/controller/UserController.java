package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDaoImp;
import web.model.User;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	private final UserServiceImp userServiceImp;

	@Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/")
	public String users(ModelMap model) {
		model.addAttribute("users", userServiceImp.index());

		return "index";
	}

	@GetMapping("/new")
	public String newUser(ModelMap model) {
		model.addAttribute("user", new User());

		return "show";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user) {
		if (user.getId() == null) {
			userServiceImp.save(user);
		} else {
			userServiceImp.update(user.getId(), user);
		}
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam(name = "id") Long id, ModelMap model) {
		User user = userServiceImp.show(id);
		model.addAttribute("user", user);
		return "show";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam(name = "id") Long id) {
		userServiceImp.delete(id);
		return "redirect:/";
	}

}