package jp.dcnet.ec.model;

import java.util.ArrayList;
import java.util.List;

public class CartEntity {
	private List<ProductEntity> products;
    // Các thuộc tính khác cần thiết
    
    public CartEntity() {
        products = new ArrayList<>();
    }
    
    // Phương thức getter, setter và các phương thức khác
    
    public void addProduct(ProductEntity product) {
        products.add(product);
    }
    
    public void removeProduct(ProductEntity product) {
        products.remove(product);
    }
    
}
