package hit.poms.servlet;

import hit.poms.bean.TotalprintBean;
import hit.poms.dao.GettotalprintDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Yinshuatotal extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Yinshuatotal() {
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
	//	if(getServletContext().getAttribute("dsz_flag")!=null)
		//System.out.print(getServletContext().getAttribute("dsz_flag"));
    if(getServletContext().getAttribute("dsz_flag")==null){
    	request.getRequestDispatcher("noauthor.jsp").forward(request, response);

    }else if((Integer)getServletContext().getAttribute("dsz_flag")!=1&&(Integer) getServletContext().getAttribute("dsz_flag")!=2){
    	request.getRequestDispatcher("noauthor.jsp").forward(request, response);
    }else{
    	GettotalprintDAO dao=new GettotalprintDAO();
    	Connection con=dao.getConnection();
    	List<TotalprintBean> list=dao.gettotalprint(con);
    	request.setAttribute("TotalprintBean", list);
    	getServletContext().setAttribute("TotalprintBean", list);
    	request.getRequestDispatcher("huizong.jsp").forward(request, response);
    }
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
