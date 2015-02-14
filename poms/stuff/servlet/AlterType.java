package hit.poms.stuff.servlet;
/*
 * 管理员进行何种操作判断的servlet，管理员选择以后servlet负责相关页面的跳转
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterType extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String no = req.getParameter("no");
		String arg = req.getParameter("selection");
		System.out.println(arg);
		if("add".equals(arg)){
			req.getRequestDispatcher("add_stuff.jsp").forward(req,resp);
		}
		else{
			req.getRequestDispatcher("searchByNo?arg="+arg).forward(req,resp);

		}
//		else if("delete".equals(arg)){
//			boolean flag = judgeNo(no);
//			if(flag){
//				req.getRequestDispatcher("delete_stuff.jsp").forward(req,resp);
//
//			}
//		}
//		else if("query".equals(arg)){
//			boolean flag = judgeNo(no);
//			if(flag){
//				req.getRequestDispatcher("query_stuff.jsp").forward(req,resp);
//
//			}
//		}
//		else if("update".equals(arg)){
//			boolean flag = judgeNo(no);
//			if(flag){
//				req.getRequestDispatcher("update_stuff.jsp").forward(req,resp);
//
//			}
//		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
	public boolean judgeNo(String No){
		boolean flag = false;
		/*
		 * dao层代码，判断员工是否存在，后续完成；
		 */
		return true;
	}
}
