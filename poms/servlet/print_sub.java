package hit.poms.servlet;

import hit.poms.bean.Print_subBean;
import hit.poms.dao.pathDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class print_sub extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public print_sub() {
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
         pathDao dao=new pathDao();
         request.setCharacterEncoding("utf-8");
         Connection con=dao.getConnection();
         List<Print_subBean> list=null;
         if(request.getParameter("tag")!=null){
        	 list=dao.getprint_sub(con,request.getParameter("search"));
        	  request.setAttribute("print_list", list);
        	  request.getRequestDispatcher("fenfa_yindianmessage.jsp").forward(request, response);	 
         }
         else{list=dao.getprint_sub(con);
  request.setAttribute("print_list", list);
  request.getRequestDispatcher("fenfa_yindianmessage.jsp").forward(request, response);}
         
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
