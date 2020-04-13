package com.bbs.dao.bbs_invitation_ans;

import java.util.List;

import com.bbs.entity.bbs_invitation_ans;

public interface Invitation_ansdao {

	List<bbs_invitation_ans> getinvitionansList();

	int add(bbs_invitation_ans ans);

}
