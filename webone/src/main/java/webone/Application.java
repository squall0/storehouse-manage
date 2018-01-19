package webone;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aboutdate.User;

@SpringBootApplication
@Controller
public class Application {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("token") == null) {
			return "redirect:/login/";
		}
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, HttpSession session,Model model) {
		System.out.println(user.getPassword());
//		model.addAttribute("tip", "密码错误");
//		session.setAttribute("token", "random");
//		return "login"
		return "redirect:/";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
