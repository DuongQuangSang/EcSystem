package jp.dcnet.ec.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ec.model.MemberPriceEntity;
import jp.dcnet.ec.obj.MemberPriceDTO;
import jp.dcnet.ec.repository.MemberPriceRepository;

@Service
public class MemberPriceService {
	 @Autowired
	    private MemberPriceRepository repo;
	    
	    @Autowired
	    private ModelMapper modelMapper;
	    
	    // Entity => ProductDTO
	    public List<MemberPriceDTO> getAllProducts() {
	        List<MemberPriceEntity> memberPriceEntities = repo.findAll();
	        return memberPriceEntities.stream()
	                .map(memberPriceEntity -> modelMapper.map(memberPriceEntity, MemberPriceDTO.class))
	                .collect(Collectors.toList());
	    }	
	    
	    public Optional<MemberPriceDTO> searchMemberPriceById(Long memberId) {
	        Optional<MemberPriceEntity> memberPriceEntityOptional = repo.findById(memberId);
	        return memberPriceEntityOptional.map(memberPriceEntity ->
	                modelMapper.map(memberPriceEntity, MemberPriceDTO.class)
	        );
	    }
	    
	    public List<MemberPriceDTO> searchProductByTimeRange(LocalDateTime currentTime) {
	        List<MemberPriceEntity> memberPriceEntities = repo.findByStartDateBeforeAndEndDateAfter(currentTime, currentTime);
	        return memberPriceEntities.stream()
	                .map(memberPriceEntity -> modelMapper.map(memberPriceEntity, MemberPriceDTO.class))
	                .collect(Collectors.toList());
	    }
	    
	    public MemberPriceDTO getProductById(Long memberId) {
	    	MemberPriceEntity memberPriceEntity = repo.findById(memberId).orElse(null);
	        if (memberPriceEntity != null) {
	            return modelMapper.map(memberPriceEntity, MemberPriceDTO.class);
	        }
	        return null;
	    }
	    
	    public MemberPriceDTO saveProduct(MemberPriceDTO memberPriceDTO) {
	    	MemberPriceEntity memberPriceEntity = modelMapper.map(memberPriceDTO, MemberPriceEntity.class);
	    	MemberPriceEntity savedMemberPriceEntity = repo.save(memberPriceEntity);
	        return modelMapper.map(savedMemberPriceEntity, MemberPriceDTO.class);
	    }
}
