package hit.poms.shoufeizhongxin.dao;

import hit.poms.shoufeizhongxin.entity.ChooseDeliver;
import hit.poms.shoufeizhongxin.entity.OrderPayInfor;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class OrderPayDAO {
	public static void main(String[] args) {
		OrderPayDAO t = new OrderPayDAO();
	}
	/**
	 * 选择投递员
	 */
	public List chooseDeliver(){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from emp_message natural join sub_destination");
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				ChooseDeliver temp = new ChooseDeliver();
				temp.setEmp_id(rs.getString("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setDestination(rs.getString("destination"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		//if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
	}
	/**
	 * 在投递前查看是否有为订单分配邮递员
	 */
	public boolean queryDeliver(OrderPayInfor orderpayinfo){		
		boolean flag =false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			//2、获取数据库连接对象 
			conn = DBManager.getConnection();
			//3、创建语句对象
			String sql = "select emp_id from emp_sell_pub where order_id=?";
			st = conn.prepareStatement(sql);
			System.out.println("queryDeliver sql="+sql);
			//st.setString(1, orderpayinfo.getOrder_id());
			rs = st.executeQuery();
			if(rs.next()){
				if(rs.getString("emp_id")!=null) flag = true ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return flag;
	}
	/**
	 * 每次确认订单投递时，修改库存表
	 * @param orderpayinfo
	 */
	public void doUpdatePub(OrderPayInfor orderpayinfo){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String emp_id = null;
		String pub_id = null;
		String pub_need_num=null;
		String pub_money=null;
		String end_time=null;
		String customer_id = null;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			String sql3 = "select * from customer_book_sub where order_id=? ";
			st = conn.prepareStatement(sql3);
			
			st.setString(1, orderpayinfo.getOrder_id());
			
			System.out.println("sql3="+sql3);
			rs = st.executeQuery();
			while(rs.next()){
				pub_need_num = rs.getString("pub_need_num");
				end_time = rs.getString("end_time").substring(0,10);
				customer_id = rs.getString("customer_id");
				pub_id = rs.getString("pub_id");
				pub_money = rs.getString("pub_money");
				System.out.println(emp_id);
				System.out.println(pub_id);	
			}
			
			
			
			String sql = "update pub_message set sell_num =? where pub_id=?";		//	System.out.println(sql);
			st = conn.prepareStatement(sql);			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, pub_need_num);	
			st.setString(2, pub_id);	
			int i = st.executeUpdate();
		
			//5、判断修改是否成功
						
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 修改是否投递,在订单表中修改最终投递信息
	 * @param user
	 */
	public void doUpdateDeliver(OrderPayInfor orderpayinfo,String emp_id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String pub_id = null;
		String pub_need_num=null;
		String pub_money=null;
		String end_time=null;
		String customer_id = null;
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
		String sys_time = dateFormat.format( now );
		System.out.println("sys_time="+sys_time);
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//从customer_book_sub表读一下数据存入EMP_SELL_PUB表中
			String sql3 = "select PUB_NEED_NUM,end_time,customer_id,pub_money,pub_id  from  customer_book_sub where order_id=? ";
			st = conn.prepareStatement(sql3);
			
			st.setString(1, orderpayinfo.getOrder_id());
			
			System.out.println("sql3="+sql3);
			rs = st.executeQuery();
			while(rs.next()){
				pub_id = rs.getString("pub_id");
				pub_need_num = rs.getString("pub_need_num");
				end_time = rs.getString("end_time").substring(0,10);
				customer_id = rs.getString("customer_id");
				pub_money = rs.getString("pub_money");
				System.out.println(emp_id);
				System.out.println(pub_id);	
			}
					
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
				
			//插入表中
			String sql2 = "insert into EMP_SELL_PUB (emp_id,pub_id,emp_order,emp_delivery,fin_time,customer_id,get_money)values(?,?,?,?,to_date(?, 'yyyy-mm-dd'),?,?)";
			st = conn.prepareStatement(sql2);
			st.setString(1, emp_id);
			st.setString(2,pub_id);
			st.setString(3,pub_need_num);
			st.setString(4,"1");
			
			st.setString(5,sys_time);
			st.setString(6,customer_id);
			st.setString(7,pub_money);
			
			int i = st.executeUpdate();
			//5、判断修改是否成功
			if(i > 0){
				System.out.println("---update ok.");
			}	
			String sql4 = "update customer_book_sub set if_toudi =? where end_time<=to_date(?, 'yyyy-mm-dd') and order_id=?";
			st = conn.prepareStatement(sql4);
			st.setString(1,"1");
			st.setString(2, sys_time);
			st.setString(3, orderpayinfo.getOrder_id());
			
			 st.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 修改是否付款
	 * @param user
	 */
	public void doUpdatePay(OrderPayInfor orderpayinfo){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "update customer_book_sub set if_paied=? where order_id=?";
			System.out.println(sql);
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, orderpayinfo.getIf_paied());
			
			
			st.setString(2, orderpayinfo.getOrder_id());
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
	 * 显示所有订单
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doOrderQueryAll(OrderPayInfor orderpayinfo){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from customer_book_sub natural join cus_message where pub_id in(select pub_id from pub_message)");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				OrderPayInfor temp = new OrderPayInfor();
				System.out.println(rs.getString("customer_id"));
				temp.setCustomer_id(rs.getString("customer_id"));                   
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setOrder_id(rs.getString("order_id"));
				temp.setStart_time(rs.getString("start_time").substring(0,10)); 
				temp.setIf_paied(rs.getString("if_paied")); 
				temp.setIf_deliver(rs.getString("if_toudi")); 
				//temp.setIf_deliver(rs.getString("if_toudi"));
				temp.setPay_money(rs.getString("pub_money"));
				//temp.setSummary_date(summary_date);
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
	/**
	 * 查询未投递订单
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doOrderNoDeliver(OrderPayInfor orderpayinfo){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from customer_book_sub natural join cus_message  where if_toudi='0'");
			
			sql.append(" and pub_id in(select pub_id from pub_message)");	
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				OrderPayInfor temp = new OrderPayInfor();
				System.out.println(rs.getString("customer_id"));
				temp.setCustomer_id(rs.getString("customer_id"));                   
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setOrder_id(rs.getString("order_id"));
				temp.setStart_time(rs.getString("start_time").substring(0,10)); 
				temp.setIf_paied(rs.getString("if_paied")); 
				temp.setIf_deliver(rs.getString("if_toudi")); 
				temp.setIf_deliver(rs.getString("if_toudi"));
				temp.setPay_money(rs.getString("pub_money"));
				//temp.setSummary_date(summary_date);
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
	
	/**
	 * 订单号查询
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doQueryByorderID(OrderPayInfor orderpayinfo){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from customer_book_sub natural join cus_message where 1=1");
			if(orderpayinfo.getOrder_id() != null && !"".equals(orderpayinfo.getOrder_id())){
				sql.append(" and order_id like '%")
					.append(orderpayinfo.getOrder_id())
					.append("%'");
			}
			sql.append(" and pub_id in(select pub_id from pub_message)");
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				OrderPayInfor temp = new OrderPayInfor();
				System.out.println(rs.getString("customer_id"));
				temp.setCustomer_id(rs.getString("customer_id"));                   
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setOrder_id(rs.getString("order_id"));
				temp.setStart_time(rs.getString("start_time").substring(0,10));
				temp.setEnd_time(rs.getString("end_time").substring(0,10));
				temp.setIf_paied(rs.getString("if_paied")); 
				temp.setIf_deliver(rs.getString("if_toudi")); 
				//temp.setIf_deliver(rs.getString("if_toudi"));
				temp.setPay_money(rs.getString("pub_money"));
				//temp.setDeliver_time(rs.getString("deliver_time").substring(0,10)); 
				//temp.setSummary_date(summary_date);
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
		
}
