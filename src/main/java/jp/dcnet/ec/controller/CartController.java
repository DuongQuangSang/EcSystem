package jp.dcnet.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dcnet.ec.obj.ProductDTO;
import jp.dcnet.ec.service.CartService;

@Controller
@RequestMapping("/user")
public class CartController {
	 @Autowired
	    private CartService cartService;
	 
	 @PostMapping("/cart/add")
	    public String addToCart(@ModelAttribute("products") List<ProductDTO> products) {
	        // Lưu thông tin các sản phẩm vào giỏ hàng
	        cartService.addProduct(products);
	        
	        return "redirect:/cart"; // Chuyển hướng đến trang giỏ hàng
	    }
}
