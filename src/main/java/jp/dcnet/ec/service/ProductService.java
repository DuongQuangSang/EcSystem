package jp.dcnet.ec.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ec.model.ProductEntity;
import jp.dcnet.ec.obj.ProductDTO;
import jp.dcnet.ec.repository.ProductRepository;

@Service
public class ProductService {
	
    @Autowired
    private ProductRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;
    // Entity => ProductDTO
    
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntities = repo.findAll();
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }	

    public ProductDTO getProductById(Long productId) {
        ProductEntity productEntity = repo.findById(productId).orElse(null);
        if (productEntity != null) {
            return modelMapper.map(productEntity, ProductDTO.class);
        }
        return null;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        ProductEntity savedProductEntity = repo.save(productEntity);
        return modelMapper.map(savedProductEntity, ProductDTO.class);
    }

    public void deleteProduct(Long productId) {
        repo.deleteById(productId);
    }
    
    public List<ProductDTO> searchProductByName(String name) {
        List<ProductEntity> productEntities = repo.findByName(name);
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
    
    public List<ProductDTO> searchProductByTimeRange(LocalDateTime currentTime) {
        List<ProductEntity> productEntities = repo.findByStartDateBeforeAndEndDateAfter(currentTime, currentTime);
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
    
    public List<ProductDTO> sortByAttributeName(String attributeName) {
        List<ProductEntity> productEntities = repo.findByAttributeName(attributeName);
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
