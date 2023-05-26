package jp.dcnet.ec.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.ec.model.MemberPriceEntity;

@Repository
public interface MemberPriceRepository extends JpaRepository<MemberPriceEntity, Long> {
	List<MemberPriceEntity> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);
}
