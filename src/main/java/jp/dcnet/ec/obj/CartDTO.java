package jp.dcnet.ec.obj;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
	    private List<ProductDTO> products;
	    // Các thuộc tính khác cần thiết
	    
	    public CartDTO() {
	        products = new ArrayList<>();
	    }
	    
	    public List<ProductDTO> getProducts() {
			return products;
		}

		public void setProducts(List<ProductDTO> products) {
			this.products = products;
		}
		
		public void addProduct(ProductDTO product) {
	        products.add(product);
	    }
}
