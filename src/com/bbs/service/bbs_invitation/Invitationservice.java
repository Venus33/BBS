package com.bbs.service.bbs_invitation;

import java.text.ParseException;
import java.util.List;

import com.bbs.entity.bbs_invitation;
import com.bbs.entity.bbs_plate;

public interface Invitationservice {

	List<bbs_invitation> getinvitionList() throws ParseException;

	boolean add(bbs_invitation it);

	bbs_invitation findEdit(String invitationId);

	boolean delById(String invitationId);

	boolean update(bbs_invitation invi);

}
