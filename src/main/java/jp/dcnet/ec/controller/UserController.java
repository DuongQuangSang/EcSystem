package jp.dcnet.ec.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.dcnet.ec.obj.UserDTO;
import jp.dcnet.ec.service.UserNotFoundException;
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
		
		if (userDTO != null) {
			// Xử lý đăng nhập thành công
			session.setAttribute("username", username);
			session.setAttribute("userId", userDTO.getUserId());
			model.addAttribute("userDTO", userDTO);
			return "user";
		} else {
			// Xử lý đăng nhập không thành công
			return "login";
		}
	}
	
	@GetMapping("/user/{username}")
	public String getUserPage(@PathVariable("username") String username, Model model) {
	    UserDTO userDTO = userService.getUserByUserName(username);
	    model.addAttribute("userDTO", userDTO);
	    return "user";
	}
	
	
	@GetMapping("/change_password")
	public String viewUserPasswordChange(HttpSession session,Model model) {
		String username = (String) session.getAttribute("username");
		Long userId = (Long) session.getAttribute("userId");
		session.setAttribute("username", username);
		session.setAttribute("userId", userId);
//		model.addAttribute("username", username);
//		model.addAttribute("userId", userId);
		return "user_change_password";
	}
	
	@PostMapping("/change_password_method")
	public String  changeUserPassWord(@RequestParam("password") String password,
//															@RequestParam("username") String username,
			 												@RequestParam("shinpassword") String shinpassword,
															@RequestParam("kakupassword") String kakupassword,
															HttpSession session,
															RedirectAttributes redirectAttributes) {
		String username = (String) session.getAttribute("username");
		Long userId = (Long) session.getAttribute("userId");
		try {
	        userService.updatePassword(userId, password, shinpassword);
	        redirectAttributes.addFlashAttribute("successMessage", "Password updated successfully");
	    } catch (UserNotFoundException e) {
	    	redirectAttributes.addFlashAttribute("errorMessage", "User not found");
	    }
		return "redirect:/user/"+username;
	}
	
	@PostMapping("/user_save")
	public String saveUser(UserDTO userDTO,RedirectAttributes redirectAttributes,
			@RequestParam("username") String username) {
		try {
	        userService.updateUser(userDTO);
	        redirectAttributes.addFlashAttribute("successMessage", "Password updated successfully");
	    } catch (UserNotFoundException e) {
	    	redirectAttributes.addFlashAttribute("errorMessage", "User not found");
	    }
		return "redirect:/user/"+ username;
	}
}
