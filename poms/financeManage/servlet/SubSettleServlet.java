package hit.poms.financeManage.servlet;

import hit.poms.dao.entity.CustomerBookSub;
import hit.poms.dao.entity.SubSellPub;
import hit.poms.financeManage.dao.SubSettleDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubSettleServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String arg = req.getParameter("arg");
		if(arg.equals("queryall")){
			doQueryAll(req,resp);
		}
	}

	private void doQueryAll(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		String time = req.getParameter("endDate");
		SubSellPub ssp = new SubSellPub();
		ssp.setSend_time(time);
		SubSettleDAO dao = new SubSettleDAO();
		List list = dao.doQuery(ssp);
		req.setAttribute("list", list);
		req.getRequestDispatcher("fm_settle.jsp").forward(req, resp);
	}
}
