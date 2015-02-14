package hit.poms.deliverManager.print.servlet;

import hit.poms.deliverManager.print.dao.deliverManager_orderListDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverManager_orderListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doQuery(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void doQuery(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String order_id=req.getParameter("order_id");//System.out.println(emp_id+"aaaa");
		Long id;
		if(!"".equals(order_id)){
			id=Long.parseLong(order_id);
		}else{
			id=null;
		}
		
		//调用dao方法
		deliverManager_orderListDAO dao=new deliverManager_orderListDAO();
		List list=new ArrayList();
		
		list=dao.doQuery(id);
		//将list存储在request中
		req.setAttribute("list", list);
		
		//将结果显示在……Query.jsp页面
		req.getRequestDispatcher("deliverManager_orderQueryList.jsp").forward(req, resp);
	}
}
