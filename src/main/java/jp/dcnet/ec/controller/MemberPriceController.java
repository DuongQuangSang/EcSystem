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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.ec.obj.MemberPriceDTO;
import jp.dcnet.ec.obj.ProductDTO;
import jp.dcnet.ec.service.MemberPriceService;

@Controller
@RequestMapping("pricemember")
public class MemberPriceController {
    // Bổ sung comment
    // メンバープライスサービスの自動ワイヤリング
    @Autowired
    private MemberPriceService memberPriceService;

    // 管理者ページを表示するGETメソッド
    @GetMapping("/")
    public String viewAdminPage(Model model) {
        List<MemberPriceDTO> listMemberPriceDTO = memberPriceService.getAllProducts();
        model.addAttribute("listMemberPriceDTO", listMemberPriceDTO);
        return "setpricemember";
    }

    // メンバープライスの検索結果を表示するPOSTメソッド
    @PostMapping("/search")
    public String searchResults(@RequestParam("searchTerm") long searchTerm, Model model) {
        Optional<MemberPriceDTO> memberPriceDTOOptional = memberPriceService.searchMemberPriceById(searchTerm);
        MemberPriceDTO memberPriceDTO = memberPriceDTOOptional.orElse(null);
        model.addAttribute("listMemberPriceDTO", memberPriceDTO);
        return "setpricemember";
    }

    // メンバープライスを表示するPOSTメソッド
    @PostMapping("/show")
    public String showMemberPrice(Model model) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<MemberPriceDTO> listProduct = memberPriceService.searchProductByTimeRange(currentTime);
        model.addAttribute("listMemberPriceDTO", listProduct);
        return "setpricemember";
    }

    // メンバープライスの編集ページを表示するPOSTメソッド
    @PostMapping("/edit/{memberId}")
    public ModelAndView editProduct(@PathVariable(name = "memberId") long memberId) {
        ModelAndView mav = new ModelAndView("editMemberPrice");

        MemberPriceDTO memberPriceDTO = memberPriceService.getProductById(memberId);
        ProductDTO productDTO = memberPriceDTO.getProduct();

        mav.addObject("member", memberPriceDTO);
        mav.addObject("product", productDTO);

        return mav;
    }

    // メンバープライスを挿入するPOSTメソッド
    @PostMapping("/insert")
    public String insertProduct(@ModelAttribute("member") MemberPriceDTO memberPriceDTO,
            @ModelAttribute("product") ProductDTO productDTO, Model model) {
        memberPriceService.saveMemberPrice(memberPriceDTO);
        List<MemberPriceDTO> listMemberPriceDTO = memberPriceService.getAllProducts();
        model.addAttribute("listMemberPriceDTO", listMemberPriceDTO);
        return "setpricemember";
    }
}
