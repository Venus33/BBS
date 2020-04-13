package com.bbs.controller.Invitation_ans;

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

import com.bbs.entity.bbs_invitation_ans;
import com.bbs.entity.bbs_plate;
import com.bbs.service.bbs_invitation_ans.Invitation_ansservice;
import com.bbs.service.bbs_invitation_ans.Invitation_ansserviceImpl;

/**
 * Servlet implementation class Invitation_ans
 */
@WebServlet("/Invitation_ans")
public class Invitation_ans extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private Invitation_ansservice ias=new Invitation_ansserviceImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Invitation_ans() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("Utf-8");
		String op=req.getParameter("op");
		 if("add".equals(op)) {
			add(req,resp);
		}
	}


	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String userId=req.getParameter("userId");
		String invitationId=req.getParameter("invitationId");
		String ansMessage=req.getParameter("ansMessage");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ansId=userId+format.format(date);
		bbs_invitation_ans ans=new bbs_invitation_ans(ansId,ansMessage,invitationId,userId);
		PrintWriter out = resp.getWriter();
		// user 就是放到uploadUser方法前面的用户对象
		boolean isOk =ias.add(ans);
		if(isOk) {
			req.getRequestDispatcher("Invitation?op=findbyid").forward(req, resp);
		}else {
			resp.sendRedirect("server/reply.jsp");
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
