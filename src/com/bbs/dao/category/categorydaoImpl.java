package com.bbs.dao.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.commons.DataUtils;
import com.bbs.entity.Category;

public class categorydaoImpl implements categorydao {

	@Override
	public List<Category> getall() {

		List<Category> list=new ArrayList<Category>();
		String sql="SELECT * FROM bbs_category";
		try {
			ResultSet rs=DataUtils.queryAll(sql, null);
			while(rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
