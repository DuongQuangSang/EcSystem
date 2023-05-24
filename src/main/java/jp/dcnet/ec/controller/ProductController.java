package jp.dcnet.ec.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ec.model.ProductEntity;
import jp.dcnet.ec.obj.Product;
import jp.dcnet.ec.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public ModelAndView viewAdminPage(Model model) {
		List<ProductEntity> listProduct = productService.getAllProducts();
		model.addAttribute("listProduct", listProduct);
		
		ModelAndView mav = new ModelAndView("qw.html");
		return mav;
	}

	@RequestMapping("/detail")
	public String viewDetail(Model model) {
		List<ProductEntity> listProduct = productService.getAllProducts();
		model.addAttribute("listProduct", listProduct);
		return "detail";
	}

	@RequestMapping("/new")
	public String newProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_form";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute("product") ProductEntity product) {
		productService.saveProduct(product);
		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") ProductEntity product) {
		productService.update(product);
		return "redirect:/";
	}

	//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	//	public String deleteProduct(@ModelAttribute("product") Product product) {
	//		productService.deleteProduct(product.getProduct_id());
	//		return "redirect:/";
	//    }

	@RequestMapping("/edit/{product_id}")
	public ModelAndView editProduct(@PathVariable(name = "product_id") int product_id) {
		ModelAndView mav = new ModelAndView("edit");

		ProductEntity product = productService.getProductById((long) product_id);
		mav.addObject("product", product);

		return mav;
	}

	@RequestMapping("/delete/{product_id}")
	public String deleteProduct(@PathVariable(name = "product_id") int product_id) {
		productService.deleteProduct((long) product_id);

		return "redirect:/";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchProductResults(@RequestParam("searchTerm") String searchTerm, Model model) {
		List<ProductEntity> searchResult = productService.searchProductByName(searchTerm);
		model.addAttribute("listProduct", searchResult);
		return "index";
	}

	@RequestMapping("/show")
	public String showProduct(Model model) {
		LocalDateTime currentTime = LocalDateTime.now();
		List<ProductEntity> listProduct = productService.searchProductByTimeRange(currentTime);
		model.addAttribute("listProduct", listProduct);
		return "index";
	}

	@RequestMapping("/attribute")
	public String viewAttribute(Model model) {
		List<ProductEntity> listProduct = productService.getAllProducts();
		model.addAttribute("listProduct", listProduct);
		return "attribute";
	}

	@RequestMapping("/attribute-chip")
	public String viewAttributeChip(Model model) {
		List<ProductEntity> listProduct = productService.sortByAttributeName("プロセッサー");
		model.addAttribute("listProduct", listProduct);
		return "attribute";
	}

	@RequestMapping("/attribute-color")
	public String viewAttributeColor(Model model) {
		List<ProductEntity> listProduct = productService.sortByAttributeName("色");
		model.addAttribute("listProduct", listProduct);
		return "attribute";
	}

	@RequestMapping("/attribute-size")
	public String viewAttributeSize(Model model) {
		List<ProductEntity> listProduct = productService.sortByAttributeName("サイズ");
		model.addAttribute("listProduct", listProduct);
		return "attribute";
	}

	@GetMapping("/attribute-edit/{product_id}")
	public ModelAndView editAttribute(@PathVariable(name = "product_id") int product_id) {
		ModelAndView mav = new ModelAndView("editAttribute");

		ProductEntity product = productService.getProductById((long) product_id);
		mav.addObject("product", product);

		List<String> attributeOptions = Arrays.asList("サイズ", "色", "プロセッサー");
		List<String> sizeOptions = Arrays.asList("13", "14", "15.6");
		List<String> colorOptions = Arrays.asList("黒", "白", "グレー");
		List<String> processorOptions = Arrays.asList("Intel", "Ryzen");
		mav.addObject("attributeOptions", attributeOptions);
		mav.addObject("sizeOptions", sizeOptions);
		mav.addObject("colorOptions", colorOptions);
		mav.addObject("processorOptions", processorOptions);

		return mav;
	}

	@RequestMapping("/attribute-delete/{product_id}")
	public String deleteAttribute(@PathVariable(name = "product_id") int product_id) {
		productService.deleteProduct((long) product_id);

		return "redirect:/";
	}

	@RequestMapping(value = "/attribute-insert", method = RequestMethod.POST)
	public String insertAttribute(@ModelAttribute("product") ProductEntity product) {
		productService.saveProduct(product);
		return "redirect:/";
	}

	@RequestMapping("/custom-error")
	public String handleError() {
		// Xử lý lỗi tại đây (ví dụ: gửi thông báo lỗi, chuyển hướng tới trang lỗi tùy chọn, vv.)
		return "error";
	}
}
