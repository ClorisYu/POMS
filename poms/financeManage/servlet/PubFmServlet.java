package hit.poms.financeManage.servlet;

import hit.poms.dao.entity.SubSellPub;
import hit.poms.financeManage.dao.PubFmDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PubFmServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String subId = req.getParameter("sub_name");
		String pubId = req.getParameter("pub_name");
		String time = req.getParameter("endDate");
		Long substation_id = 0L;
		Long pub_id = 0L;
		if(!"a".equals(subId)){
			substation_id = Long.parseLong(subId);
		}
		if(!"a".equals(pubId)){
			pub_id = Long.parseLong(pubId);
		}
		SubSellPub ssp = new SubSellPub();
		ssp.setSubstation_id(substation_id);
		ssp.setPub_id(pub_id);
		ssp.setSend_time(time);
		PubFmDAO dao = new PubFmDAO();
		List list = dao.doQuery(ssp);
		req.setAttribute("list", list);
		req.getRequestDispatcher("fm_deliver_count.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
