package hit.poms.deliverManager.print.servlet;

import hit.poms.deliverManager.print.dao.deliverManager_orderListDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverManager_orderPrintServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取arg参数，根据arg 的值跳转到不同的方法去执行
		String arg = req.getParameter("arg");System.out.println(arg);
		Long id=Long.parseLong(arg);
		
		//调用dao方法
		deliverManager_orderListDAO dao=new deliverManager_orderListDAO();
		List list=new ArrayList();
		
		list=dao.doQuery(id);
		//将list存储在request中
		req.setAttribute("list", list);
		
		//将结果显示在……Query.jsp页面
		req.getRequestDispatcher("deliverManager_orderPrint.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
