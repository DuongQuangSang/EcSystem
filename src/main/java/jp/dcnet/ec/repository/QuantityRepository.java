package jp.dcnet.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.ec.model.QuantityEntity;

@Repository
public interface QuantityRepository extends JpaRepository<QuantityEntity, Long> {
	
}
