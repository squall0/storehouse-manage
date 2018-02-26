package com.webone.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	public String index(Model model, HttpSession session) {
		String token = session.getAttribute("token").toString();
		User user = userrepository.findBytoken(token).get(0);
		List<Tool> tools = new ArrayList<Tool>();
		tools.addAll(toolrepository.findByuserId(user.getId()));
		List<Storeroom> storerooms = new ArrayList<Storeroom>();
		storerooms.addAll(storepository.findByteamId(user.getTeamId()));
		model.addAttribute("name", user.getUsername());
		model.addAttribute("tools", tools);
		model.addAttribute("storerooms", storerooms);
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String find(@RequestParam("name") String name, @RequestParam("size") String size, HttpSession session,
			Model model) {

		int teamId = userrepository.findBytoken(session.getAttribute("token").toString()).get(0).getTeamId();
		Iterator<Storeroom> iterator = storepository.findByteamId(teamId).iterator();
		List<Integer> storeRoomList = new ArrayList<>();
		HashMap<Integer, String> roomname = new HashMap<>();
		Iterator<Storeroom> t = storepository.findAll().iterator();
		while (t.hasNext()) {
			Storeroom storeroom = (Storeroom) t.next();
			roomname.put(storeroom.getId(), storeroom.getName());
		}
		while (iterator.hasNext()) {
			Storeroom storeroom = (Storeroom) iterator.next();
			storeRoomList.add(storeroom.getId());

		}
		List<Tool> tools = toolrepository.findByNameLikeAndSize1LikeAndStoreroomIdIn("%" + name + "%", "%" + size + "%",
				storeRoomList);
		model.addAttribute("tools", tools);
		return "find";
	}

	@RequestMapping(value = "/backpack", method = RequestMethod.GET)
	@ResponseBody
	public List<Tool> backpack(Model model, HttpSession session) {
		String token = session.getAttribute("token").toString();
		int id = userrepository.findBytoken(token).get(0).getId();
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
			Integer token = (int) (Math.random() * 1000000);
			userDb.setToken(token.toString());
			userrepository.save(userDb);
			session.setAttribute("token", token.toString());
		}
		return "redirect:/";
	}

	@RequestMapping("/storeroom")
	@ResponseBody
	public List<Tool> storeroom(@RequestParam("name") String name,Model modle ) {
		//if(name.equals("选择仓库"))
		//	return "null";
		int roomid = storepository.findByname(name).get(0).getId();
		return toolrepository.findByStoreroomIdAndUserId(roomid, 0);
		//modle.addAttribute("tools",toolrepository.findByStoreroomIdAndUserId(roomid, 0));
		//return "test";
	}

	@RequestMapping("/take")
	@ResponseBody
	public void take(@RequestParam("id") int toolid, HttpSession session) {
		String token = session.getAttribute("token").toString();
		int id = userrepository.findBytoken(token).get(0).getId();
		Tool tool = toolrepository.findOne(toolid);
		tool.setUserId(id);
		toolrepository.save(tool);
	}

	// 被拿取的工具不显示
	@RequestMapping("/bring")
	@ResponseBody
	public void bring(@RequestParam("id") int toolid,
			@RequestParam(value = "roomid", required = false) Integer storeroomId) {
		Tool tool = toolrepository.findOne(toolid);
		tool.setUserId(0);
		if (storeroomId != null)
			tool.setStoreroom(storepository.findOne(storeroomId));
		toolrepository.save(tool);
	}
}
