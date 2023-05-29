package jp.dcnet.ec.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.dcnet.ec.obj.UserDTO;
import jp.dcnet.ec.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String viewUserPage() {
		return "login";
	}
	
	@PostMapping("/login-method")
	public String login(@RequestParam("username") String username,
								   @RequestParam("password") String password,
								   HttpSession session,
								   Model model) {
		UserDTO userDTO = userService.login(username, password);
		model.addAttribute("userDTO", userDTO);
		if (userDTO != null) {
			// Xử lý đăng nhập thành công
			session.setAttribute("username", username);
			return "user";
		} else {
			// Xử lý đăng nhập không thành công
			return "login";
		}
	}
	
	
	@GetMapping("/change_password_page")
	public String viewUserPasswordChange(HttpSession session,Model model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		return "user_change_password";
	}
}
