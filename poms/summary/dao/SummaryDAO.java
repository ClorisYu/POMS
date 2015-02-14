package hit.poms.summary.dao;

import hit.poms.summary.entity.SummaryInfor;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SummaryDAO {
	public static void main(String[] args) {
		SummaryDAO t = new SummaryDAO();
	}
	/**
	 * 修改投递数
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public void doSummaryUpdate(SummaryInfor suminfo){
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
			StringBuffer sql = new StringBuffer("update all_message set real_num=? where pub_id=? and substation_id=? and summary_date = to_date(?,'yyyy-mm-dd')");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setLong(1, suminfo.getReal_num());
			st.setLong(2, suminfo.getPub_id());
			st.setLong(3, suminfo.getSubstation_id());
			st.setString(4, suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	
	/**
	 * 每日报刊汇总
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doSummary(SummaryInfor suminfo){
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
//			StringBuffer sql = new StringBuffer("select sum(send_num),pub_id from ((sub_message natural join pub_message) natural join summary_infor)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			
			StringBuffer sql = new StringBuffer("select new_order_num,new_real_num,pub_id,pub_name from(select sum(order_num)as new_order_num ,sum(real_num)as new_real_num,pub_id from all_message where summary_date = to_date(?,'yyyy-mm-dd') group by pub_id)natural join pub_message  ");
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				SummaryInfor temp = new SummaryInfor();
				System.out.println(rs.getLong("pub_id"));
				temp.setPub_id(rs.getLong("pub_id"));                   
				temp.setPub_name(rs.getString("pub_name"));
//				temp.setSubstation_id(rs.getLong("substation_id"));
//				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setOrder_num(rs.getLong("new_order_num"));
				temp.setReal_num(rs.getLong("new_real_num"));
				temp.setSummary_date(suminfo.getSummary_date());
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
	 * 下数单汇总钱需要判断是否存在汇总表中
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public boolean doQuery(SummaryInfor suminfo,String summary_date){
		//1、声明数据库相关对象
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sell_num as order_num,substation_id,substation_name ,pub_id,pub_name from ((sub_message natural join pub_message) natural join sub_sell_pub)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			StringBuffer sql = new StringBuffer("select * from all_message  where summary_date = to_date('"+summary_date+"','yyyy-mm-dd')");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，如果有数据，这将flag标志为true
			if(rs.next()){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return flag ; //如果list中没有元素，那么返回null。
		
	}
	/**
	 * 下数单汇总从汇总表中取
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doOrderSummaryHuizong(SummaryInfor suminfo){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sell_num as order_num,substation_id,substation_name ,pub_id,pub_name from ((sub_message natural join pub_message) natural join sub_sell_pub)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			StringBuffer sql = new StringBuffer("select * from ((sub_message natural join all_message) natural join pub_message)  where summary_date=to_date(?,'yyyy-mm-dd') order by substation_id");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, suminfo.getSummary_date());
			System.out.println(suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				SummaryInfor temp = new SummaryInfor();
				System.out.println(rs.getLong("pub_id"));
				temp.setPub_id(rs.getLong("pub_id"));                   
				temp.setPub_name(rs.getString("pub_name"));
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setOrder_num(rs.getLong("order_num"));
				temp.setReal_num(rs.getLong("real_num"));
				temp.setSummary_date(rs.getString("summary_date").substring(0,10));
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
				//将找到的结果存到汇总表中
				StringBuffer sql1 = new StringBuffer("");
				
				System.out.println("---sql=" + sql);
				st = conn.prepareStatement(sql.toString());
				
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
	 * 下数单汇总，订单表中取
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doOrderSummary(SummaryInfor suminfo){
		//1、声明数据库相关对象
		Connection conn = null;
		
		PreparedStatement st = null;
		PreparedStatement st1 = null;
	
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sell_num as order_num,substation_id,substation_name ,pub_id,pub_name from ((sub_message natural join pub_message) natural join sub_sell_pub)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			StringBuffer sql = new StringBuffer("select order_num,pub_id,pub_name,substation_id,substation_name from((select sum(pub_need_num)as order_num,pub_id,substation_id from  customer_book_sub  where start_time=to_date(?,'yyyy-mm-dd') group by pub_id,substation_id) natural join sub_message) natural join pub_message");
//			select sum(pub_need_num)as order_num,pub_id,substation_id from ((sub_message natural join pub_message) natural join customer_book_sub)  where start_time=to_date('2014-7-15','yyyy-mm-dd') group by pub_id,substation_id

				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				SummaryInfor temp = new SummaryInfor();
				System.out.println(rs.getLong("pub_id"));
				temp.setPub_id(rs.getLong("pub_id"));                   
				temp.setPub_name(rs.getString("pub_name"));
//				temp.setPub_name(rs.getString("pub_name"));
//				Long pub_id = rs.getLong("pub_id");
//				Long sub_id = rs.getLong("substation_id");
//				StringBuffer sql2 = new StringBuffer("select pub_name from pub_message where pub_id=? ");
//				StringBuffer sql3 = new StringBuffer("select substation_name from sub_message where substation_id_id=? ");
				
				
				
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setOrder_num(rs.getLong("order_num"));
				temp.setReal_num(rs.getLong("order_num"));
				temp.setSummary_date(suminfo.getSummary_date());
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
				
				
				//将找到的结果存到汇总表中
				StringBuffer sql1 = new StringBuffer("insert into all_message(substation_id,pub_id,order_num,real_num,summary_date) values(?,?,?,?,to_date(?, 'yyyy-mm-dd'))");
				
				System.out.println("---sql=" + sql1);
				st1 = conn.prepareStatement(sql1.toString());
				st1.setLong(1, rs.getLong("substation_id"));
				st1.setLong(2, rs.getLong("pub_id"));
				st1.setLong(3, rs.getLong("order_num"));
				st1.setLong(4, rs.getLong("order_num"));
				st1.setString(5, suminfo.getSummary_date());
				st1.executeUpdate();
				System.out.println(suminfo.getSummary_date());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源		
			
			//DBManager.closeAll(conn, st1, rs1);
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
	}	
}
