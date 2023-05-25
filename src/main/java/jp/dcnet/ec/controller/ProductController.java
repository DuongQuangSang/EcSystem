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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ec.obj.ProductDTO;
import jp.dcnet.ec.service.ProductService;

@Controller	
public class ProductController {
	@Autowired
	private ProductService productService;

//	@GetMapping("/")
//	public ModelAndView viewAdminPage(Model model) {
//		List<ProductEntity> listProduct = productService.getAllProducts();
//		model.addAttribute("listProduct", listProduct);
//		
//		ModelAndView mav = new ModelAndView("qw.html");
//		return mav;
//	}
	
	@GetMapping("/")
    public String viewAdminPage(Model model) {
		List<ProductDTO> listProductDTO = productService.getAllProducts();
		model.addAttribute("listProductDTO",listProductDTO);
        return "index";
    }

//	@RequestMapping("/detail")
//	public String viewDetail(Model model) {
//		List<ProductEntity> listProduct = productService.getAllProducts();
//		model.addAttribute("listProduct", listProduct);
//		return "detail";
//	}
//
	@PostMapping(value = "/new")
	public String newProduct(Model model) {
		ProductDTO productDTO = new ProductDTO();
		model.addAttribute("product", productDTO);
		return "new_form";
	}

	@PostMapping(value = "/insert")
	public String insertProduct(@ModelAttribute("product") ProductDTO productDTO) {
		productService.saveProduct(productDTO);
		return "redirect:/";
	}

//	@PostMapping(value = "/update")
//	public String updateProduct(@ModelAttribute("product") ProductDTO productDTO) {
//		productService.update(productDTO);
//		return "redirect:/";
//	}

	@PostMapping("/edit/{productId}")
	public ModelAndView editProduct(@PathVariable(name = "productId") long productId) {
		ModelAndView mav = new ModelAndView("edit");

		ProductDTO productDTO = productService.getProductById(productId);
		mav.addObject("product", productDTO);

		return mav;
	}

	@PostMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable(name = "productId") long productId) {
		productService.deleteProduct(productId);
		return "redirect:/";
	}

	@PostMapping(value = "/search")
	public String searchProductResults(@RequestParam("searchTerm") String searchTerm, Model model) {
		List<ProductDTO> searchResult = productService.searchProductByName(searchTerm);
		model.addAttribute("listProductDTO", searchResult);
		return "index";
	}

	@PostMapping("/show")
	public String showProduct(Model model) {
		LocalDateTime currentTime = LocalDateTime.now();
		List<ProductDTO> listProduct = productService.searchProductByTimeRange(currentTime);
		model.addAttribute("listProductDTO", listProduct);
		return "index";
	}

	@PostMapping("/attribute")
	public String viewAttribute(Model model) {
		List<ProductDTO> listProduct = productService.getAllProducts();
		model.addAttribute("listProductDTO", listProduct);
		return "attribute";
	}

	@GetMapping("/attribute-chip")
	public String viewAttributeChip(Model model) {
		List<ProductDTO> listProduct = productService.sortByAttributeName("プロセッサー");
		model.addAttribute("listProductDTO", listProduct);
		return "attribute";
	}

	@GetMapping("/attribute-color")
	public String viewAttributeColor(Model model) {
		List<ProductDTO> listProduct = productService.sortByAttributeName("色");
		model.addAttribute("listProductDTO", listProduct);
		return "attribute";
	}

	@GetMapping("/attribute-size")
	public String viewAttributeSize(Model model) {
		List<ProductDTO> listProduct = productService.sortByAttributeName("サイズ");
		model.addAttribute("listProductDTO", listProduct);
		return "attribute";
	}

	@GetMapping("/attribute-edit/{productId}")
	public ModelAndView editAttribute(@PathVariable(name = "productId") long productId) {
		ModelAndView mav = new ModelAndView("editAttribute");

		ProductDTO productDTO = productService.getProductById(productId);
		mav.addObject("product", productDTO);

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

	@PostMapping("/attribute-delete/{productId}")
	public String deleteAttribute(@PathVariable(name = "productId") long productId) {
		productService.deleteProduct(productId);

		return "redirect:/attribute";
	}
	
	@GetMapping("/attribute-delete/{productId}")
	public String deleteAttributeForm(@PathVariable(name = "productId") long productId) {
	    return "attribute";
	}

	@PostMapping(value = "/attribute-insert")
	public String insertAttribute(@ModelAttribute("product") ProductDTO productDTO) {
		productService.saveProduct(productDTO);
		return "redirect:/";
	}

}
