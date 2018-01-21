package com.webone.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webone.domain.User;
import com.webone.repository.UserJpaRepository;

@Controller
public class UserController {
	@Autowired
	private UserJpaRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) {
		
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, HttpSession session, Model model) {
		// model.addAttribute("tip", "密码错误");
		User userDb = repository.findByusername(user.getUsername()).get(0);
		if (user.getPassword().equals(userDb.getPassword())) {
			Integer a = (int) (Math.random() * 1000000);
			userDb.setToken(a.toString());
			repository.save(userDb);
			session.setAttribute("token", a.toString());
		}

		// return "login"
		return "redirect:/";
	}

}
