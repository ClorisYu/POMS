package hit.poms.bonus.servlet;

import hit.poms.bonus.dao.bonusDAO;
import hit.poms.bonus.entity.BonusInfor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BonusServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doGet(req, resp);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String arg = req.getParameter("arg");
		if("querycard".equals(arg)){
			doQueryCard(req,resp);
		}if("toupdate".equals(arg)){
			doToUpdate(req,resp);
		}if("add".equals(arg)){
			doAdd(req,resp);
		}if("update".equals(arg)){
			doUpdate(req,resp);
		}if("toAdd".equals(arg)){
			doToAdd(req,resp);
		}
			
	}
	/**
	 *增加赠卡前先查询赠卡
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doToAdd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		
		bonusDAO dao = new bonusDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
		List list = dao.doToADD();
		
//		req.setAttribute("list", list);
//		req.setAttribute("summary_endDate", endDate);
//		String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
		
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		if(list==null)  System.out.println("没有查询结果");
		if(list==null)  req.setAttribute("msg1","没有查询结果");
		//转发到查询结果页面
		
		req.getRequestDispatcher("/zengka_add.jsp").forward(req, resp);	
	}
	/**
	 * 类型查询赠卡
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doQueryCard(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String zengka_type = req.getParameter("zengka_type");
		System.out.println("zengka_type = "+zengka_type);
		BonusInfor bonus = new BonusInfor();
		bonus.setCard_type(zengka_type);
		
		bonusDAO dao = new bonusDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
		dao.doDelete();
		List list = dao.doQueryByType(bonus);
		
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
		req.setAttribute("msg","金卡信息表");
		req.getRequestDispatcher("/zengka_infor.jsp").forward(req, resp);	
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
		String card_id = req.getParameter("card_id");
		System.out.println("card_id = "+card_id);
		BonusInfor bonus = new BonusInfor();
		bonus.setCard_id(card_id);
		
		bonusDAO dao = new bonusDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
		List list = dao.doQueryByID(bonus);
		
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
		req.getRequestDispatcher("/zengka_modify.jsp").forward(req, resp);	
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
		String card_id = req.getParameter("card_id");
		String card_type = req.getParameter("card_type");
		String pub_id = req.getParameter("pub_id");
		String start_time = req.getParameter("start_time");
		String endDate = req.getParameter("endDate");
		String card_infor = req.getParameter("card_infor");
		System.out.println("card_type = "+card_type);
		BonusInfor bonus = new BonusInfor();
		bonus.setCard_id(card_id);
		bonus.setCard_type(card_type);
		bonus.setPub_id(pub_id);
		bonus.setStart_time(start_time);
		bonus.setEnd_time(endDate);
		bonus.setCard_infor(card_infor);
		bonusDAO dao = new bonusDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
	    dao.doUpdate(bonus);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
	    BonusInfor bonus1 = new BonusInfor();
		List list = dao.doQueryByType(bonus1);
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		//转发到查询结果页面	
		req.getRequestDispatcher("/zengka_infor.jsp").forward(req, resp);	
	}
	/**
	 * 增加赠卡功能函数
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doAdd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String card_type = req.getParameter("card_type");
		String pub_id = req.getParameter("pub_id");
		String start_time = req.getParameter("start_time");
		String endDate = req.getParameter("endDate");
		String card_infor = req.getParameter("card_infor");
		System.out.println("card_type = "+card_type);
		BonusInfor bonus = new BonusInfor();
		bonus.setCard_type(card_type);
		bonus.setPub_id(pub_id);
		bonus.setStart_time(start_time);
		bonus.setEnd_time(endDate);
		bonus.setCard_infor(card_infor);
		bonusDAO dao = new bonusDAO();
		//boolean flag = dao.doQuery(suminfo, endDate);
	    dao.doAdd(bonus);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//String realname = req.getParameter("realname");
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
	    BonusInfor bonus1 = new BonusInfor();
		List list = dao.doQueryByType(bonus1);
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		req.setAttribute("list", list);
		//转发到查询结果页面	
		req.getRequestDispatcher("/zengka_infor.jsp").forward(req, resp);	
	}
}
