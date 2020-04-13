package com.bbs.service.bbs_invitation_ans;

import java.text.ParseException;
import java.util.List;

import com.bbs.dao.bbs_invitation_ans.Invitation_ansdao;
import com.bbs.dao.bbs_invitation_ans.Invitation_ansdaoimpl;
import com.bbs.entity.bbs_invitation_ans;

public class Invitation_ansserviceImpl implements Invitation_ansservice{
	Invitation_ansdao adao=new Invitation_ansdaoimpl();

	@Override
	public List<bbs_invitation_ans> getinvitionansList() throws ParseException {
		// TODO Auto-generated method stub
		return adao.getinvitionansList();
	}

	@Override
	public boolean add(bbs_invitation_ans ans) {
		int result=adao.add(ans);
		if(result>0) {
			return true;
		}else {
		return false;
		}
	}
}
