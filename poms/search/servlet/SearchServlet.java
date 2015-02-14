package hit.poms.search.servlet;

import hit.poms.bean.emp_sell;
import hit.poms.search.dao.Change_reasonDAO;
import hit.poms.search.dao.Customer_book_subDAO;
import hit.poms.search.dao.Pub_messageDAO;
import hit.poms.search.dao.Reason_typeDAO;
import hit.poms.search.dao.Sub_sell_pubDAO;
import hit.poms.search.entity.Change_reason;
import hit.poms.search.entity.Customer_book_sub;
import hit.poms.search.entity.Pub_message;
import hit.poms.search.entity.Sub_sell_pub;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取arg参数，根据arg的值跳转到不同的方法去执行
		String arg = req.getParameter("arg");
		if ("Customer_bookQuery".equals(arg)) {
			doCustomer_bookQuery(req, resp);
		}else if("Pub_messageQuery".equals(arg)){
			doPub_messageQuery(req, resp);
		}else if("OrderQuery".equals(arg)){
			doOrderQuery(req, resp);
		}else if("Sub_sellQuery".equals(arg)){
			doSub_sellQuery(req, resp);
		}else if("Sub_logQuery".equals(arg)){
			doSub_logQuery(req, resp);
		}else if("Sub_orderQuery".equals(arg)){
			doSub_orderQuery(req, resp);
		}else if("Sub_orderUpdate".equals(arg)){
			doToSub_orderUpdate(req, resp);
		}else if("OrderUpdate".equals(arg)){
			doSub_orderUpdate(req, resp);
		}else if("Sub_orderDelete".equals(arg)){
			doSub_orderDelete(req, resp);
		}else if("Order_pubQuery".equals(arg)){
			doOrder_pubQuery(req, resp);
		}else if("ToOrder_pub".equals(arg)){
			doToOrder_pub(req, resp);
		}else if("Create_order".equals(arg)){
			doCreate_order(req, resp);
		}else if("ToChangeOrder".equals(arg)){
			doToChangeOrder(req, resp);
		}else if("ToXuDing".equals(arg)){
			doToXuDing(req, resp);
		}else if("XuDing".equals(arg)){
			doXuDing(req, resp);
		}else if("ToTuiDing".equals(arg)){
			doToTuiDing(req, resp);
		}else if("TuiDing".equals(arg)){
			doTuiDing(req, resp);
		}
	}

	//订户订购查询功能
	protected void doCustomer_bookQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String customer_id = req.getParameter("customer_id");
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		Customer_book_sub customer_book_sub = new Customer_book_sub();
		
		//为查询数据库做准备--customer_id
		if(!"".equals(customer_id) && customer_id != null )
			customer_book_sub.setCustomer_id(customer_id);
		else 
			customer_id="";
		//为查询数据库做准备--start_time
		if(!"".equals(start_time) && start_time != null)
			customer_book_sub.setStart_time(start_time);
		else 
			start_time="";
		//为查询数据库做准备--end_time
		if(!"".equals(end_time) && end_time != null)
			customer_book_sub.setEnd_time(end_time);
		else 
			end_time="";
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		List list = dao.doQuery(customer_book_sub);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/customer_book_query.jsp").forward(req, resp);		
	}
	
	//报刊信息查询功能
	protected void doPub_messageQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String pub_id = req.getParameter("pub_id");
		String pub_name = req.getParameter("pub_name");
		String delivery_unit = req.getParameter("delivery_unit");
		Pub_message pub_message = new Pub_message();
		
		//为查询数据库做准备--pub_id
		if(!"".equals(pub_id) && pub_id != null )
			pub_message.setPub_id(pub_id);
		else 
			pub_id="";
		//为查询数据库做准备--pub_name
		if(!"".equals(pub_name) && pub_name != null)
			pub_message.setPub_name(pub_name);
		else 
			pub_name="";
		//为查询数据库做准备--delivery_unit
		if(!"".equals(delivery_unit) && delivery_unit != null)
			pub_message.setDelivery_unit(delivery_unit);
		else 
			delivery_unit="";
		
		
		//调用dao的方法
		Pub_messageDAO dao = new Pub_messageDAO();
		List list = dao.doQuery(pub_message);
		
		//将list存储到request中 
		req.setAttribute("list", list);
	
		//转发到查询结果页面
		req.getRequestDispatcher("/pub_query.jsp").forward(req, resp);		
	}
	
	//报刊订单查询功能
	protected void doOrderQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String customer_id = req.getParameter("customer_id");
		String pub_id = req.getParameter("pub_id");
		
		Customer_book_sub customer_book_sub = new Customer_book_sub();
		
		//为查询数据库做准备--customer_id
		if(!"".equals(customer_id) && customer_id != null )
			customer_book_sub.setCustomer_id(customer_id);
		else 
			customer_id="";
		//为查询数据库做准备--pub_id
		if(!"".equals(pub_id) && pub_id != null)
			customer_book_sub.setPub_id(pub_id);
		else 
			pub_id="";
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		List list = dao.doQuery(customer_book_sub);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/order_query.jsp").forward(req, resp);		
	}

	//站点月表查询功能
	protected void doSub_sellQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String substation_id = req.getParameter("substation_id");
		String send_time = req.getParameter("month_time");
		
		
		Sub_sell_pub sub_sell_pub = new Sub_sell_pub();
		
		//为查询数据库做准备--substation_id
		if(!"".equals(substation_id) && substation_id != null )
			sub_sell_pub.setSubstation_id(substation_id);
		else 
			substation_id="";
		//为查询数据库做准备--send_time
		if(!"".equals(send_time) && send_time != null)
			sub_sell_pub.setSend_time(send_time);
		else 
			send_time="";
		
		
		
		//调用dao的方法
		Sub_sell_pubDAO dao = new Sub_sell_pubDAO();
		List list = dao.doQuery(sub_sell_pub);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/sub_sell_query.jsp").forward(req, resp);		
	}
	
	//分站日志查询功能
	protected void doSub_logQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String substation_id = req.getParameter("substation_id");
		String substation_name = req.getParameter("substation_name");
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		Customer_book_sub customer_book_sub = new Customer_book_sub();
		
		//为查询数据库做准备--substation_id
		if(!"".equals(substation_id) && substation_id != null )
			customer_book_sub.setSubstation_id(substation_id);
		else 
			substation_id="";
		//为查询数据库做准备--start_time
		if(!"".equals(start_time) && start_time != null)
			customer_book_sub.setStart_time(start_time);
		else 
			start_time="";
		//为查询数据库做准备--end_time
		if(!"".equals(end_time) && end_time != null)
			customer_book_sub.setEnd_time(end_time);
		else 
			end_time="";
		//为查询数据库做准备--substation_name
		if(!"".equals(substation_name) && substation_name != null)
			customer_book_sub.setSubstation_name(substation_name);
		else 
			substation_name="";
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		List list = dao.doLogQuery(customer_book_sub);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/sub_log_query.jsp").forward(req, resp);		
	}
	
	//分站订单查询功能
	protected void doSub_orderQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String substation_id = req.getParameter("substation_id");
		String substation_name = req.getParameter("substation_name");
		
		Customer_book_sub customer_book_sub = new Customer_book_sub();
		
		//为查询数据库做准备--substation_id
		if(!"".equals(substation_id) && substation_id != null )
			customer_book_sub.setSubstation_id(substation_id);
		else 
			substation_id="";
		//为查询数据库做准备--substation_name
		if(!"".equals(substation_name) && substation_name != null)
			customer_book_sub.setSubstation_name(substation_name);
		else 
			substation_name="";
		
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		List list = dao.doQuery(customer_book_sub);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);		
	}

	
	//转到订单修改页面
	protected void doToSub_orderUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的id
		String id = req.getParameter("id");
		int id_1 = 0;
		if(!"".equals(id)){
			id_1 = Integer.parseInt(id);
		}
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		Customer_book_sub customer_book_sub = dao.doQueryById(id_1);
		
		//将user存储到request中 
		req.setAttribute("customer_book_sub", customer_book_sub);
		
		//转发到修改页面
		req.getRequestDispatcher("/sub_order_update.jsp").forward(req, resp);		
	}
	
	//订单修改功能
	protected void doSub_orderUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//先获取从前台提交的所有数据
		String order_id = req.getParameter("order_id");
		String substation_id = req.getParameter("substation_id");
		String pub_id = req.getParameter("pub_id");
		String customer_id = req.getParameter("customer_id");
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		String pub_money = req.getParameter("pub_money");
		String pub_need_num = req.getParameter("pub_need_num");
		String if_paied = req.getParameter("if_paied");
		String gift_type = req.getParameter("gift_type");
		String discount = req.getParameter("discount");
		String gift_num = req.getParameter("gift_num");
		String paied_money = req.getParameter("paied_money");
		
		
		
		//将这些数据封装到user对象中
		Customer_book_sub customer_book_sub = new Customer_book_sub(order_id, if_paied, pub_id, substation_id, customer_id,
				start_time, end_time, pub_money, pub_need_num, gift_type, discount, gift_num, paied_money);
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		dao.doUpdate(customer_book_sub);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	
	//订单删除功能
	protected void doSub_orderDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的id
		String id = req.getParameter("id");
		int id_1 = 0;
		if(!"".equals(id)){
			id_1 = Integer.parseInt(id);
		}
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		dao.doDelete(id_1);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/sub_order_query.jsp").forward(req, resp);
	}
	
	//订购查询功能
	protected void doOrder_pubQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String pub_type = new String(req.getParameter("kind").getBytes("iso-8859-1"),"utf-8");
		
		Pub_message pub_message = new Pub_message();
		
		//为查询数据库做准备--pub_type
		if(!"".equals(pub_type) && pub_type != null )
			pub_message.setPub_type(pub_type);
		else 
			pub_type="";
		
		
		//调用dao的方法
		Pub_messageDAO dao = new Pub_messageDAO();
		List list = dao.doQuery(pub_message);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		req.setAttribute("kind", pub_type);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/order.jsp").forward(req, resp);		
	}
	
	//订购功能
	protected void doToOrder_pub(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String pub_id = req.getParameter("pub_id");
		String customer_id = req.getParameter("customer_id");
		Customer_book_sub customer_book_sub = new Customer_book_sub();
		
		//为查询数据库做准备--customer_id
		if(!"".equals(customer_id) && customer_id != null )
			customer_book_sub.setCustomer_id(customer_id);
		else {
			customer_id="";
			req.setAttribute("flag", false);
			req.getRequestDispatcher("/payway.jsp").forward(req, resp);	
		}
		//为查询数据库做准备--pub_id
		if(!"".equals(pub_id) && pub_id != null )
			customer_book_sub.setPub_id(pub_id);
		else 
			pub_id="";
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		customer_book_sub = dao.doOrderQuery(customer_book_sub);
		if(customer_book_sub!=null){
			//将list存储到request中 
			req.setAttribute("customer_book_sub", customer_book_sub);
			//转发到查询结果页面
			req.getRequestDispatcher("/paydetail.jsp").forward(req, resp);	
		}else{
			req.setAttribute("flag", false);
			req.getRequestDispatcher("/payway.jsp").forward(req, resp);	
		}
			
	}
	
	//订单生成功能
	protected void doCreate_order(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//先获取从前台提交的所有数据
		String customer_id = req.getParameter("customer_id");
		String substation_id = req.getParameter("substation_id");
		String pub_id = req.getParameter("pub_id");
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		String pub_price = req.getParameter("pub_price");
		String pub_need_num = req.getParameter("pub_need_num");
		String if_paied = "0";
		String gift_type = "0";
		String discount = req.getParameter("discount");
		String gift_num = "0";
		String paied_money = "0";
		String pay_way = req.getParameter("pay_way");
		String if_toudi = "0";
		String pub_money = (Double.parseDouble(discount)*Integer.parseInt(pub_need_num)*Double.parseDouble(pub_price))+"";
		
		String pub_name = req.getParameter("pub_name");
		String pub_type = req.getParameter("pub_type");
		
		String customer_address = req.getParameter("customer_address");
		String customer_phone = req.getParameter("customer_phone");
		
		
		//将这些数据封装到user对象中
		Customer_book_sub customer_book_sub = new Customer_book_sub("0", if_paied, pub_id, substation_id, customer_id,
				if_toudi, start_time, end_time, pub_money, pub_need_num, gift_type, discount, gift_num, paied_money, pay_way);
	
		
		//更改报刊的发行量和剩余量
		Pub_messageDAO pdao = new Pub_messageDAO();
		Pub_message pub_message = pdao.doQueryById(Long.parseLong(pub_id));
		int add = Integer.parseInt(pub_need_num);

		if(Integer.parseInt(pub_message.getRest_num())-add<0){
		//	req.setAttribute("addtag", customer_book_sub);

		
			//转发到error页面
			req.getRequestDispatcher("/MyJsp.jsp").forward(req, resp);	
			
		}else{
			pub_message.setSell_num((Integer.parseInt(pub_message.getSell_num())+add)+"");
			pub_message.setRest_num((Integer.parseInt(pub_message.getRest_num())-add)+"");
			
			//调用dao的方法
			Customer_book_subDAO dao = new Customer_book_subDAO();
			String id = dao.doCreate(customer_book_sub);
			
			customer_book_sub.setOrder_id(id);
			customer_book_sub.setPub_name(pub_name);
			customer_book_sub.setPub_type(pub_type);
			customer_book_sub.setCustomer_address(customer_address);
			customer_book_sub.setCustomer_phone(customer_phone);
		    pdao.doUpdate(pub_message);
		    emp_sell bean=new emp_sell();
		    bean.setCustomer_id(Long.parseLong(customer_id));
		    bean.setEmp_delivery(0);
		    bean.setEmp_id((Long)getServletContext().getAttribute("empid"));
		    bean.setGet_money(0);
		    bean.setEmp_order(add);
		    bean.setPub_id(Long.parseLong(pub_id));
		    bean.setFinal_time(end_time);
		    dao.insertintopus_sub(bean);
		    req.setAttribute("customer_book_sub", customer_book_sub);
		
		//转发到查询结果页面
		    req.getRequestDispatcher("/order_detail.jsp").forward(req, resp);}
	}
	
	//按客户号号查询,准备实现续订退订功能
	protected void doToChangeOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的条件
		String customer_id = req.getParameter("customer_id");
		
		Customer_book_sub customer_book_sub = new Customer_book_sub();
		
		//为查询数据库做准备--customer_id
		if(!"".equals(customer_id) && customer_id != null )
			customer_book_sub.setCustomer_id(customer_id);
		else 
			customer_id="";		
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		List list = dao.doQuery(customer_book_sub);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		
		//转发到查询结果页面
		req.getRequestDispatcher("/xuding.jsp").forward(req, resp);		
	}
	
	//转到续订修改页面
	protected void doToXuDing(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的id
		String id = req.getParameter("id");
		int id_1 = 0;
		if(!"".equals(id)){
			id_1 = Integer.parseInt(id);
		}
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		Customer_book_sub customer_book_sub = dao.doQueryById(id_1);
		
		Reason_typeDAO rdao=new Reason_typeDAO();
		List reason_list = rdao.doQuery();
		req.setAttribute("reason_list", reason_list);
		
		//将user存储到request中 
		req.setAttribute("customer_book_sub", customer_book_sub);
		
		//转发到修改页面
		req.getRequestDispatcher("/pub_num_update.jsp").forward(req, resp);		
	}

	//订单修改功能
	protected void doXuDing(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//先获取从前台提交的所有数据
		String order_id = req.getParameter("order_id");
		String pub_id = req.getParameter("pub_id");
		String end_time = req.getParameter("end_time");
		String pub_add_num = req.getParameter("pub_add_num");
		String change_type = "续订";
		String reason_type = req.getParameter("reason_type");
		String reason = req.getParameter("reason");
		
		int id_1 = 0;
		int id_2 = 0;
		int add = 0; 
		if(!"".equals(order_id)){
			id_1 = Integer.parseInt(order_id);
		}
		if(!"".equals(pub_id)){
			id_2 = Integer.parseInt(pub_id);
		}
		if(!"".equals(pub_add_num)){
			add = Integer.parseInt(pub_add_num);
		}
		
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		
		Customer_book_sub customer_book_sub = dao.doQueryById(id_1);
		customer_book_sub.setPub_need_num((Integer.parseInt(customer_book_sub.getPub_need_num())+add)+"");
		customer_book_sub.setEnd_time(end_time);
		System.out.println((Integer.parseInt(customer_book_sub.getPub_need_num())*Double.parseDouble(customer_book_sub.getDiscount())*Double.parseDouble(customer_book_sub.getPub_price()))+"");
		customer_book_sub.setPub_money((Integer.parseInt(customer_book_sub.getPub_need_num())*Double.parseDouble(customer_book_sub.getDiscount())*Double.parseDouble(customer_book_sub.getPub_price()))+"");
		//如果已支付，就会将支付状态改为0
		if(add>0)customer_book_sub.setIf_paied("0");
		dao.doUpdate(customer_book_sub);
		customer_book_sub = dao.doQueryById(id_1);
		//添加进理由表
		Change_reasonDAO cdao = new Change_reasonDAO();
		Change_reason change_reason = new Change_reason("0",customer_book_sub.getCustomer_id(),order_id,change_type,reason_type,reason);
		cdao.doCreate(change_reason);
		//更改报刊的发行量和剩余量
		Pub_messageDAO pdao = new Pub_messageDAO();
		Pub_message pub_message = pdao.doQueryById(id_2);
		pub_message.setSell_num((Integer.parseInt(pub_message.getSell_num())+add)+"");
		pub_message.setRest_num((Integer.parseInt(pub_message.getRest_num())-add)+"");
		pdao.doUpdate(pub_message);
		//将user存储到request中 
		req.setAttribute("customer_book_sub", customer_book_sub);
		//转发到查询结果页面
		req.getRequestDispatcher("/order_detail.jsp").forward(req, resp);
	}
	
	//转到退订页面
	protected void doToTuiDing(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台提交的id
		String id = req.getParameter("id");
		int id_1 = 0;
		if(!"".equals(id)){
			id_1 = Integer.parseInt(id);
		}
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		Customer_book_sub customer_book_sub = dao.doQueryById(id_1);
		
		Reason_typeDAO rdao=new Reason_typeDAO();
		List reason_list = rdao.doQuery();
		req.setAttribute("reason_list", reason_list);
		
		//将user存储到request中 
		req.setAttribute("customer_book_sub", customer_book_sub);
		
		//转发到修改页面
		req.getRequestDispatcher("/tuiding.jsp").forward(req, resp);		
	}
	
	//订单修改功能
	protected void doTuiDing(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//先获取从前台提交的所有数据
		String order_id = req.getParameter("order_id");
		String customer_id = req.getParameter("customer_id");
		String pub_id = req.getParameter("pub_id");
		String pub_need_num = req.getParameter("pub_need_num");
		
		String change_type = "退订";
		String reason_type = req.getParameter("reason_type");
		String reason = req.getParameter("reason");
		
		int id_1 = 0;
		int id_2 = 0;
		int add = 0;
		if(!"".equals(order_id)){
			id_1 = Integer.parseInt(order_id);
		}
		if(!"".equals(pub_id)){
			id_2 = Integer.parseInt(pub_id);
		}
		if(!"".equals(pub_need_num)){
			add = Integer.parseInt(pub_need_num);
		}
		
		//调用dao的方法
		Customer_book_subDAO dao = new Customer_book_subDAO();
		
		dao.doDelete(id_1);
		
		Change_reasonDAO cdao = new Change_reasonDAO();
		Change_reason change_reason = new Change_reason("0",customer_id,order_id,change_type,reason_type,reason);
		cdao.doCreate(change_reason);

		//更改报刊的发行量和剩余量
		Pub_messageDAO pdao = new Pub_messageDAO();
		Pub_message pub_message = pdao.doQueryById(id_2);
		pub_message.setSell_num((Integer.parseInt(pub_message.getSell_num())-add)+"");
		pub_message.setRest_num((Integer.parseInt(pub_message.getRest_num())+add)+"");
		pdao.doUpdate(pub_message);
		
		//转发到查询结果页面
		req.getRequestDispatcher("/xuding.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
