package hit.poms.servlet;

import hit.poms.bean.transportBean;
import hit.poms.dao.transportDao;
import hit.poms.daoimpl.transportDaoimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class transportsearch extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public transportsearch() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		transportDao dao=new transportDaoimpl();
//		bean.setAddress(finaladdress);
//		bean.setCompname(compname);
//		bean.setLeadercontract(Long.parseLong(leadercontract));
//		bean.setDavitime(s.toString());
//		bean.setLeadername(leader);
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("type")!=null&&request.getParameter("type").equals("search")){
			Connection con=dao.getConnection();
		
			System.out.print((String)request.getParameter("searchword"));
			List<transportBean> list=dao.gettransportlist(con,(String)request.getParameter("searchword"));
	        request.setAttribute("transport", list);
			request.getRequestDispatcher("zhongxin_setting_main.jsp").forward(request, response);
			
		}
		else
		{		Connection con=dao.getConnection();
		List<transportBean> list=dao.gettransportlist(con);
		request.setAttribute("transport", list);
		request.getRequestDispatcher("zhongxin_setting_main.jsp").forward(request, response);}
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
