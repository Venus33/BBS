package com.bbs.dao.plant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.commons.DataUtils;
import com.bbs.entity.bbs_plate;

public class PlantDaoImpl implements PlantDao {
	private ResultSet rs = null;
	@Override
	public int savaPlant(bbs_plate plant) {
		String sql="insert into bbs_plate(plateTitle,plateMessage,isEnable) values(?,?,0)";
		Object[] parms= {plant.getPlateTitle(),plant.getPlateMessage()};
		return DataUtils.executeUpdate(sql, parms);
	}

	@Override
	public List<bbs_plate> getPlateList()  throws ParseException{
		List<bbs_plate> list=new ArrayList<bbs_plate>();
		
		try {
			String sql="select * from bbs_plate";
			rs = DataUtils.queryAll(sql, null);
			while (rs.next()) {
				bbs_plate plate=new bbs_plate(); 
				plate.setPlateId(rs.getInt("plateId"));
				plate.setPlateTitle(rs.getString("plateTitle"));
				plate.setPlateMessage(rs.getString("plateMessage"));
				plate.setIsEnable(rs.getInt("isEnable"));
				list.add(plate);
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
	public int delById(String plateId) {
		String sql = "delete from bbs_plate where plateId=?";
		Object[] params = {plateId};
		return DataUtils.executeUpdate(sql, params);
	}

	@Override
	public bbs_plate findBid(int plateId) {
		bbs_plate plate=new bbs_plate();
		
		try {
			String sql="select * from bbs_plate where plateId=?";
			Object[] params = {plateId};
			rs = DataUtils.queryAll(sql, params);
			if(rs.next()) {
				
				plate.setPlateId(rs.getInt("plateId"));
				plate.setPlateTitle(rs.getString("plateTitle"));
				plate.setPlateMessage(rs.getString("plateMessage"));
				plate.setIsEnable(rs.getInt("isEnable"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return plate;
	}

	@Override
	public int update(bbs_plate plate) {
		String sql = "update bbs_plate set plateTitle=?,plateMessage=? where plateId=?";
		Object[] parms = {plate.getPlateTitle(),plate.getPlateMessage(),plate.getPlateId()};
		return DataUtils.executeUpdate(sql, parms);
	}

	@Override
	public int delAll(String[] uids) {
		StringBuffer sql = new StringBuffer("delete from bbs_plate where plateId in(");
		// 根据参数数组的长度，拼接锁需要的?号个数
		for (int i = 0; i < uids.length; i++) {
			sql.append("?");
			if (i != uids.length-1) {
				sql.append(",");
			}
		}
		sql.append(")");
		// delete from bbs_user where userid in(test001,test002)
		return DataUtils.executeUpdate(sql.toString(), uids);
	}

}
