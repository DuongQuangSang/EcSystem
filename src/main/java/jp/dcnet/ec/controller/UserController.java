package jp.dcnet.ec.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.dcnet.ec.obj.ProductDTO;
import jp.dcnet.ec.obj.UserDTO;
import jp.dcnet.ec.service.ProductService;
import jp.dcnet.ec.service.UserNotFoundException;
import jp.dcnet.ec.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@GetMapping("/login")
	// ユーザーページを表示するためのメソッド
	public String viewUserPage() {
		return "user/login";
	}
	
	@GetMapping("/login-method")
	// ログインメソッド
	public String login(@RequestParam("username") String username,
									@RequestParam("password") String password,
									HttpSession session,
									Model model) {
		UserDTO userDTO = userService.login(username, password);
		
		if (userDTO != null) {
			// ログイン成功の処理
			session.setAttribute("username", username);
			session.setAttribute("userId", userDTO.getUserId());
			model.addAttribute("userDTO", userDTO);
			return "user/user";
		} else {
			// ログイン失敗の処理
			return "user/login";
		}
	}
	
	@GetMapping("/user/{username}")
	// ユーザーページを取得するためのメソッド
	public String getUserPage(@PathVariable("username") String username, Model model) {
	    UserDTO userDTO = userService.getUserByUserName(username);
	    model.addAttribute("userDTO", userDTO);
	    return "user";
	}
	
	
	@GetMapping("/change-password")
	// ユーザーパスワード変更ページを表示するためのメソッド
	public String viewUserPasswordChange(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		Long userId = (Long) session.getAttribute("userId");
		session.setAttribute("username", username);
		session.setAttribute("userId", userId);
		return "user/user_change_password";
	}
	
	@PostMapping("/change_password_method")
	// ユーザーパスワード変更メソッド
	public String changeUserPassWord(@RequestParam("password") String password,
									@RequestParam("shinpassword") String shinpassword,
									@RequestParam("kakupassword") String kakupassword,
									HttpSession session,
									RedirectAttributes redirectAttributes) {
		String username = (String) session.getAttribute("username");
		Long userId = (Long) session.getAttribute("userId");
		try {
	        userService.updatePassword(userId, password, shinpassword);
	        redirectAttributes.addFlashAttribute("successMessage", "パスワードが正常に更新されました");
	    } catch (UserNotFoundException e) {
	    	redirectAttributes.addFlashAttribute("errorMessage", "ユーザーが見つかりませんでした");
	    }
		return "redirect:/user/"+username;
	}
	
	@PostMapping("/userSave")
	// ユーザー情報保存メソッド
	public String saveUser(UserDTO userDTO, RedirectAttributes redirectAttributes,
			@RequestParam("username") String username) {
		try {
	        userService.updateUser(userDTO);
	        redirectAttributes.addFlashAttribute("successMessage", "ユーザー情報が正常に更新されました");
	    } catch (UserNotFoundException e) {
	    	redirectAttributes.addFlashAttribute("errorMessage", "ユーザーが見つかりませんでした");
	    }
		return "redirect:/"+ username;
	}
	
	@GetMapping("/signUp")
	// サインアップページを表示するためのメソッド
	public String signUpView() {
		return "signup";
	}
	
	@PostMapping("/creatUser")
	// ユーザー作成フォームを保存するためのメソッド
	public String saveUserForm(@RequestParam("username") String username,
							   @RequestParam("password") String password,
							   @RequestParam("kakuninpassword") String kakuninpassword,
							   @RequestParam("email") String email,
							   @RequestParam("firstname") String firstname,
							   @RequestParam("lastname") String lastname,
							   @RequestParam("address") String address,
							   @RequestParam("phonenumber") String phoneNumber,
							   RedirectAttributes redirectAttributes) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setEmail(email);
		userDTO.setFirstname(firstname);
		userDTO.setLastname(lastname);
		userDTO.setAddress(address);
		userDTO.setPhonenumber(phoneNumber);
		if (password.contentEquals(kakuninpassword) && userService.isUserNameExist(username)) {
			try {
				userService.createUser(userDTO);
				redirectAttributes.addFlashAttribute("successMessage", "ユーザーが正常に作成されました");
			} catch (UserNotFoundException e) {
				redirectAttributes.addFlashAttribute("errorMessage", "ユーザーの作成に失敗しました");
			}
			return "redirect:/user/"+ username;
		}
		redirectAttributes.addFlashAttribute("errorMessage", "ユーザーの作成に失敗しました");
		return "signup";
	}
	
	@GetMapping("/top_page")
	public String showTopPage(Model model) {
		List<ProductDTO> arrivalProductDTO = productService.getArrivalProducts();
		model.addAttribute("arrivalProductDTO",arrivalProductDTO);
		
		List<ProductDTO> suggestProductDTO = productService.getSuggestProducts();
		model.addAttribute("suggestProductDTO",suggestProductDTO);
        return "user/top_page";
	}
	
	@GetMapping("/")
	public String showIndex() {
		return "user/index";
	}
	
	@PostMapping("/search")
	public String searchProductResults(@RequestParam("searchTerm") String searchTerm, Model model) {
		List<ProductDTO> searchResult = productService.searchProductByName(searchTerm);
		model.addAttribute("listProductDTO", searchResult);
		return "user/index";
	}
	
}


