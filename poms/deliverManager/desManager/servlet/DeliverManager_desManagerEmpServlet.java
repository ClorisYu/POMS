package hit.poms.deliverManager.desManager.servlet;

import hit.poms.deliverManager.desManager.dao.DeliverManager_desEmpManagerDAO;
import hit.poms.deliverManager.desManager.dao.DeliverManager_desManagerDAO;
import hit.poms.deliverManager.desManager.entity.DesEmp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverManager_desManagerEmpServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String arg=req.getParameter("arg");
		if("delete".equals(arg)){
			doDelete(req,resp);
		}else if("toupdate".equals(arg)){
			doToUpdate(req,resp);
		}else if("update".equals(arg)){
			doUpdate(req,resp);
		}else if("add".equals(arg)){
			doAdd(req,resp);
		}else if("toadd".equals(arg)){
			doToAdd(req,resp);
		}else{
			doQuery(req,resp);
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void doAdd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//js表单验证：destination不为空
		String des_id=req.getParameter("des_id");
		String emp_id=req.getParameter("emp_id");
		Long des_id2=Long.parseLong(des_id);
		Long emp_id2=Long.parseLong(emp_id);
		DeliverManager_desEmpManagerDAO dao = new DeliverManager_desEmpManagerDAO();
		
		DesEmp de = new DesEmp();
		de.setDes_id(des_id2);
		de.setEmp_id(emp_id2);
		req.setAttribute("de", de);
		//投递段不存在
		if(dao.doQueryDes(des_id2)==null){
			req.setAttribute("msg1", "des_id not exist!");
			req.getRequestDispatcher("/deliverManager_desEmpAdd.jsp").forward(req, resp);
		}else{
		//投递段存在，该投递员不存在
		if(!dao.doQueryEmp(emp_id2)){
			req.setAttribute("msg2", "emp_id not exist!");
			req.getRequestDispatcher("/deliverManager_desEmpAdd.jsp").forward(req, resp);
		}else{
			//投递员存在，emp_id和des_id组存在
			if(!dao.doQueryED(emp_id2,des_id2)){
				req.setAttribute("msg2", "relation has existed!");
				req.getRequestDispatcher("/deliverManager_desEmpAdd.jsp").forward(req, resp);
			}else{
				//满足条件，添加进数据库
				String destination=dao.doQueryDes(des_id2);
				de.setDestination(destination);
				Long des_id3=dao.doEmpAdd(de);

				// 转发到查询结果页面
				req.getRequestDispatcher("/deliverManager_desManagerEmpServlet?arg=query&des_id="+des_id3).forward(req, resp);
				
			}
		}
		}
	}
	
	protected void doToAdd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		Long des_id2=Long.parseLong(des_id);
		
		// 调用dao的方法
		DesEmp de = new DesEmp();
		de.setDes_id(des_id2);

		// 将user存储到request中
		req.setAttribute("de", de);

		// 转发到修改页面
		req.getRequestDispatcher("/deliverManager_desEmpAdd.jsp").forward(req, resp);
	}
	
	protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		String emp_id=req.getParameter("emp_id");
		String old_des_id=req.getParameter("old_des_id");
		Long des_id2=Long.parseLong(des_id);
		Long emp_id2=Long.parseLong(emp_id);
		Long old_des_id2=Long.parseLong(old_des_id);
		String destination="a";
		//根据des_id查出唯一对应destination
		DeliverManager_desEmpManagerDAO dao = new DeliverManager_desEmpManagerDAO();
		destination=dao.doQueryDes(des_id2);
		if(destination==null){
			DesEmp de = new DesEmp();
			de.setDes_id(des_id2);
			de.setEmp_id(emp_id2);
			req.setAttribute("de", de);
			req.setAttribute("msg", "des_id not exist!");
			req.getRequestDispatcher("/deliverManager_desEmpUpdate.jsp").forward(req, resp);
		}else{
		
			// 将这些数据封装到user对象中
			DesEmp de = new DesEmp();
			de.setDes_id(des_id2);
			de.setEmp_id(emp_id2);
			de.setDestination(destination);
	
			// 调用dao的方法
			
			dao.doUpdate(de,old_des_id2);
	
			// 转发到查询结果页面
			req.getRequestDispatcher("/deliverManager_desManagerEmpServlet?arg=query").forward(req, resp);
		}
	}
	
	protected void doToUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		Long des_id2=Long.parseLong(des_id);
		String emp_id=req.getParameter("emp_id");
		Long emp_id2=Long.parseLong(emp_id);
		
		// 调用dao的方法
		DesEmp de = new DesEmp();
		de.setDes_id(des_id2);
		de.setEmp_id(emp_id2);

		// 将user存储到request中
		req.setAttribute("de", de);

		// 转发到修改页面
		req.getRequestDispatcher("/deliverManager_desEmpUpdate.jsp").forward(req, resp);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String des_id=req.getParameter("des_id");
		Long des_id2=Long.parseLong(des_id);
		String emp_id=req.getParameter("emp_id");
		Long emp_id2=Long.parseLong(emp_id);
		
		DesEmp de=new DesEmp();
		de.setDes_id(des_id2);
		de.setEmp_id(emp_id2);
		
		// 调用dao的方法
		DeliverManager_desEmpManagerDAO dao = new DeliverManager_desEmpManagerDAO();
		dao.doDelete(de);

		
		
		// 转发到查询结果页面
		req.getRequestDispatcher("/deliverManager_desManagerEmpServlet?arg=query").forward(req, resp);
	}
	
	protected void doQuery(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String emp_id=req.getParameter("emp_id");
		Long emp_id2=Long.parseLong(emp_id);
		
		DesEmp de=new DesEmp();
		de.setEmp_id(emp_id2);
		
		DeliverManager_desEmpManagerDAO dao=new DeliverManager_desEmpManagerDAO();
		List list = dao.doQuery(de);
		
		// 将list存储到request中
		req.setAttribute("list2", list);

		//
		req.getRequestDispatcher("/deliverManager_desManagerMain.jsp").forward(req, resp);
	}
}
