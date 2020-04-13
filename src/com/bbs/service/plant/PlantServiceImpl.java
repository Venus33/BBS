package com.bbs.service.plant;

import java.text.ParseException;
import java.util.List;

import com.bbs.dao.plant.PlantDao;
import com.bbs.dao.plant.PlantDaoImpl;
import com.bbs.entity.bbs_plate;

public class PlantServiceImpl implements Plantservice {
private PlantDao pd=new PlantDaoImpl();
	@Override
	public boolean savaPlant(bbs_plate plant) {
		int result=pd.savaPlant(plant);
		if(result>0) {
			return true;
		}else {
		return false;
		}
	}
	@Override
	public List<bbs_plate> getPlateList() throws ParseException{
		// TODO Auto-generated method stub
		return pd.getPlateList();
	}
	@Override
	public boolean delById(String plateId) {
		int result=pd.delById(plateId);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public bbs_plate findEdit(int plateId) {
		// TODO Auto-generated method stub
		return pd.findBid(plateId);
	}
	@Override
	public boolean update(bbs_plate plate) {
		int result=pd.update(plate);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean delAll(String plateIds) {
	
		// 将字符串中的所有引号去掉，并截取[]中的数据
		plateIds = plateIds.substring(1, plateIds.lastIndexOf("]")).replaceAll("\"", "");
				// 将字符窜进行拆分为数组
				String[] uids = plateIds.split(",");
				int result=pd.delAll(uids);
				if(result>0) {
					return true;
				}else {
					return false;
				}
	}

}
