package hit.poms.bigClientManager.servlet;

import hit.poms.bigClientManager.dao.BigClientManager_PayServletDAO;
import hit.poms.bigClientManager.entity.BigClient;
import hit.poms.deliverManager.desManager.dao.DeliverManager_desManagerDAO;
import hit.poms.deliverManager.desManager.entity.DesEmp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BigClientManager_PayServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String arg=req.getParameter("arg");
		if("query".equals(arg)){
			doQuery(req, resp);
		}else if("toupdate".equals(arg)){
			doToUpdate(req, resp);
		}else if("update".equals(arg)){
			doUpdate(req, resp);
		}else if("allquery".equals(arg)){
			doAllQuery(req, resp);
		}else if("todetail".equals(arg)){
			doToDetail(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void doToDetail(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String order_id=req.getParameter("order_id");
		Long order_id2=Long.parseLong(order_id);
		
		BigClient bc=new BigClient();
		bc.setOrder_id(order_id2);
		
		
		BigClientManager_PayServletDAO dao=new BigClientManager_PayServletDAO();
		List list = dao.doDetailQuery(bc);
		
		// 将list存储到request中
		req.setAttribute("list", list);

		//
		req.getRequestDispatcher("/customer_detail.jsp").forward(req, resp);
	}
	
	protected void doAllQuery(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String customer_id=req.getParameter("customer_id");
		String order_id=req.getParameter("order_id");
		Long customer_id2=Long.parseLong(customer_id);
		
		BigClient bc=new BigClient();
		bc.setCustomer_id(customer_id2);
		if(!"".equals(order_id)){
			Long order_id2=Long.parseLong(order_id);
			bc.setOrder_id(order_id2);
		}
		
		BigClientManager_PayServletDAO dao=new BigClientManager_PayServletDAO();
		List list = dao.doAllQuery(bc);
		
		// 将list存储到request中
		req.setAttribute("list", list);

		//
		req.getRequestDispatcher("/customer_search.jsp").forward(req, resp);
	}
	
	protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String order_id=req.getParameter("order_id");
		String pay_in_money=req.getParameter("pay_in_money");//Double
		String paied_money=req.getParameter("paied_money");//Double
		String customer_id=req.getParameter("customer_id"); 
		String order_money=req.getParameter("order_money");//Double
		String left_money=req.getParameter("left_money");
		Double pay_in_money2=Double.parseDouble(pay_in_money);
		Double paied_money2=Double.parseDouble(paied_money);
		Double left_money2=Double.parseDouble(left_money);
		Long order_id2=Long.parseLong(order_id);
		Long customer_id2=Long.parseLong(customer_id);
		Double order_money2=Double.parseDouble(order_money);
		
		//判断支付金额是否大于应付金额
		if(pay_in_money2<left_money2){
			//只修改paied_money
			Double new_paied_money=paied_money2+pay_in_money2;
			
			// 调用dao的方法
			BigClientManager_PayServletDAO dao=new BigClientManager_PayServletDAO();
			dao.doUpdatePay(order_id2,new_paied_money);
			
			// 转发到查询结果页面
			req.getRequestDispatcher("/bigClientManager_PayServlet?arg=query&customer_id="+customer_id+"&order_id="+order_id).forward(req, resp);
		}else{
			//变成已支付
			// 调用dao的方法
			BigClientManager_PayServletDAO dao=new BigClientManager_PayServletDAO();
			dao.doUpdateIfPay(order_id2,order_money2);
			
			// 转发到查询结果页面
			req.getRequestDispatcher("/bigClientManager_PayServlet?arg=query&customer_id="+customer_id+"&order_id="+order_id).forward(req, resp);
		}
	}
	
	protected void doToUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String customer_name=req.getParameter("customer_name");
		String order_id=req.getParameter("order_id");
		String paied_money=req.getParameter("paied_money");
		String pub_money=req.getParameter("pub_money");
		String pub_need_num=req.getParameter("pub_need_num");
		String customer_id=req.getParameter("customer_id");
		Long order_id2=Long.parseLong(order_id);
		Double paied_money2=Double.parseDouble(paied_money);
		Long pub_need_num2=Long.parseLong(pub_need_num);
		Long customer_id2=Long.parseLong(customer_id);
		Double pub_money2=Double.parseDouble(pub_money);
		
		// 将这些数据封装到user对象中
		BigClient bc=new BigClient();
		bc.setCustomer_name(customer_name);
		bc.setOrder_id(order_id2);
		bc.setPaied_money(paied_money2);
		bc.setPub_money(pub_money2);
		bc.setPub_need_num(pub_need_num2);
		bc.setCustomer_id(customer_id2);

		//System.out.println("get params");
		// 将user存储到request中
		req.setAttribute("bc", bc);

		// 转发到修改页面
		req.getRequestDispatcher("/customer_account.jsp").forward(req, resp);
	}
	
	protected void doQuery(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String customer_id=req.getParameter("customer_id");
		String order_id=req.getParameter("order_id");
		Long customer_id2=Long.parseLong(customer_id);
		
		BigClient bc=new BigClient();
		bc.setCustomer_id(customer_id2);
		if(!"".equals(order_id)){
			Long order_id2=Long.parseLong(order_id);
			bc.setOrder_id(order_id2);
		}
		
		BigClientManager_PayServletDAO dao=new BigClientManager_PayServletDAO();
		List list = dao.doQuery(bc);
		
		// 将list存储到request中
		req.setAttribute("list", list);

		//
		req.getRequestDispatcher("/customer_pay.jsp").forward(req, resp);
	}
}
