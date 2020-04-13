package com.bbs.service.plant;

import java.text.ParseException;
import java.util.List;

import com.bbs.entity.User;
import com.bbs.entity.bbs_plate;

public interface Plantservice {
boolean savaPlant(bbs_plate plant);
List<bbs_plate> getPlateList() throws ParseException;
boolean delById(String plateId);
bbs_plate findEdit(int plateId);
boolean update(bbs_plate plate);
boolean delAll(String plateIds);

}
