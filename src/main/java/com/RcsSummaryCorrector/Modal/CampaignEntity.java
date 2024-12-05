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
@Table(name = "campaign")
@Data
public class CampaignEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long campaignId;
	private String campaignName;
	private long userId;
	private String campaignStatus;
	private String campaignType;
	private Date campaignStartTime;
	private Date campaignEndTime;
	@Column(columnDefinition = "integer default 0")
	private int isDeleted;
	private String description;
	private String channelPriorityScheme;
	private String rcsAgentId;
	private Long messageId;
	@Column(columnDefinition = "varchar(5000)")
	private String messageJson;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "LAST_MODIFIED_BY")
	private String lastModifiedBy;
	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;
	private Long templateId;

	private String viResponse;
	private String deliveryStatus;
	private String sendToClient;
	private String rcspClientResponse;
	private Integer isSendToRmq;
	private String dataSourceName;
	private Long rcsMsgTypeId;
	private String msgCampaignType;
	private Long smsTemplateId;


}
