package hit.poms.deliverManager.orderNum.dao;

import hit.poms.deliverManager.desManager.entity.DesEmp;
import hit.poms.deliverManager.orderNum.entity.PrintSend;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliverManager_orderNumDAO {
	
	
	public List doQuery(PrintSend ps){
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
			StringBuffer sql = new StringBuffer("select * from print_send_message where sub_id=? ");
			if(!"".equals(ps.getSend_time())){
				sql.append(" and send_time=to_date('"+ps.getSend_time()+"', 'yyyy-mm-dd')");
			}
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			st.setLong(1, ps.getSub_id());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				PrintSend temp = new PrintSend();
				temp.setPub_id(rs.getLong("pub_id"));
				temp.setPub_num(rs.getLong("pub_num"));
				temp.setPrint_id(rs.getLong("print_id"));
				temp.setTransport_company_name(rs.getString("transport_company_name"));
				temp.setSub_id(rs.getLong("sub_id"));
				temp.setSend_time(rs.getString("send_time").substring(0, 10));
				
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
	
	public Long doQuerySum(PrintSend ps){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Long sum=0L;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select sum(pub_num) summary from print_send_message where sub_id=? ");
			if(!"".equals(ps.getSend_time())){
				sql.append(" and send_time=to_date('"+ps.getSend_time()+"', 'yyyy-mm-dd')");
			}
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			st.setLong(1, ps.getSub_id());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				sum=rs.getLong("summary");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return sum>0 ? sum : 0L; //如果list中没有元素，那么返回null。
		
	}
}
