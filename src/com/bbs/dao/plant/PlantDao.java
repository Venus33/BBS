package com.bbs.dao.plant;

import java.text.ParseException;
import java.util.List;

import com.bbs.entity.bbs_plate;

public interface PlantDao {
 int savaPlant(bbs_plate plant);

List<bbs_plate> getPlateList()  throws ParseException;

int delById(String plateId);

bbs_plate findBid(int plateId);

int update(bbs_plate plate);

int delAll(String[] uids);;
}
