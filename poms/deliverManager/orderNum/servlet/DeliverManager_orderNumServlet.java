package hit.poms.deliverManager.orderNum.servlet;

import hit.poms.deliverManager.orderNum.dao.DeliverManager_orderNumDAO;
import hit.poms.deliverManager.orderNum.entity.PrintSend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverManager_orderNumServlet extends HttpServlet{
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
		String sub_id=req.getParameter("sub_id");
		String send_time=req.getParameter("send_time");
		Long sub_id2=Long.parseLong(sub_id);
		
		PrintSend ps=new PrintSend();
		ps.setSub_id(sub_id2);
		ps.setSend_time(send_time);
		
		DeliverManager_orderNumDAO dao=new DeliverManager_orderNumDAO();
		List list = dao.doQuery(ps);
		Long sum = dao.doQuerySum(ps);
		
		// 将list存储到request中
		req.setAttribute("list", list);
		req.setAttribute("sum", sum);

		//
		req.getRequestDispatcher("/deliverManager_orderNum.jsp").forward(req, resp);
		
		
	}
}
