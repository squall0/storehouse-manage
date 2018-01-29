package com.webone.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webone.domain.Storeroom;
import com.webone.domain.Tool;
import com.webone.domain.User;
import com.webone.repository.StoJpaRepository;
import com.webone.repository.ToolJpaRepository;
import com.webone.repository.UserJpaRepository;

@Controller
public class UserController {
	@Autowired
	private UserJpaRepository userrepository;
	@Autowired
	private ToolJpaRepository toolrepository;
	@Autowired
	private StoJpaRepository storepository;

	@RequestMapping("/")
	public String index(Model model,HttpSession session) {
		String token=session.getAttribute("token").toString();
		int id=userrepository.findBytoken(token).get(0).getId();
		List<Tool> tools=new ArrayList<Tool>();		
		tools.addAll(toolrepository.findByuserId(id));
		List<Storeroom> storerooms=new ArrayList<Storeroom>();
		storerooms.addAll(storepository.findAll());
		model.addAttribute("tools", tools);
		model.addAttribute("storerooms",storerooms);
		return "index";
	}
	
	@RequestMapping(value = "/backpack", method = RequestMethod.GET)
	@ResponseBody
	public List<Tool> backpack(Model model, HttpSession session) {
		String token=session.getAttribute("token").toString();
		int id=userrepository.findBytoken(token).get(0).getId();
		return toolrepository.findByuserId(id);
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, HttpSession session) {
		// model.addAttribute("tip", "密码错误");
		User userDb = userrepository.findByusername(user.getUsername()).get(0);
		if (user.getPassword().equals(userDb.getPassword())) {
			Integer a = (int) (Math.random() * 1000000);
			userDb.setToken(a.toString());
			userrepository.save(userDb);
			session.setAttribute("token", a.toString());
		}

		// return "login"
		return "redirect:/";
	}
	@RequestMapping("/storeroom")
	@ResponseBody
	public List<Tool> storeroom(@RequestParam("name") String name){
		int id=storepository.findByname(name).get(0).getId();
		return toolrepository.findBystoreroomId(id);
	}
}
