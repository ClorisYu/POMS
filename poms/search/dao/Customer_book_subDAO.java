package hit.poms.search.dao;

import hit.poms.bean.emp_sell;
import hit.poms.search.entity.Customer_book_sub;
import hit.poms.search.entity.Sub_log;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Customer_book_subDAO {
	
	public static void main(String[] args) {
	}
	/**
	 * 通过order_id查询
	 * @param id
	 * @return
	 */
	public Customer_book_sub doQueryById(int id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Customer_book_sub temp = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from customer_book_sub,pub_message,cus_message where customer_book_sub.pub_id = pub_message.pub_id and customer_book_sub.customer_id=cus_message.customer_id and order_id=? ");				
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setInt(1, id);
			rs = st.executeQuery();
			
			//5、对rs进行遍历
			while(rs.next()){
				temp = new Customer_book_sub();
				temp.setOrder_id(rs.getString("order_id"));
				temp.setIf_paied(rs.getString("if_paied"));
				temp.setPub_id(rs.getString("pub_id"));
				temp.setCustomer_id(rs.getString("customer_id"));
				temp.setSubstation_id(rs.getString("substation_id"));
				temp.setStart_time(rs.getString("start_time").substring(0, 10));
				temp.setEnd_time(rs.getString("end_time").substring(0, 10));
				temp.setPub_money(rs.getString("pub_money"));
				temp.setPub_need_num(rs.getString("pub_need_num"));
				temp.setGift_type(rs.getString("gift_type"));
				temp.setDiscount(rs.getString("discount"));
				temp.setGift_num(rs.getString("gift_num"));
				temp.setPaied_money(rs.getString("paied_money"));
				temp.setPub_price(rs.getString("pub_price"));
				temp.setPay_way(rs.getString("pay_way"));
				temp.setPub_name(rs.getString("pub_name"));
				temp.setPub_type(rs.getString("pub_type"));
				temp.setCustomer_phone(rs.getString("customer_phone"));
				temp.setCustomer_address(rs.getString("customer_address"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return temp;
		
	}
	
	/**
	 * 通过customer_id查询
	 * @param id
	 * @return
	 */
	public Customer_book_sub doQueryByCusId(int id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Customer_book_sub temp = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from cus_message where customer_id=? ");				
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setInt(1, id);
			rs = st.executeQuery();
			
			//5、对rs进行遍历
			while(rs.next()){
				temp = new Customer_book_sub();
				//temp.setCustomer_id(rs.getString("customer_id"));
				temp.setCustomer_address(rs.getString("customer_address"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return temp;
		
	}
	/*
	 * 
	 * 
	 * 
	 */
	
public void insertintopus_sub(emp_sell bean){
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	try{
		//2、获取数据库连接对象
		conn = DBManager.getConnection();
        String sql="insert into emp_sell_pub values (?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		//3、创建语句对象
//		String sql = "update customer_book_sub set if_paied=?, pub_id=?, substation_id=?," +
//				" customer_id=?, start_time=to_date(?, 'yyyy-mm-dd'), end_time=to_date(?, 'yyyy-mm-dd')," +
//				" pub_money=?, pub_need_num=?, gift_type=?, discount=?, gift_num=?, paied_money=? where order_id=?";
		st = conn.prepareStatement(sql);
		
		//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
		st.setLong(1,bean.getEmp_id());
		st.setLong(2, bean.getPub_id());
		st.setLong(3,bean.getEmp_order());
		st.setLong(4, bean.getEmp_delivery());
		st.setString(5, bean.getFinal_time());
		st.setLong(6,bean.getCustomer_id());
		st.setDouble(7, bean.getGet_money());
		int i = st.executeUpdate();
		
		//5、判断修改是否成功
		if(i > 0){
			System.out.println("---update ok.");
		}			
	}catch(Exception e){
		e.printStackTrace();
	}finally{//释放所有数据库资源			
		DBManager.closeAll(conn, st, rs);
	}
	
	
}
	/**
	 * 查询
	 * @param user
	 * @return
	 */
	public List doQuery(Customer_book_sub customer_book_sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from customer_book_sub, sub_message, pub_message, cus_message " +
					"where sub_message.substation_id=customer_book_sub.substation_id " +
					"and customer_book_sub.customer_id=cus_message.customer_id " +
					"and customer_book_sub.pub_id=pub_message.pub_id ");
			//查询的前提是客户ID的存在
			if(customer_book_sub.getCustomer_id() != null && !"".equals(customer_book_sub.getCustomer_id())){
				sql.append(" and customer_book_sub.customer_id =")
					.append(customer_book_sub.getCustomer_id())
					.append(" ");
			}
			if(customer_book_sub.getStart_time() != null && !"".equals(customer_book_sub.getStart_time())){
				sql.append(" and start_time >= to_date('")
					.append(customer_book_sub.getStart_time())
					.append("', 'yyyy-mm-dd')");
			}	
			if(customer_book_sub.getEnd_time() != null && !"".equals(customer_book_sub.getEnd_time())){
				sql.append(" and end_time <= to_date('")
					.append(customer_book_sub.getEnd_time())
					.append("', 'yyyy-mm-dd')");
			}
			if(customer_book_sub.getPub_id() != null && !"".equals(customer_book_sub.getPub_id())){
				sql.append(" and customer_book_sub.pub_id =")
					.append(customer_book_sub.getPub_id())
					.append(" ");
			}
			if(customer_book_sub.getSubstation_id() != null && !"".equals(customer_book_sub.getSubstation_id())){
				sql.append(" and sub_message.substation_id =")
					.append(customer_book_sub.getSubstation_id())
					.append(" ");
			}
			if(customer_book_sub.getSubstation_name() != null && !"".equals(customer_book_sub.getSubstation_name())){
				sql.append(" and substation_name like '%")
					.append(customer_book_sub.getSubstation_name())
					.append("%'");
			}
	
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Customer_book_sub temp = new Customer_book_sub();
				temp.setOrder_id(rs.getString("order_id"));
				temp.setIf_paied(rs.getString("if_paied"));
				temp.setPub_id(rs.getString("pub_id"));
				temp.setCustomer_id(rs.getString("customer_id"));
				temp.setSubstation_id(rs.getString("substation_id"));
				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setStart_time(rs.getString("start_time").substring(0, 10));
				temp.setEnd_time(rs.getString("end_time").substring(0, 10));
				temp.setPub_money(rs.getString("pub_money"));
				temp.setPub_need_num(rs.getString("pub_need_num"));
				temp.setGift_type(rs.getString("gift_type"));
				temp.setDiscount(rs.getString("discount"));
				temp.setGift_num(rs.getString("gift_num"));
				temp.setPaied_money(rs.getString("paied_money"));
				temp.setGift_num(rs.getString("gift_num"));
				temp.setCustomer_address(rs.getString("customer_address"));
				temp.setPub_name(rs.getString("pub_name"));
				temp.setIf_toudi(rs.getString("if_toudi"));
				temp.setPub_debook(rs.getString("pub_debook"));
				
				
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
	
	/**
	 * 构建订单
	 * @param user
	 * @return
	 */
	public Customer_book_sub doOrderQuery(Customer_book_sub customer_book_sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from cus_message where 1=1 ");
			//查询的前提是客户ID的存在
			if(customer_book_sub.getCustomer_id() != null && !"".equals(customer_book_sub.getCustomer_id())){
				sql.append(" and customer_id =")
					.append(customer_book_sub.getCustomer_id())
					.append(" ");
			}	
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			if(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中

				customer_book_sub.setCustomer_name(rs.getString("customer_name"));
				customer_book_sub.setCustomer_grade(rs.getString("customer_grade"));
				customer_book_sub.setCustomer_address(rs.getString("customer_address"));
				customer_book_sub.setCustomer_phone(rs.getString("customer_phone"));				
			}else{
				return null;
			}
			
			sql = new StringBuffer("select * from pub_message where 1=1 ");
			if(customer_book_sub.getPub_id() != null && !"".equals(customer_book_sub.getPub_id())){
				sql.append(" and pub_id =")
					.append(customer_book_sub.getPub_id())
					.append(" ");
			}
	
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				
				
				if(customer_book_sub.getCustomer_grade().equals("1"))
					customer_book_sub.setDiscount(rs.getDouble("discount1")+"");
				else if(customer_book_sub.getCustomer_grade().equals("2"))
					customer_book_sub.setDiscount(rs.getDouble("discount2")+"");
				else if(customer_book_sub.getCustomer_grade().equals("0"))
					customer_book_sub.setDiscount("1.00");	
				customer_book_sub.setPub_name(rs.getString("pub_name"));
				customer_book_sub.setPub_type(rs.getString("pub_type"));
				customer_book_sub.setPub_time(rs.getString("pub_time"));
				customer_book_sub.setPub_price(rs.getString("pub_price"));
				customer_book_sub.setPub_month_price(rs.getString("pub_month_price"));
				customer_book_sub.setPub_year_price(rs.getString("pub_year_price"));
				customer_book_sub.setPub_debook(rs.getString("pub_debook"));
				//杂项补齐
				customer_book_sub.setStart_time(formatDate.format(new Date()));
				System.out.println(formatDate.format(new Date()));
				
				//customer_book_sub.setPub_year_price(rs.getString("pub_debook"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return customer_book_sub; //如果list中没有元素，那么返回null。
		
	}
	
	/**
	 * 分站日志
	 * @param customer_book_sub
	 * @return
	 */
	public List doLogQuery(Customer_book_sub customer_book_sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select count(start_time),count(end_time),sub_message.substation_id,substation_name from customer_book_sub, sub_message where sub_message.substation_id=customer_book_sub.substation_id ");
			if(customer_book_sub.getStart_time() != null && !"".equals(customer_book_sub.getStart_time())){
				sql.append(" and start_time >= to_date('")
					.append(customer_book_sub.getStart_time())
					.append("', 'yyyy-mm-dd')");
			}
			if(customer_book_sub.getEnd_time() != null && !"".equals(customer_book_sub.getEnd_time())){
				sql.append(" and start_time <= to_date('")
					.append(customer_book_sub.getEnd_time())
					.append("', 'yyyy-mm-dd')");
			}
			if(customer_book_sub.getStart_time() != null && !"".equals(customer_book_sub.getStart_time())){
				sql.append(" and end_time >= to_date('")
					.append(customer_book_sub.getStart_time())
					.append("', 'yyyy-mm-dd')");
			}
			if(customer_book_sub.getEnd_time() != null && !"".equals(customer_book_sub.getEnd_time())){
				sql.append(" and end_time <= to_date('")
					.append(customer_book_sub.getEnd_time())
					.append("', 'yyyy-mm-dd')");
			}
			//查询的前提是分站ID的存在
			if(customer_book_sub.getSubstation_id() != null && !"".equals(customer_book_sub.getSubstation_id())){
				sql.append(" and sub_message.substation_id =")
					.append(customer_book_sub.getSubstation_id())
					.append(" ");
			}else if(customer_book_sub.getSubstation_name() != null && !"".equals(customer_book_sub.getSubstation_name())){
				sql.append(" and substation_name like '%")
					.append(customer_book_sub.getSubstation_name())
					.append("%'");
			}
			sql.append(" group by sub_message.substation_id, substation_name");
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
	
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Sub_log temp = new Sub_log();
				temp.setNew_order_num(rs.getString(1));
				temp.setEnd_order_num(rs.getString(2));
				temp.setStart_time(customer_book_sub.getStart_time());
				temp.setEnd_time(customer_book_sub.getEnd_time());
				temp.setSubstation_id(rs.getString(3));
				temp.setSubstation_name(rs.getString(4));
				
				//把对象加入到list集合里
				list.add(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
	
	/**
	 * 修改
	 * @param student
	 */
	public void doUpdate(Customer_book_sub customer_book_sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "update customer_book_sub set if_paied=?, pub_id=?, substation_id=?," +
					" customer_id=?, start_time=to_date(?, 'yyyy-mm-dd'), end_time=to_date(?, 'yyyy-mm-dd')," +
					" pub_money=?, pub_need_num=?, gift_type=?, discount=?, gift_num=?, paied_money=? where order_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, customer_book_sub.getIf_paied());
			st.setString(2, customer_book_sub.getPub_id());
			st.setString(3, customer_book_sub.getSubstation_id());
			st.setString(4, customer_book_sub.getCustomer_id());
			st.setString(5, customer_book_sub.getStart_time().substring(0, 10));
			st.setString(6, customer_book_sub.getEnd_time().substring(0, 10));
			st.setString(7, customer_book_sub.getPub_money());
			st.setString(8, customer_book_sub.getPub_need_num());
			st.setString(9, customer_book_sub.getGift_type());
			st.setString(10, customer_book_sub.getDiscount());
			st.setString(11, customer_book_sub.getGift_num());
			st.setString(12, customer_book_sub.getPaied_money());
			st.setString(13, customer_book_sub.getOrder_id());
			int i = st.executeUpdate();
			
			//5、判断修改是否成功
			if(i > 0){
				System.out.println("---update ok.");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	
	/**
	 * 增加
	 * @param student
	 */
	public String doCreate(Customer_book_sub customer_book_sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String id = "0";
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			
			String sql_1 = "select order_quen.nextval order_id from dual";
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			while(rs.next()){
				id = rs.getString("order_id");
			}
			System.out.println(id);
			//3、创建语句对象
			String sql = "insert into customer_book_sub values( ?, ?, to_date(?,'yyyy-mm-dd'), to_date(?,'yyyy-mm-dd'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, customer_book_sub.getPub_id());
			st.setString(2, customer_book_sub.getCustomer_id());
			st.setString(3, customer_book_sub.getStart_time().substring(0, 10));
			st.setString(4, customer_book_sub.getEnd_time().substring(0, 10));
			st.setString(5, customer_book_sub.getPub_money());
			st.setString(6, customer_book_sub.getPub_need_num());
			st.setString(7, customer_book_sub.getGift_type());
			st.setString(8, customer_book_sub.getGift_num());
			st.setString(9, customer_book_sub.getDiscount());
			st.setString(10, id);
			st.setString(11, customer_book_sub.getIf_paied());
			st.setString(12, customer_book_sub.getSubstation_id());
			st.setString(13, customer_book_sub.getPaied_money());
			st.setString(14, customer_book_sub.getIf_toudi());
			st.setString(15, customer_book_sub.getPay_way());
			
			int i = st.executeUpdate();
			
			//5、判断修改是否成功
			if(i > 0){
				System.out.println("---insert ok.");
			}else{
				id="";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		return id;
		
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void doDelete(int id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "delete from customer_book_sub where order_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setInt(1, id);
			int i = st.executeUpdate();
			
			//5、判断删除是否成功
			if(i > 0){
				System.out.println("---delete ok.");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
}
