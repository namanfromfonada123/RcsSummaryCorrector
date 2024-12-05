package com.RcsSummaryCorrector.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.RcsSummaryCorrector.Modal.RcsSummary;
import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RcsSummaryRepository extends JpaRepository<RcsSummary, Long> {

	
	@Query(value = "SELECT rs.* FROM rcsmessaging.template t JOIN rcsmessaging.campaign c ON t.id = c.template_id JOIN rcsmessaging.lead_info l ON c.campaign_id = l.lead_campaign_id JOIN rcsmessaging.rcs_summary rs ON l.lead_id = rs.lead_id WHERE t.template_code =?1 and rs.created_date = ?2 ", nativeQuery = true)
	List<RcsSummary> findtemplatebyLeadid(String templateName,String date);
	
	
	List<RcsSummary> findByCreatedDate(String createdDate);
	
}
