package jp.dcnet.ec.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ec.model.ProductEntity;
import jp.dcnet.ec.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    public ProductRepository repo ;

    public List<ProductEntity> getAllProducts() {
        return repo.findAll();
    }

    public ProductEntity getProductById(Long product_id) {
        return repo.findById(product_id).get();
    }

    public ProductEntity saveProduct(ProductEntity product) {
        return repo.save(product);
    }

    public void deleteProduct(Long product_id) {
    	repo.deleteById(product_id);
    }
    
    public List<ProductEntity> searchProductByName(String name) {
    	return repo.findByName(name);
    }
    
    public List<ProductEntity> searchProductByTimeRange(LocalDateTime currentTime) {
        return repo.findByStartDateBeforeAndEndDateAfter(currentTime, currentTime);
    }
    
    public List<ProductEntity> sortByAttributeName(String attributeName) {
    	return repo.findByAttributeName(attributeName);
    }
    
//    @Transactional
    public ProductEntity update(ProductEntity product) {
    	ProductEntity updateResponse = repo.save(product);
        return updateResponse;
    }
}
