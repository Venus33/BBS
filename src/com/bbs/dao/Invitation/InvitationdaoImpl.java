package com.bbs.dao.Invitation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.commons.DataUtils;
import com.bbs.entity.bbs_invitation;
import com.bbs.entity.bbs_plate;


public class InvitationdaoImpl implements Invitationdao {
	private ResultSet rs = null;
	@Override
	public List<bbs_invitation> getinvitionList() throws ParseException {
		List<bbs_invitation> list=new ArrayList<bbs_invitation>();
		
		try {
			String sql="SELECT i.*,p.plateTitle,c.category FROM bbs_invitation i\r\n" + 
					"INNER JOIN bbs_plate p ON(i.`plateId`=p.`plateId`)\r\n" + 
					"INNER JOIN bbs_category c ON(i.`categoryId`=c.`categoryId`)";
			rs = DataUtils.queryAll(sql, null);
			while(rs.next()) {
				bbs_invitation it=new bbs_invitation();
				it.setInvitationId(rs.getString("invitationId"));
				it.setInvitationMessage(rs.getString("invitationMessage"));
				it.setUserId(rs.getString("userId"));
				it.setPlateId(rs.getInt("plateId"));
				it.setCategoryId(rs.getInt("categoryId"));
				it.setIsPass(rs.getInt("isPass"));
				it.setIsEnable(rs.getInt("isEnable"));
				it.setIsCream(rs.getInt("isCream"));
				it.setInvitationCreate(rs.getDate("invitationCreate"));
				it.setInvitationModify(rs.getDate("invitationModify"));
				list.add(it);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return list;
	}
	@Override
	public int savaPlant(bbs_invitation it) {
		
		String sql="INSERT INTO bbs_invitation(invitationId,invitationMessage,userId,plateId,categoryId) VALUES(?,?,?,?,?)";
		Object parms[]= {it.getInvitationId(),it.getInvitationMessage(),it.getUserId(),it.getPlateId(),it.getCategoryId()};
		return DataUtils.executeUpdate(sql, parms);
	}
	@Override
	public bbs_invitation findbyid(String invitationId) {
		bbs_invitation it=new bbs_invitation();
		
		try {
			String sql="select * from bbs_invitation where invitationId=?";
			Object[] params = {invitationId};
			rs = DataUtils.queryAll(sql, params);
			if(rs.next()) {
				it.setInvitationId(rs.getString("invitationId"));
				it.setInvitationMessage(rs.getString("invitationMessage"));
				it.setUserId(rs.getString("userId"));
				it.setPlateId(rs.getInt("plateId"));
				it.setCategoryId(rs.getInt("categoryId"));
				it.setIsPass(rs.getInt("isPass"));
				it.setIsEnable(rs.getInt("isEnable"));
				it.setIsCream(rs.getInt("isCream"));
				it.setInvitationCreate(rs.getDate("invitationCreate"));
				it.setInvitationModify(rs.getDate("invitationModify"));
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return it;
	}
	@Override
	public int delById(String invitationId) {
		String sql = "delete from bbs_invitation where invitationId=?";
		Object[] params = {invitationId};
		return DataUtils.executeUpdate(sql, params);
	}
	@Override
	public int update(bbs_invitation invi) {
		String sql = "update bbs_invitation set invitationMessage=?,plateId=?,categoryId=? where invitationId=?";
		Object[] parms = {invi.getInvitationMessage(),invi.getPlateId(),invi.getCategoryId(),invi.getInvitationId()};
		return DataUtils.executeUpdate(sql, parms);
	}

}
