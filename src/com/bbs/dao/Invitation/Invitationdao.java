package com.bbs.dao.Invitation;

import java.text.ParseException;
import java.util.List;

import com.bbs.entity.bbs_invitation;
import com.bbs.entity.bbs_plate;

public interface Invitationdao {

	List<bbs_invitation> getinvitionList() throws ParseException;

	int savaPlant(bbs_invitation it);

	bbs_invitation findbyid(String invitationId);

	int delById(String invitationId);

	int update(bbs_invitation invi);

}
