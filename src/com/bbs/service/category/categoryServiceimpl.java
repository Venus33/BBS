package com.bbs.service.category;

import java.util.List;

import com.bbs.dao.category.categorydao;
import com.bbs.dao.category.categorydaoImpl;
import com.bbs.entity.Category;

public class categoryServiceimpl implements categoryService {
	categorydao sdao=new categorydaoImpl();

	@Override
	public List<Category> getall() {
		// TODO Auto-generated method stub
		return sdao.getall();
	}
}
