package hit.poms.search.dao;

import hit.poms.search.entity.Sub_sell_pub;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Sub_sell_pubDAO {
	
	public static void main(String[] args) {
	}
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Sub_sell_pub doQueryById(int id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Sub_sell_pub temp = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from sub_sell_pub where substation_id=? ");				
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setInt(1, id);
			rs = st.executeQuery();
			
			//5、对rs进行遍历
			while(rs.next()){
				temp = new Sub_sell_pub();
				temp.setSubstation_id(rs.getString("substation_id"));
				temp.setPub_id(rs.getString("pub_id"));
				temp.setPub_send_num(rs.getString("pub_send_num"));
				temp.setSend_time(rs.getString("send_time").substring(0, 10));
				temp.setGet_money(rs.getString("get_money"));
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
	public List doQuery(Sub_sell_pub sub_sell_pub){
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
			StringBuffer sql = new StringBuffer("select * from sub_sell_pub where 1=1 ");
			//查询的前提是客户ID的存在
			if(sub_sell_pub.getSubstation_id() != null && !"".equals(sub_sell_pub.getSubstation_id())){
				sql.append(" and substation_id =")
					.append(sub_sell_pub.getSubstation_id())
					.append(" ");
			}
			if(sub_sell_pub.getSend_time() != null && !"".equals(sub_sell_pub.getSend_time())){
				sql.append(" and to_char(send_time,'yyyy-mm') = '")
					.append(sub_sell_pub.getSend_time())
					.append("'");
			}	
	
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Sub_sell_pub temp = new Sub_sell_pub();
				temp.setSubstation_id(rs.getString("substation_id"));
				temp.setPub_id(rs.getString("pub_id"));
				temp.setPub_send_num(rs.getString("pub_send_num"));
				temp.setSend_time(rs.getString("send_time").substring(0, 10));
				temp.setGet_money(rs.getString("get_money"));
				
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
}
