package hit.poms.search.dao;

import hit.poms.search.entity.Customer_book_sub;
import hit.poms.search.entity.Pub_message;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Pub_messageDAO {
	
	public static void main(String[] args) {
	}
	/**
	 * 通过id查询
	 * @param l
	 * @return
	 */
	public Pub_message doQueryById(long l){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Pub_message temp = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from pub_message where pub_id=? ");				
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setLong(1, l);
			rs = st.executeQuery();
			
			//5、对rs进行遍历
			while(rs.next()){
				temp = new Pub_message();
				temp.setPub_id(rs.getString("pub_id"));
				temp.setPub_name(rs.getString("pub_name"));
				temp.setPrint_num(rs.getString("print_num"));
				temp.setSell_num(rs.getString("sell_num"));
				temp.setRest_num(rs.getString("rest_num"));
				//System.out.print(rs.getString("rest_num"));
				temp.setImage(rs.getString("image"));
				temp.setPub_price(rs.getString("pub_price"));
				temp.setPub_type(rs.getString("pub_type"));
				temp.setPub_debook(rs.getString("pub_debook"));
				temp.setPub_redirect(rs.getString("pub_redirect"));
				temp.setPub_month_price(rs.getString("pub_month_price"));
				temp.setPub_year_price(rs.getString("pub_year_price"));
				temp.setPub_release_rate(rs.getString("pub_release_rate"));
				temp.setPub_time(rs.getString("pub_time").substring(0, 10));
				temp.setDelivery_unit(rs.getString("delivery_unit"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return temp;
		
	}
	
	/**
	 * 查询
	 * @param user
	 * @return
	 */
	public List doQuery(Pub_message pub_message){
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
			StringBuffer sql = new StringBuffer("select * from pub_message where 1=1 ");
			if(pub_message.getPub_id() != null && !"".equals(pub_message.getPub_id())){
				sql.append(" and pub_id = ")
					.append(pub_message.getPub_id())
					.append(" ");
			}	
			if(pub_message.getPub_name() != null && !"".equals(pub_message.getPub_name())){
				sql.append(" and pub_name like '%")
					.append(pub_message.getPub_name())
					.append("%'");
			}	
			if(pub_message.getDelivery_unit() != null && !"".equals(pub_message.getDelivery_unit())){
				sql.append(" and delivery_unit like '%")
					.append(pub_message.getDelivery_unit())
					.append("%'");
			}	
			if(pub_message.getPub_type() != null && !"".equals(pub_message.getPub_type())){
				sql.append(" and pub_type like '%")
					.append(pub_message.getPub_type())
					.append("%'");
			}	
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Pub_message temp = new Pub_message();
				temp.setPub_id(rs.getString("pub_id"));
				temp.setPub_name(rs.getString("pub_name"));
				temp.setPrint_num(rs.getString("print_num"));
				temp.setSell_num(rs.getString("sell_num"));
				temp.setRest_num(rs.getString("rest_num"));
				temp.setImage(rs.getString("image"));
				temp.setPub_price(rs.getString("pub_price"));
				temp.setPub_type(rs.getString("pub_type"));
				temp.setPub_debook(rs.getString("pub_debook"));
				temp.setPub_redirect(rs.getString("pub_redirect"));
				temp.setPub_month_price(rs.getString("pub_month_price"));
				temp.setPub_year_price(rs.getString("pub_year_price"));
				temp.setPub_release_rate(rs.getString("pub_release_rate"));
				temp.setPub_time(rs.getString("pub_time").substring(0, 10));
				temp.setDelivery_unit(rs.getString("delivery_unit"));
				
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
	 * 修改发行数，剩余量
	 * @param student
	 */
	public void doUpdate(Pub_message pub_message){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "update pub_message set sell_num=?, rest_num=? where pub_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, pub_message.getSell_num());
			st.setString(2, pub_message.getRest_num());
			st.setString(3, pub_message.getPub_id());
			
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
}
