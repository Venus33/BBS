package com.bbs.controller.bbs_invitation;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.entity.bbs_invitation;
import com.bbs.entity.bbs_invitation_ans;
import com.bbs.entity.bbs_plate;
import com.bbs.service.bbs_invitation.Invitationservice;
import com.bbs.service.bbs_invitation.InvitationserviceImpl;
import com.bbs.service.bbs_invitation_ans.Invitation_ansservice;
import com.bbs.service.bbs_invitation_ans.Invitation_ansserviceImpl;

/**
 * Servlet implementation class Invitation
 */
@WebServlet("/Invitation")
public class Invitation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Invitationservice is=new InvitationserviceImpl();
       private Invitation_ansservice ias=new Invitation_ansserviceImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Invitation() {
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
				if("showInvitation".equals(op)) {
					show(req,resp);
				}else if("add".equals(op)) {
					add(req,resp);
				}else if("findid".equals(op)) {
					findid(req,resp);
				}else if("deletebyid".equals(op)) {
					deleteid(req,resp);
				}else if("update".equals(op)) {
					update(req,resp);
				}else if("findbyid".equals(op)) {
					findbyid(req,resp);				}
	}

	private void findbyid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String invitationId = req.getParameter("invitationId");
		bbs_invitation invition = is.findEdit(invitationId);
		List<bbs_invitation_ans> listans;
		try {
			if(invition != null) {
				listans = ias.getinvitionansList();
				req.getSession().setAttribute("invition", invition);
				req.getSession().setAttribute("listans", listans);
				resp.sendRedirect("server/reply.jsp");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String invitationId = req.getParameter("invitationId");
		String invitationMessage = req.getParameter("invitationMessage");
		int plateId =Integer.parseInt( req.getParameter("plateId"));
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		bbs_invitation invi=new bbs_invitation();
		invi.setInvitationMessage(invitationMessage);
		invi.setInvitationId(invitationId);
		invi.setPlateId(plateId);
		invi.setCategoryId(categoryId);
		boolean isOk = is.update(invi);
		if(isOk) {
			out.write("true");
		}else {
			out.write("false");
		}
		out.flush();
		
	}

	private void deleteid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String invitationId = req.getParameter("invitationId");
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		boolean isOk = is.delById(invitationId);
		if(isOk) {
			out.write("{\"result\":\"true\"}");
		}else {
			out.write("{\"result\":\"false\"}");
		}
		out.flush();
		
	}

	private void findid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String invitationId = req.getParameter("invitationId");
		bbs_invitation invition = is.findEdit(invitationId);
		if(invition != null) {
			req.getSession().setAttribute("invition", invition);
			resp.sendRedirect("server/order-update.jsp");
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置响应的文件类型
		resp.setContentType("application/json;charset=UTF-8");
		
		String invitationMessage = req.getParameter("invitationMessage");
		String plateId = req.getParameter("plateId");
		String categoryId = req.getParameter("categoryId");
		String userId = (String) req.getSession().getAttribute("userId");
		// 得到系统的默认时间
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String invitationId = userId+format.format(date);
	
		
		bbs_invitation it = new bbs_invitation(invitationId, invitationMessage, userId, Integer.parseInt(plateId), Integer.parseInt(categoryId));
		
				PrintWriter out = resp.getWriter();
				// user 就是放到uploadUser方法前面的用户对象
				boolean isOk =is.add(it);
				if(isOk) {
					out.write("{\"result\":\"true\"}");
				}else {
					out.write("{\"result\":\"false\"}");
				}
				out.flush();
		
	}

	private void show(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		//创建保存数据的集合
		List<bbs_invitation> list = null;
			try {
				list = is.getinvitionList();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		req.getSession().setAttribute("list", list);
		resp.sendRedirect("server/order-list.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
