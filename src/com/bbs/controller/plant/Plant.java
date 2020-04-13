package com.bbs.controller.plant;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bbs.entity.bbs_plate;
import com.bbs.service.plant.PlantServiceImpl;
import com.bbs.service.plant.Plantservice;

/**
 * Servlet implementation class Plantadd
 */
@WebServlet("/Plant")
public class Plant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Plantservice ps=new PlantServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Plant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获得客户端传递的参数值
		req.setCharacterEncoding("Utf-8");
		String op=req.getParameter("op");
		if("add".equals(op)) {
			savaPlant(req,resp);
		}else if("show".equals(op)) {
			showplate(req,resp);
		}else if("deleByid".equals(op)) {
			DeleById(req,resp);
		}else if("findid".equals(op)) {
			findByid(req,resp);
		}else if("update".equals(op)) {
			update(req,resp);
		}else if("delAll".equals(op)) {
			delAll(req,resp);
		}
	}

	

	private void delAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String plateIds = req.getParameter("plateIds");
		//["test001","test002"]
		PrintWriter out = resp.getWriter();
		boolean isOk = ps.delAll(plateIds);
		if(isOk) {
			out.write("true");
		}else {
			out.write("false");
		}
		out.flush();
		
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String plateTitle=req.getParameter("plateTitle");
		String plateMessage=req.getParameter("plateMessage");
		int plateId = Integer.parseInt(req.getParameter("plateId"));
		bbs_plate plate=new bbs_plate();
		plate.setPlateTitle(plateTitle);
		plate.setPlateMessage(plateMessage);
		plate.setPlateId(plateId);
		boolean isOk = ps.update(plate);
		if(isOk) {
			out.write("true");
		}else {
			out.write("false");
		}
		out.flush();
		
	}

	private void findByid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int plateId =Integer.parseInt( req.getParameter("plateId"));
		bbs_plate plate = ps.findEdit(plateId);
		if(plate != null) {
			req.getSession().setAttribute("plate", plate);
			resp.sendRedirect("server/plant-update.jsp");
		}
		
	}

	private void DeleById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String plateId = req.getParameter("plateId");
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		boolean isOk = ps.delById(plateId);
		if(isOk) {
			out.write("{\"result\":\"true\"}");
		}else {
			out.write("{\"result\":\"false\"}");
		}
		out.flush();
	}

	private void showplate(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//创建保存数据的集合
				List<bbs_plate> list = null;
					try {
						list = ps.getPlateList();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				req.getSession().setAttribute("list", list);
				resp.sendRedirect("server/plant-list.jsp");
		
	}

	private void savaPlant(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置响应的文件类型
		resp.setContentType("application/json;charset=UTF-8");
		//获得表单提交
		String plateTitle=req.getParameter("plateTitle");
		String plateMessage=req.getParameter("plateMessage");
		
		bbs_plate plant=new bbs_plate(plateTitle, plateMessage);
				PrintWriter out = resp.getWriter();
				// user 就是放到uploadUser方法前面的用户对象
				boolean isOk =ps.savaPlant(plant);
				if(isOk) {
					out.write("{\"result\":\"true\"}");
				}else {
					out.write("{\"result\":\"false\"}");
				}
				out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
