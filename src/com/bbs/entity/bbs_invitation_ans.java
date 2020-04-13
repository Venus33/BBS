package com.bbs.entity;

import java.util.Date;

public class bbs_invitation_ans {

    private String ansId;
    private String ansMessage;
    private String invitationId;
    private String userId;
    private Date ansDate;
	public String getAnsId() {
		return ansId;
	}
	public void setAnsId(String ansId) {
		this.ansId = ansId;
	}
	public String getAnsMessage() {
		return ansMessage;
	}
	public void setAnsMessage(String ansMessage) {
		this.ansMessage = ansMessage;
	}
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getAnsDate() {
		return ansDate;
	}
	public void setAnsDate(Date ansDate) {
		this.ansDate = ansDate;
	}
	
	public bbs_invitation_ans(String ansId, String ansMessage, String invitationId, String userId) {
		super();
		this.ansId = ansId;
		this.ansMessage = ansMessage;
		this.invitationId = invitationId;
		this.userId = userId;
		
	}
	public bbs_invitation_ans() {
		super();
	}
    
}
