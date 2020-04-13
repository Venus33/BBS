package com.bbs.dao.bbs_invitation_ans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.commons.DataUtils;
import com.bbs.entity.bbs_invitation;
import com.bbs.entity.bbs_invitation_ans;



public class Invitation_ansdaoimpl implements Invitation_ansdao{
	private ResultSet rs = null;
	@Override
	public List<bbs_invitation_ans> getinvitionansList() {
		List<bbs_invitation_ans> listans=new ArrayList<bbs_invitation_ans>();
		try {
			String sql="SELECT i.*,p.invitationMessage,c.userId FROM bbs_invitation_ans i\r\n" + 
					"INNER JOIN bbs_invitation p ON(i.`invitationId`=p.`invitationId`)\r\n" + 
					"INNER JOIN bbs_user c ON(i.`userId`=c.`userId`)";
			rs = DataUtils.queryAll(sql, null);
			while(rs.next()) {
				bbs_invitation_ans ans=new bbs_invitation_ans();
				ans.setAnsId(rs.getString("ansId"));
				ans.setAnsMessage(rs.getString("ansMessage"));
				ans.setInvitationId(rs.getString("invitationId"));
				ans.setUserId(rs.getString("userId"));
				ans.setAnsDate(rs.getDate("ansDate"));
				listans.add(ans);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return listans;
	}

	@Override
	public int add(bbs_invitation_ans ans) {

		String sql="INSERT INTO bbs_invitation_ans(ansId,ansMessage,invitationId,userId) VALUES(?,?,?,?)";
		Object parms[]= {ans.getAnsId(),ans.getAnsMessage(),ans.getInvitationId(),ans.getUserId()};
		return DataUtils.executeUpdate(sql, parms);
	}

}
