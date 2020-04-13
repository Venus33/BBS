package com.bbs.service.bbs_invitation;

import java.text.ParseException;
import java.util.List;

import com.bbs.dao.Invitation.Invitationdao;
import com.bbs.dao.Invitation.InvitationdaoImpl;
import com.bbs.entity.bbs_invitation;
import com.bbs.entity.bbs_plate;

public class InvitationserviceImpl implements Invitationservice {
private Invitationdao idao=new InvitationdaoImpl();
	@Override
	public List<bbs_invitation> getinvitionList() throws ParseException{
		// TODO Auto-generated method stub
		return idao.getinvitionList();
	}
	@Override
	public boolean add(bbs_invitation it) {
		int result=idao.savaPlant(it);
		if(result>0) {
			return true;
		}else {
		return false;
		}
	}
	@Override
	public bbs_invitation findEdit(String invitationId) {
		// TODO Auto-generated method stub
		return idao.findbyid(invitationId);
	}
	@Override
	public boolean delById(String invitationId) {
		int result=idao.delById(invitationId);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean update(bbs_invitation invi) {
		int result=idao.update(invi);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}

}
