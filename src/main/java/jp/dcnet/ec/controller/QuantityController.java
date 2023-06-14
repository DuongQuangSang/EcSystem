package jp.dcnet.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ec.obj.QuantityDTO;
import jp.dcnet.ec.service.ProductService;
import jp.dcnet.ec.service.QuantityService;

@Controller
@RequestMapping("/admin")
public class QuantityController {
	@Autowired
	private QuantityService quantityService;
	@Autowired
	private ProductService productService;

	@GetMapping("/quantity")
	// 数量ページを表示するためのメソッド
	public String viewQuantityPage(Model model) {
		List<QuantityDTO> listQuantityDTO = quantityService.getAllProducts();
		model.addAttribute("listQuantityDTO",listQuantityDTO);
		return "admin/quantity";
	}
	

	// 商品のを検索するためのメソッド
	@PostMapping("/quantity-search/{productId}")
	public ModelAndView searchProductQuantity(@PathVariable(name = "productId") Long productId) {
		ModelAndView mav = new ModelAndView("admin/quantity-search");
		if (productId != null) {
		QuantityDTO searchResult = quantityService.findQuantityByProductId(productId);
		mav.addObject("listQuantityDTO", searchResult);
		}
		return mav;
	}
	
//	@PostMapping("/login-method")
//	// ログインメソッド
//	public String login(@RequestParam("username") String username,
//			@RequestParam("password") String password,
//			HttpSession session,
//			Model model,
//			RedirectAttributes redirectAttributes) {
//		try {
//			UserDTO userDTO = userService.login(username, password);
//			if (userDTO != null) {
//				// ログイン成功の処理
//				session.setAttribute("username", username);
//				session.setAttribute("userId", userDTO.getUserId());
//				model.addAttribute("userDTO", userDTO);
//				return "user/user-info";
//			} else {
//				// ログイン失敗の処理
//				redirectAttributes.addFlashAttribute("errorMessage", "ログインエラー");
//				return "redirect:/user/login";
//			}
//		} catch (BadRequestException e) {
//			// Handle bad request exception
//			redirectAttributes.addFlashAttribute("errorMessage", "ログインエラー");
//			return "redirect:/user/login";
//		}
//	}
//
//	@GetMapping("/user/{username}")
//	// ユーザーページを取得するためのメソッド
//	public String getUserPage(@PathVariable("username") String username, Model model) {
//		UserDTO userDTO = userService.getUserByUserName(username);
//		model.addAttribute("userDTO", userDTO);
//		return "user/user-info/" + username;
//	}
//
//	@GetMapping("/change-password")
//	// ユーザーパスワード変更ページを表示するためのメソッド
//	public String viewUserPasswordChange(HttpSession session, Model model) {
//		String username = (String) session.getAttribute("username");
//		Long userId = (Long) session.getAttribute("userId");
//		session.setAttribute("username", username);
//		session.setAttribute("userId", userId);
//		return "user/user_change_password";
//	}
//
//	@PostMapping("/change-password-method")
//	// ユーザーパスワード変更メソッド
//	public String changeUserPassWord(@RequestParam("password") String password,
//			@RequestParam("shinpassword") String shinpassword,
//			@RequestParam("kakupassword") String kakupassword,
//			HttpSession session,
//			RedirectAttributes redirectAttributes,
//			Model model) {
//		String username = (String) session.getAttribute("username");
//		Long userId = (Long) session.getAttribute("userId");
//		UserDTO userDTO = userService.login(username, password);
//		try {
//			userService.updatePassword(userId, password, shinpassword);
//			redirectAttributes.addFlashAttribute("successMessage", "パスワードが正常に更新されました");
//		} catch (UserNotFoundException e) {
//			redirectAttributes.addFlashAttribute("errorMessage", "ユーザーが見つかりませんでした");
//		}
//
//		if (userDTO != null) {
//			// ログイン成功の処理
//			session.setAttribute("username", username);
//			session.setAttribute("userId", userDTO.getUserId());
//			model.addAttribute("userDTO", userDTO);
//		}
//		return "user/user-info";
//	}
//
//	@PostMapping("/user-save")
//	// ユーザー情報保存メソッド
//	public String saveUser(UserDTO userDTO,
//			RedirectAttributes redirectAttributes,
//			HttpSession session,
//			Model model,
//			@RequestParam("username") String username) {
//		try {
//			userService.updateUser(userDTO);
//			redirectAttributes.addFlashAttribute("successMessage", "ユーザー情報が正常に更新されました");
//		} catch (UserNotFoundException e) {
//			redirectAttributes.addFlashAttribute("errorMessage", "ユーザーが見つかりませんでした");
//		}
//		if (userDTO != null) {
//			// ログイン成功の処理
//			session.setAttribute("username", username);
//			session.setAttribute("userId", userDTO.getUserId());
//			model.addAttribute("userDTO", userDTO);
//		}
//		return "user/user-info";
//	}
//
//	@GetMapping("/sign-up")
//	// サインアップページを表示するためのメソッド
//	public String signUpView() {
//		return "user/signup";
//	}
//
//	@PostMapping("/creat-user")
//	// ユーザー作成フォームを保存するためのメソッド
//	public String saveUserForm(@RequestParam("username") String username,
//			@RequestParam("password") String password,
//			@RequestParam("kakuninpassword") String kakuninpassword,
//			@RequestParam("email") String email,
//			@RequestParam("firstname") String firstname,
//			@RequestParam("lastname") String lastname,
//			@RequestParam("address") String address,
//			@RequestParam("phonenumber") String phoneNumber,
//			RedirectAttributes redirectAttributes) {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setUsername(username);
//		userDTO.setPassword(password);
//		userDTO.setEmail(email);
//		userDTO.setFirstname(firstname);
//		userDTO.setLastname(lastname);
//		userDTO.setAddress(address);
//		userDTO.setPhonenumber(phoneNumber);
//		if (password.contentEquals(kakuninpassword) && userService.isUserNameExist(username)) {
//			try {
//				userService.createUser(userDTO);
//				redirectAttributes.addFlashAttribute("successMessage", "ユーザーが正常に作成されました");
//			} catch (UserNotFoundException e) {
//				redirectAttributes.addFlashAttribute("errorMessage", "ユーザーの作成に失敗しました");
//			}
//			return "redirect:/user/" + username;
//		}
//		redirectAttributes.addFlashAttribute("errorMessage", "ユーザーの作成に失敗しました");
//		return "signup";
//	}
//
//	@GetMapping("/top-page")
//	public String showTopPage(Model model) {
//		List<ProductDTO> arrivalProductDTO = productService.getArrivalProducts();
//		model.addAttribute("arrivalProductDTO", arrivalProductDTO);
//
//		List<ProductDTO> suggestProductDTO = productService.getSuggestProducts();
//		model.addAttribute("suggestProductDTO", suggestProductDTO);
//		return "user/top_page";
//	}
//
//	@GetMapping("/")
//	public String showIndex(Model model) {
//		List<ProductDTO> listProductDTO = productService.getAllProducts();
//		model.addAttribute("listProductDTO", listProductDTO);
//		return "user/index";
//	}
//
//	// 商品を編集するためのメソッド
//	@PostMapping("/info/{productId}")
//	public ModelAndView editProduct(@PathVariable("productId") long productId) {
//		ModelAndView mav = new ModelAndView("user/product_info");
//
//		ProductDTO productDTO = productService.getProductById(productId);
//		mav.addObject("product", productDTO);
//
//		return mav;
//	}
//
//	// 商品を検索するためのメソッド
//	@PostMapping("/search")
//	public String searchProductResults(@RequestParam("searchTerm") String searchTerm, Model model) {
//		List<ProductDTO> searchResult = productService.searchProductByName(searchTerm);
//		model.addAttribute("listProductDTO", searchResult);
//		return "user/index";
//	}
//
//	@GetMapping("/order")
//	public String order(Model model) {
//		List<ProductDTO> listProductDTO = productService.getAllProducts();
//		model.addAttribute("listProductDTO", listProductDTO);
//		return "user/order";
//	}
}
