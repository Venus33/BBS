package com.bbs.service.bbs_invitation_ans;

import java.text.ParseException;
import java.util.List;


import com.bbs.entity.bbs_invitation_ans;

public interface Invitation_ansservice {
	List<bbs_invitation_ans> getinvitionansList() throws ParseException;

	boolean add(bbs_invitation_ans ans);
}
