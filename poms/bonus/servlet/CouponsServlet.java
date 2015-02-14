package hit.poms.bonus.servlet;

import hit.poms.bonus.dao.bonusDAO;
import hit.poms.bonus.dao.couponsDAO;
import hit.poms.bonus.entity.BonusInfor;
import hit.poms.bonus.entity.Coupons;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CouponsServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doGet(req, resp);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String arg = req.getParameter("arg");
		if("querycoupons".equals(arg)){
			doQueryCoupons(req,resp);
		}if("toupdate".equals(arg)){
			doToUpdate(req,resp);
		}if("add".equals(arg)){
			doAdd(req,resp);
		}if("update".equals(arg)){
			doUpdate(req,resp);
		}
			
	}
	/**
	 * 类型查询赠卡
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doQueryCoupons(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String coupons_type = req.getParameter("coupons_type");
		System.out.println("coupons_type = "+coupons_type);
		Coupons coupons = new Coupons();
		coupons.setCoupons_type(coupons_type);
		
		couponsDAO dao = new couponsDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
		/*
		 * 查询前先删掉过期的代金卷类型
		 */
		dao.doDelete();                     
		List list = dao.doQueryByType(coupons);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
		
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		
		if(list==null)  req.setAttribute("msg1","没有查询结果");
		//转发到查询结果页面
		req.getRequestDispatcher("/daijinjuan_infor.jsp").forward(req, resp);	
	}
	/**
	 * 修改赠卡时，先查询赠卡ID功能函数
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doToUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String coupons_id = req.getParameter("coupons_id");
		System.out.println("coupons_id = "+coupons_id);
		Coupons coupons = new Coupons();
		coupons.setCoupons_id(coupons_id);
		
		couponsDAO dao = new couponsDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
		List list = dao.doQueryByID(coupons);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
		
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		//转发到查询结果页面	
		req.getRequestDispatcher("/daijinjuan_modify.jsp").forward(req, resp);	
	}
	/**
	 * 修改赠卡功能函数
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String coupons_id = req.getParameter("coupons_id");
		String coupons_type = req.getParameter("coupons_type");
		String coupons_money = req.getParameter("coupons_money");
		String start_time = req.getParameter("start_time");
		String endDate = req.getParameter("endDate");
		String coupons_infor = req.getParameter("coupons_infor");
		System.out.println("coupons_type = "+coupons_type);
		Coupons coupons = new Coupons();
		coupons.setCoupons_id(coupons_id);
		coupons.setCoupons_type(coupons_type);
		coupons.setCoupons_money(coupons_money);
		coupons.setStart_time(start_time);
		coupons.setEnd_time(endDate);
		coupons.setCoupons_infor(coupons_infor);
		couponsDAO dao = new couponsDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
	    dao.doUpdate(coupons);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
	    Coupons coupons1 = new Coupons();
		List list = dao.doQueryByType(coupons1);
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		if(list==null)  req.setAttribute("msg1","没有查询结果");
		//转发到查询结果页面	
		req.getRequestDispatcher("/daijinjuan_infor.jsp").forward(req, resp);	
	}
	/**
	 * 增加代金卷功能函数
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doAdd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String coupons_type = req.getParameter("coupons_type");
		String coupons_money = req.getParameter("coupons_money");
		String start_time = req.getParameter("start_time");
		String endDate = req.getParameter("endDate");
		String coupons_infor = req.getParameter("coupons_infor");
		System.out.println("coupons_type = "+coupons_type);
		Coupons coupons = new Coupons();
		coupons.setCoupons_type(coupons_type);
		coupons.setCoupons_money(coupons_money);
		coupons.setStart_time(start_time);
		coupons.setEnd_time(endDate);
		coupons.setCoupons_infor(coupons_infor);
		couponsDAO dao = new couponsDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
	    dao.doAdd(coupons);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
	    Coupons coupons1 = new Coupons();
		List list = dao.doQueryByType(coupons1);
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		//转发到查询结果页面	
		req.getRequestDispatcher("/daijinjuan_infor.jsp").forward(req, resp);	
	}
}
