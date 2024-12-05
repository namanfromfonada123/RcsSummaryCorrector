package com.RcsSummaryCorrector.Modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "LEAD_INFO")
@Data
public class LeadInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LEAD_ID")
	private Long leadId;
	@Column(name = "LEAD_NAME")
	private String leadName;
	@Column(name = "LEAD_USER_ID")
	private Long userId;
	
	@Column(name = "LEAD_CAMPAIGN_ID")
	private Long campaignId;

	@Column(name = "LEAD_SCHEDULE_ID")
	private Long scheduleId;

	@Column(name = "LEAD_RETRY_ID")
	private Long retryId;

	@Column(name = "LEAD_STATUS")
	private String leadStatus;
	@Column(name = "LEAD_COMPLETION_STATUS")
	private String leadCompletionStatus;
	@Column(name = "LEAD_ACTION")
	private String leadAction;
	@Column(name = "COUNT_OF_NUMBERS")
	private Integer countOfNumbers;
	@Column(name = "COUNT_OF_INVALID_NUMBERS")
	private Integer countOfInvalidNumbers;
	@Column(name = "COUNT_OF_NON_RCS_NUMBERS")
	private Integer countOfNonRcsNumbers;
	@Column(name = "COUNT_OF_DUPLICATE_NUMBERS")
	private Integer countOfDuplicateNumbers;
	@Column(name = "COUNT_OF_BLACKLIST_NUMBERS")
	private Integer countOfBlackListNumbers;
	@Column(name = "INSERT_DTM")
	private Date insertDtm;
	@Column(name = "PROCESS_DTM")
	private Date processDtm;
	@Column(name = "LEAD_PRIORITY_TYPE")
	private String leadPriorityType;
	@Column(name = "LEAD_PRIORITY")
	private Integer leadPriority;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "LAST_MODIFIED_BY")
	private String lastModifiedBy;
	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;
	@Column(name = "PROCESS_END_DTM")
	private Date processEndDtm;
	private Long rcsMsgTypeId;
	private Long fileTotalRecord;

	

}
