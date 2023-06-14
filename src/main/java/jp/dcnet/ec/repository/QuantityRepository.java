package jp.dcnet.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.ec.model.QuantityEntity;

@Repository
public interface QuantityRepository extends JpaRepository<QuantityEntity, Long> {
	@Query("SELECT q FROM QuantityEntity q WHERE q.product.productId = :productId")
	QuantityEntity findByProductId(@Param("productId") Long productId);
}
