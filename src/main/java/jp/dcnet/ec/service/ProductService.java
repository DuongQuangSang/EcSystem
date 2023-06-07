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
    
    /**
     * すべての商品を取得します。
     */
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntities = repo.findAll();
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }	

    /**
     * 指定された商品IDに該当する商品を取得します。
     *
     * @param productId 商品ID
     * @return 該当する商品のDTOオブジェクト
     */
    public ProductDTO getProductById(Long productId) {
        ProductEntity productEntity = repo.findById(productId).orElse(null);
        if (productEntity != null) {
            return modelMapper.map(productEntity, ProductDTO.class);
        }
        return null;
    }

    /**
     * 商品を保存します。
     *
     * @param productDTO 保存する商品のDTOオブジェクト
     * @return 保存された商品のDTOオブジェクト
     */
    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        ProductEntity savedProductEntity = repo.save(productEntity);
        return modelMapper.map(savedProductEntity, ProductDTO.class);
    }

    /**
     * 指定された商品IDに該当する商品を削除します。
     *
     * @param productId 削除する商品のID
     */
    public void deleteProduct(Long productId) {
        repo.deleteById(productId);
    }
    
    /**
     * 指定された商品名に該当する商品を検索します。
     *
     * @param name 商品名
     * @return 該当する商品のDTOオブジェクトのリスト
     */
    public List<ProductDTO> searchProductByName(String name) {
        List<ProductEntity> productEntities = repo.findByName(name);
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
    
    /**
     * 指定された時刻範囲に該当する商品を検索します。
     *
     * @param currentTime 現在時刻
     * @return 該当する商品のDTOオブジェクトのリスト
     */
    public List<ProductDTO> searchProductByTimeRange(LocalDateTime currentTime) {
        List<ProductEntity> productEntities = repo.findByStartDateBeforeAndEndDateAfter(currentTime, currentTime);
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
    
    /**
     * 指定された属性名に該当する商品を属性名でソートして取得します。
     *
     * @param attributeName 属性名
     * @return 該当する商品のDTOオブジェクトのリスト
     */
    public List<ProductDTO> sortByAttributeName(String attributeName) {
        List<ProductEntity> productEntities = repo.findByAttributeName(attributeName);
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
    
    
    /**
     * すすめの商品を取得します。
     */
    public List<ProductDTO> getArrivalProducts() {
        List<ProductEntity> arrivalProductEntities = repo.findTop8ByOrderByProductIdDesc();	
        return arrivalProductEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
    
    public List<ProductDTO> getSuggestProducts() {
        List<ProductEntity> suggestProductEntities = repo.findBySuggest(true);	
        return suggestProductEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
