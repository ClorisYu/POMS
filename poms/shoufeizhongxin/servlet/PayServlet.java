package hit.poms.shoufeizhongxin.servlet;

import hit.poms.shoufeizhongxin.dao.OrderPayDAO;
import hit.poms.shoufeizhongxin.entity.OrderPayInfor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayServlet extends HttpServlet {
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
	String today_time = dateFormat.format( now );
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.print("woshuaxingle");
		String arg = req.getParameter("arg");
		System.out.println("-------arg=" + arg);
		if ("queryAll".equals(arg)) {
			doQueryAll(req, resp);
		}
		if ("queryNodeliver".equals(arg)) {
			doQueryNoDeliver(req, resp);
		}
		if ("queryByorderID".equals(arg)) {
			doQueryByorderID(req, resp);
		}
		if ("updatePay".equals(arg)) {
			doUpdatePay(req, resp);
		} if("updateDeliver".equals(arg)) {
			doChooseDeliver(req,resp);
		}
		if("toudi".equals(arg)) {
			doUpdateDeliver(req,resp);
		}
	}
	/**
	 * 选择相应投递员
	 */
	protected void doChooseDeliver(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
//		String endDate = req.getParameter("endDate");
//		System.out.println("----endDate="+endDate);
		
		boolean flag_1 = false;
		String order_id = req.getParameter("order_id");
		OrderPayDAO dao = new OrderPayDAO();
		OrderPayInfor orderpayinfo = new OrderPayInfor();
		//从先从数据库中读取，看是否有指定人员分陪
		List deliverlist = dao.chooseDeliver();
		List orderlist = dao.doOrderNoDeliver(orderpayinfo);
		req.setAttribute("deliverlist", deliverlist);
		req.setAttribute("order_id", order_id);
		req.getRequestDispatcher("/choosedeliver.jsp").forward(req, resp);	
	}
	/**
	 * 收费中心，订单修改是否投递
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doUpdateDeliver(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
//		String endDate = req.getParameter("endDate");
//		System.out.println("----endDate="+endDate);
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
		String sys_time = dateFormat.format( now );
		boolean flag_1 = false;
		String order_id = req.getParameter("order_id");
		String emp_id = req.getParameter("deliver_choose");
		OrderPayDAO dao = new OrderPayDAO();
		OrderPayInfor orderpayinfo = new OrderPayInfor();
		//从先从数据库中读取，看是否有指定人员分陪
			
		
	//	//OrderPayInfor orderpayinfo = new OrderPayInfor();
		orderpayinfo.setOrder_id(order_id);
		//orderpayinfo.setIf_deliver("1");
		//更新操作
		dao.doUpdateDeliver(orderpayinfo,emp_id); 
		dao.doUpdatePub(orderpayinfo);
		//增加COOKIES
		
		Cookie delivercookies = new Cookie(order_id,sys_time);
		delivercookies.setMaxAge(60*60*24*30);
		resp.addCookie(delivercookies);

		List list = dao.doOrderQueryAll(orderpayinfo);
		
		//将list存储到request中 
		req.setAttribute("list", list);
	//	/
		//if(list==null)  req.setAttribute("msg1","没有查询结果");
		//转发到查询结果页面
		req.setAttribute("msg", "订单");
		//if(req.getParameter("type")==null||req.getParameter("type").equals("norefresh")){
		req.getRequestDispatcher("/pay.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	/**
	 * 收费中心，订单修改是否付款
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doUpdatePay(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
//		String endDate = req.getParameter("endDate");
//		System.out.println("----endDate="+endDate);
		String order_id = req.getParameter("order_id");
		OrderPayInfor orderpayinfo = new OrderPayInfor();
		orderpayinfo.setOrder_id(order_id);
		orderpayinfo.setIf_paied("1");
		OrderPayDAO dao = new OrderPayDAO();
		dao.doUpdatePay(orderpayinfo); 
		List list = dao.doOrderQueryAll(orderpayinfo);
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
		req.setAttribute("msg", "全部订单列表");
		req.getRequestDispatcher("/pay.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	
	/**
	 * 收费中心，订单列表显示全部
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doQueryAll(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
//		String endDate = req.getParameter("endDate");
//		System.out.println("----endDate="+endDate);
		
		OrderPayInfor orderpayinfo = new OrderPayInfor();
		OrderPayDAO dao = new OrderPayDAO();
		List list = dao.doOrderQueryAll(orderpayinfo); 
		
		////从COOkie判断
	
			
		req.setAttribute("list", list);
		
		if(list==null)  req.setAttribute("msg1","没有查询结果");
		//转发到查询结果页面
		req.setAttribute("msg", "全部订单列表");
		req.getRequestDispatcher("/pay.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	/**
	 * 收费中心，订单列表显示未投递订单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doQueryNoDeliver(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
//		String endDate = req.getParameter("endDate");
//		System.out.println("----endDate="+endDate);
		
		OrderPayInfor orderpayinfo = new OrderPayInfor();
		OrderPayDAO dao = new OrderPayDAO();
		List list = dao.doOrderNoDeliver(orderpayinfo); 
		
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
		req.setAttribute("msg", "未投递订单列表");
		req.getRequestDispatcher("/pay.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	/**
	 * 收费中心，订单号查询显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doQueryByorderID(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//获取前台提交的条件
		String order_id = req.getParameter("order_id");
		System.out.println("order_id = "+order_id);
		OrderPayInfor orderpayinfo = new OrderPayInfor();
		orderpayinfo.setOrder_id(order_id);
		OrderPayDAO dao = new OrderPayDAO();
		List list = dao.doQueryByorderID(orderpayinfo); 
		
		
		//从COOkie判断
			/*boolean myflag = false;
			Cookie cookie[] = req.getCookies();
 			if(cookie!=null){
				for(int i=0;i<cookie.length;i++){
					Cookie c = cookie[i];
					if(c.getName().equals(order_id))					
					{
						if(c.getValue().equals("toudi"))
						{
							myflag = true;
						}
					}
				}
			}
 			req.setAttribute("myflag", myflag);*/
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
		req.setAttribute("msg", order_id+"订单");
		req.getRequestDispatcher("/pay.jsp").forward(req, resp);	
		//req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
}
