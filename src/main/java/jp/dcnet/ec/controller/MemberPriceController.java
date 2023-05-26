package jp.dcnet.ec.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ec.obj.MemberPriceDTO;
import jp.dcnet.ec.service.MemberPriceService;
@Controller	
public class MemberPriceController {
	@Autowired
	private MemberPriceService memberPriceService;
	
	@GetMapping("/pricemember")
    public String viewAdminPage(Model model) {
		List<MemberPriceDTO> listMemberPriceDTO = memberPriceService.getAllProducts();
		model.addAttribute("listMemberPriceDTO",listMemberPriceDTO);
        return "setpricemember";
    }
	
	@PostMapping("/memberprice-search")
	public String searchResults(@RequestParam("searchTerm") long searchTerm, Model model) {
	    Optional<MemberPriceDTO> memberPriceDTOOptional = memberPriceService.searchMemberPriceById(searchTerm);
		MemberPriceDTO memberPriceDTO = memberPriceDTOOptional.orElse(null);
		model.addAttribute("listMemberPriceDTO", memberPriceDTO);
		return "setpricemember";
	}
	
	@PostMapping("/memberprice-show")
	public String showMemberPrice(Model model) {
		LocalDateTime currentTime = LocalDateTime.now();
		List<MemberPriceDTO> listProduct = memberPriceService.searchProductByTimeRange(currentTime);
		model.addAttribute("listMemberPriceDTO", listProduct);
		return "setpricemember";
	}
	
	@PostMapping("/memberprice-edit/{memberId}")
	public ModelAndView editProduct(@PathVariable(name = "memberId") long memberId) {
		ModelAndView mav = new ModelAndView("editMemberPrice");

		MemberPriceDTO memberPriceDTO = memberPriceService.getProductById(memberId);
		mav.addObject("member", memberPriceDTO);

		return mav;
	}
	
	@PostMapping(value = "/insert-memberprice")
	public String insertProduct(@ModelAttribute("member") MemberPriceDTO memberPriceDTO) {
		memberPriceService.saveProduct(memberPriceDTO);
		return "redirect:/";
		
	}
}
