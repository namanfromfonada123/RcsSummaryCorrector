package com.RcsSummaryCorrector.Modal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "rcs_summary")
@Data
public class RcsSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campaign_name", length = 100)
    private String campaignName;

    @Column(name = "created_date", length = 20)
    private String createdDate;

    @Column(name = "delivered", length = 10)
    private String delivered;

    @Column(name = "failed", length = 10)
    private String failed;

    @Column(name = "invalid", length = 20)
    private String invalid;

    @Column(name = "last_modified_date", length = 20)
    private String lastModifiedDate;

    @Column(name = "lead_name", length = 200)
    private String leadName;

    @Column(name = "nonrcs_failed", length = 20)
    private String nonrcsFailed;

    @Column(name = "`read`", length = 20)
    private String read;

    @Column(name = "sent", length = 20)
    private String sent;

    @Column(name = "status", length = 5000)
    private String status;

    @Column(name = "submitted", length = 20)
    private String submitted;

    @Column(name = "total", length = 20)
    private String total;

    @Column(name = "read_no", length = 10)
    private String readNo;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "lead_id", length = 255)
    private String leadId;

    @Column(name = "rcs_msg_type", length = 50)
    private String rcsMsgType;

    @Column(name = "rcs_msg_type_id", length = 1)
    private String rcsMsgTypeId;

    @Column(name = "nonrcs_fallback_sms_submit", length = 10)
    private String nonrcsFallbackSmsSubmit;

    @Column(name = "nonrcs_sms_submit", length = 10)
    private String nonrcsSmsSubmit;

    @Column(name = "delivery_success", length = 255)
    private String deliverySuccess;

    @Column(name = "dellivery_failed", length = 255)
    private String deliveryFailed;

    @Column(name = "submit_failed", length = 255)
    private String submitFailed;
}

