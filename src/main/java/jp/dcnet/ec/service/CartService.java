package jp.dcnet.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ec.obj.CartDTO;
import jp.dcnet.ec.obj.ProductDTO;
@Service
public class CartService {
	
	@Autowired
	private CartDTO cart;
	
//	@Autowired
//    public CartService(CartDTO cart) {
//        this.cart = cart;
//    }
	
	public void addProduct(List<ProductDTO> products) {
		for (ProductDTO product : products) {
            cart.addProduct(product);
        }
    }
   
}
