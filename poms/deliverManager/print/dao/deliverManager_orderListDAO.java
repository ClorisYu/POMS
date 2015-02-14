package hit.poms.deliverManager.print.dao;

import hit.poms.deliverManager.print.entity.OrderList;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class deliverManager_orderListDAO {
	
	
	public List doQuery(Long id){
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
			StringBuffer sql = new StringBuffer("select cb.order_id order_id,cb.if_paied if_paied,cb.pub_id pub_id,p.pub_name pub_name,cb.customer_id customer_id, ")
								.append("c.customer_name customer_name,cb.pub_money pub_money,cb.pub_need_num pub_need_num,c.customer_address customer_address")
			                      .append(" from customer_book_sub cb,pub_message p,cus_message c")
			                      .append(" where cb.pub_id=p.pub_id and cb.customer_id=c.customer_id and cb.if_paied='0' ");
			if(id != null){
				sql.append(" and cb.order_id="+id);
			}                     
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				OrderList temp = new OrderList();
				temp.setOrder_id(rs.getLong("order_id"));
				temp.setIf_paied(rs.getString("if_paied"));
				temp.setPub_id(rs.getLong("pub_id"));
				temp.setPub_name(rs.getString("pub_name"));
				temp.setCustomer_id(rs.getLong("customer_id"));
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setPub_money(rs.getDouble("pub_money"));
				temp.setPub_need_num(rs.getString("pub_need_num"));
				temp.setCustomer_address(rs.getString("customer_address"));
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
