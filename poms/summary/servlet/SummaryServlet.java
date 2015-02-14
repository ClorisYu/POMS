package hit.poms.summary.servlet;

import hit.poms.summary.dao.SummaryDAO;
import hit.poms.summary.entity.SummaryInfor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SummaryServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
			doGet(req, resp);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String arg = req.getParameter("arg");
		System.out.println("-------arg="+arg);
		if ("query".equals(arg)) {
			doOrderSummary(req, resp);
		}if ("update".equals(arg)) {
			doSummaryUpdate(req, resp);
		}if ("summary".equals(arg)) {
			doSummary(req, resp);
		}
	}
	//下数单汇总
	protected void doOrderSummary(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
		String endDate = req.getParameter("endDate");
		System.out.println("----endDate="+endDate);
		
		SummaryInfor suminfo = new SummaryInfor();
		suminfo.setSummary_date(endDate);
		SummaryDAO dao = new SummaryDAO();
		boolean flag = dao.doQuery(suminfo, endDate);
		List list = null;
		if(flag == true) {                  //从汇总表中查询数据
			list = dao.doOrderSummaryHuizong(suminfo);
		}
		else{                               //从订单表中查询数据
			list = dao.doOrderSummary(suminfo);
		}
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
		req.setAttribute("msg", endDate);
		req.getRequestDispatcher("/orderSummary.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	/**
	 * 修改实际投递数时操作
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doSummaryUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
		String endDate = req.getParameter("summary_date");
		String sub_id = req.getParameter("substation_id");
		String pub_id = req.getParameter("pub_id");
		String real_num = req.getParameter("real_num");
		System.out.println("----endDate="+endDate+sub_id);
		System.out.println("update sub_id:" +sub_id+"33"+real_num);
		SummaryInfor suminfo = new SummaryInfor();
		
		suminfo.setSummary_date(endDate);
		suminfo.setSubstation_id( Long.parseLong(sub_id));
		suminfo.setPub_id(Long.parseLong(pub_id));
		suminfo.setReal_num(Long.parseLong(real_num));
		SummaryDAO dao = new SummaryDAO();
		dao.doSummaryUpdate(suminfo);
		
		//req.setAttribute("list", list);
		//req.setAttribute("summary_endDate", endDate);
		//
//		Student stu = new Student();
//		stu.setStu_name(stu_name);
		//stu.setRealname(realname);
		
		//调用dao的方法
//		StudentDAO dao = new StudentDAO();
//		List list = dao.doQuery(stu);
		//System.out.println(list.isEmpty());
		//将list存储到request中 
		List list =dao.doOrderSummaryHuizong(suminfo);
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.setAttribute("msg", endDate);
		req.getRequestDispatcher("/orderSummary.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	
	/**
	 * 每日汇总报刊信息汇总
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doSummary(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
		String endDate = req.getParameter("endDate");
		System.out.println("----endDate="+endDate);
		
		SummaryInfor suminfo = new SummaryInfor();
		suminfo.setSummary_date(endDate);
		SummaryDAO dao = new SummaryDAO();
		boolean flag = dao.doQuery(suminfo, endDate);
		if(flag == false){
			 req.setAttribute("msg1",endDate+"请先执行当日下数单统计");
			 req.getRequestDispatcher("/summary.jsp").forward(req, resp);
		}
		if(flag == true){
			List list = dao.doSummary(suminfo);
			
			//req.setAttribute("list", list);
			//req.setAttribute("summary_endDate", endDate);
			//String realname = req.getParameter("realname");
//			Student stu = new Student();
//			stu.setStu_name(stu_name);
			//stu.setRealname(realname);
			
			//调用dao的方法
//			StudentDAO dao = new StudentDAO();
//			List list = dao.doQuery(stu);
			//System.out.println(list.isEmpty());
			//将list存储到request中 
			req.setAttribute("list", list);
			if(list==null)  req.setAttribute("msg1","没有查询结果");
			//转发到查询结果页面
			if(list!=null){
				req.setAttribute("msg", endDate);
				req.getRequestDispatcher("/summary.jsp").forward(req, resp);	
			//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
			}
		}
		
	}
}
