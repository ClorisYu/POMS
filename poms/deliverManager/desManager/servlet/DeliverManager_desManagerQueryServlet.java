package hit.poms.deliverManager.desManager.servlet;

import hit.poms.deliverManager.desManager.dao.DeliverManager_desManagerDAO;
import hit.poms.deliverManager.desManager.entity.DesEmp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverManager_desManagerQueryServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String des_id=req.getParameter("des_id");//System.out.println(des_id+"aaaaa");
		Long des_id2=0L;
		if(!"".equals(des_id)){
			des_id2=Long.parseLong(des_id);
		}else{
			des_id2=null;
		}
		DesEmp de=new DesEmp();
		de.setDes_id(des_id2);
		
		DeliverManager_desManagerDAO dao=new DeliverManager_desManagerDAO();
		List list = dao.doQuery(de);
		
		// 将list存储到request中
		req.setAttribute("list1", list);

		//
		req.getRequestDispatcher("/deliverManager_desManagerMain.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
