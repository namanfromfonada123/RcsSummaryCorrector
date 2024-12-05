package com.RcsSummaryCorrector.Modal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "template")
@Data
public class Template {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String templateCode;

	private String templateJson;

	private String templateType;

	private String templateMsgType;

	private Long templateUserId;

	private String templateCustomParam;

	private String inserttime;

	private Integer status;

	private String botId;
	private String sms_dlt_principle_id;
	private String sms_dlt_content_id;
	private String sms_senderId;
	private String sms_contentId;
	private String sms_content;
	private String msgType;
	private String rcsMsgTypeId;
	
	@Column(name = "operator_response")
	private String operatorResponse;
	@Column(name = "approve_response")
	private String approveResponse;

}
