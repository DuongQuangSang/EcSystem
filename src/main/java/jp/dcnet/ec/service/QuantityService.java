package jp.dcnet.ec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ec.model.QuantityEntity;
import jp.dcnet.ec.obj.QuantityDTO;
import jp.dcnet.ec.repository.QuantityRepository;

@Service
public class QuantityService {
    @Autowired
    private QuantityRepository repo;

    @Autowired
    private ModelMapper modelMapper;
    // Entity => ProductDTO
    
    /**
     * すべての商品を取得します。
     */
    public List<QuantityDTO> getAllProducts() {
        List<QuantityEntity> quantityEntities = repo.findAll();
        return quantityEntities.stream()
                .map(quantityEntity -> modelMapper.map(quantityEntity, QuantityDTO.class))
                .collect(Collectors.toList());
    }
    
    public QuantityDTO findQuantityByProductId(Long productId) {
    	QuantityEntity quantityEntity = repo.findByProductId(productId);
        if (quantityEntity != null) {
            return modelMapper.map(quantityEntity, QuantityDTO.class);
        }
        return null;
    }
}
