package hit.poms.deliverManager.desManager.servlet;

import hit.poms.deliverManager.desManager.dao.DeliverManager_desManagerDAO;
import hit.poms.deliverManager.desManager.entity.DesEmp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverManager_desManagerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String arg=req.getParameter("arg");
		if("update".equals(arg)){
			doUpdate(req, resp);
		}else if("toupdate".equals(arg)){
			doToUpdate(req, resp);
		}else if("delete".equals(arg)){
			doDelete(req, resp);
		}else{
			doAdd(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		String destination=req.getParameter("destination");
		Long des_id2=Long.parseLong(des_id);
		
		// 将这些数据封装到user对象中
		DesEmp de = new DesEmp();
		de.setDes_id(des_id2);
		de.setDestination(destination);

		// 调用dao的方法
		DeliverManager_desManagerDAO dao = new DeliverManager_desManagerDAO();
		dao.doUpdate(de);

		// 转发到查询结果页面
		req.getRequestDispatcher("/deliverManager_desManagerQueryServlet?des_id="+"").forward(req, resp);
	}
	
	protected void doToUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		Long des_id2=Long.parseLong(des_id);
		
		// 调用dao的方法
		DeliverManager_desManagerDAO dao = new DeliverManager_desManagerDAO();
		DesEmp de = dao.doQueryById(des_id2);

		// 将user存储到request中
		req.setAttribute("de", de);

		// 转发到修改页面
		req.getRequestDispatcher("/deliverManager_desUpdate.jsp").forward(req, resp);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		Long des_id2=Long.parseLong(des_id);
		
		// 调用dao的方法
		DeliverManager_desManagerDAO dao = new DeliverManager_desManagerDAO();
		dao.doDelete(des_id2);

		

		// 转发到查询结果页面
		req.getRequestDispatcher("/deliverManager_desManagerQueryServlet?des_id="+"").forward(req, resp);
	}
	
	protected void doAdd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//js表单验证：destination不为空
		String destination=req.getParameter("destination");
		DesEmp de=new DesEmp(0L,0L,destination);
		
		// 调用dao的方法
		DeliverManager_desManagerDAO dao = new DeliverManager_desManagerDAO();
		Long des_id=dao.doAdd(de);
		
		

		// 转发到查询结果页面
		req.getRequestDispatcher("/deliverManager_desManagerQueryServlet?des_id="+des_id).forward(req, resp);
	}
}
