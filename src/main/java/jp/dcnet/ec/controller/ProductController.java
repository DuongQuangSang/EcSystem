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
//@RequestMapping("admin")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	// ホームページを表示するためのメソッド
	@GetMapping("/")
    public String viewAdminPage(Model model) {
		List<ProductDTO> listProductDTO = productService.getAllProducts();
		model.addAttribute("listProductDTO",listProductDTO);
        return "admin/index";
    }

	
	// 新しい商品を作成するためのメソッド
	@PostMapping(value = "/new")
	public String newProduct(Model model) {
		ProductDTO productDTO = new ProductDTO();
		model.addAttribute("product", productDTO);
		return "admin/new_form";
	}

	
	// 新しい商品を保存するためのメソッド
	@PostMapping("/insert")
	public String insertProduct(@ModelAttribute("product") ProductDTO productDTO) {

	    if (productDTO.getAttributeName() == null || productDTO.getAttributeValue() == null) {

	        ProductDTO existingProduct = productService.getProductById(productDTO.getProductId());

	        productDTO.setAttributeName(existingProduct.getAttributeName());
	        productDTO.setAttributeValue(existingProduct.getAttributeValue());
	    }
		productService.saveProduct(productDTO);
		return "redirect:/";
	}

	
	// 商品を編集するためのメソッド
	@PostMapping("/edit/{productId}")
	public ModelAndView editProduct(@PathVariable(name = "productId") long productId) {
		ModelAndView mav = new ModelAndView("admin/edit");

		ProductDTO productDTO = productService.getProductById(productId);
		mav.addObject("product", productDTO);

		return mav;
	}

	
	// 商品を削除するためのメソッド
	@PostMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable(name = "productId") long productId) {
		productService.deleteProduct(productId);
		return "redirect:/";
	}

	
	// 商品を検索するためのメソッド
	@PostMapping("/search")
	public String searchProductResults(@RequestParam("searchTerm") String searchTerm, Model model) {
		List<ProductDTO> searchResult = productService.searchProductByName(searchTerm);
		model.addAttribute("listProductDTO", searchResult);
		return "admin/index";
	}

	
	// 商品を表示するためのメソッド
	@PostMapping("/show")
	public String showProduct(Model model) {
		LocalDateTime currentTime = LocalDateTime.now();
		List<ProductDTO> listProduct = productService.searchProductByTimeRange(currentTime);
		model.addAttribute("listProductDTO", listProduct);
		return "admin/index";
	}

	
	// 属性を表示するためのメソッド
	@PostMapping("/attribute")
	public String viewAttribute(Model model) {
		List<ProductDTO> listProduct = productService.getAllProducts();
		model.addAttribute("listProductDTO", listProduct);
		return "admin/attribute";
	}

	
	// 属性「プロセッサー」の商品を表示するためのメソッド
	@GetMapping("/attribute-chip")
	public String viewAttributeChip(Model model) {
		List<ProductDTO> listProduct = productService.sortByAttributeName("プロセッサー");
		model.addAttribute("listProductDTO", listProduct);
		return "admin/attribute";
	}

	
	// 属性「色」の商品を表示するためのメソッド
	@GetMapping("/attribute-color")
	public String viewAttributeColor(Model model) {
		List<ProductDTO> listProduct = productService.sortByAttributeName("色");
		model.addAttribute("listProductDTO", listProduct);
		return "admin/attribute";
	}

	
	// 属性「サイズ」の商品を表示するためのメソッド
	@GetMapping("/attribute-size")
	public String viewAttributeSize(Model model) {
		List<ProductDTO> listProduct = productService.sortByAttributeName("サイズ");
		model.addAttribute("listProductDTO", listProduct);
		return "admin/attribute";
	}

	
	// 属性を編集するためのメソッド
	@PostMapping("/attribute-edit/{productId}")
	public ModelAndView editAttribute(@PathVariable(name = "productId") long productId) {
	    ModelAndView mav = new ModelAndView("editAttribute");

	    ProductDTO productDTO = productService.getProductById(productId);

	    List<String> attributeOptions = Arrays.asList("サイズ", "色", "プロセッサー");
	    List<String> sizeOptions = Arrays.asList("13", "14", "15.6");
	    List<String> colorOptions = Arrays.asList("黒", "白", "グレー");
	    List<String> processorOptions = Arrays.asList("Intel", "Ryzen");

	    mav.addObject("attributeOptions", attributeOptions);
	    mav.addObject("sizeOptions", sizeOptions);
	    mav.addObject("colorOptions", colorOptions);
	    mav.addObject("processorOptions", processorOptions);
	    mav.addObject("product", productDTO);
	    mav.addObject("selectedAttributeOption", productDTO.getAttributeName());

	    return mav;
	}
	
	
	// 属性を保存するためのメソッド
	@PostMapping("/insertAttribute")
	public String insertAttribute(@ModelAttribute("product") ProductDTO productDTO, Model model) {
		productService.saveProduct(productDTO);
		return "admin/attribute";
	}

	
	// 属性を削除するためのメソッド
	@PostMapping("/attribute-delete/{productId}")
	public String deleteAttribute(@PathVariable(name = "productId") long productId) {
		productService.deleteProduct(productId);
		return "redirect:/attribute";
	}
	
	
	// 属性を削除するフォームを表示するためのメソッド
	@GetMapping("/attribute-delete/{productId}")
	public String deleteAttributeForm(@PathVariable(name = "productId") long productId) {
	    return "admin/attribute";
	}
	
}
