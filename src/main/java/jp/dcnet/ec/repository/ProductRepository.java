package jp.dcnet.ec.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.ec.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	List<ProductEntity> findByName(String name);

	List<ProductEntity> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

	List<ProductEntity> findByAttributeName(String attributeName);
}
