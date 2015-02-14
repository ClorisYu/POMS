package hit.poms.dsz.dao;

import hit.poms.dao.entity.CompanyMessage;
import hit.poms.dao.entity.Substation;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyMessageDAO {
	/**
	 * 修改
	 * @param user
	 */
	public void doUpdate(CompanyMessage cm){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			 
			//3、创建语句对象
			String sql = "update company_message set type_1=?,type_2=?,type_3=?,"+
					"type_4=?,type_5=?,content_1=?,content_2=?,content_3=?,"+
					"content_4=?,content_5=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, cm.getType_1());
			st.setString(2, cm.getType_2());
			st.setString(3, cm.getType_3());
			st.setString(4, cm.getType_4());
			st.setString(5, cm.getType_5());
			st.setString(6, cm.getContent_1());
			st.setString(7, cm.getContent_2());
			st.setString(8, cm.getContent_3());
			st.setString(9, cm.getContent_4());
			st.setString(10, cm.getContent_5());
			int i = st.executeUpdate();		
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
	public List<CompanyMessage> doQuery(){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		CompanyMessage temp=null;
		List<CompanyMessage> list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "select * from company_message";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到对象中
				temp = new CompanyMessage();
				temp.setType_1(rs.getString("type_1"));
				temp.setType_2(rs.getString("type_2"));
				temp.setType_3(rs.getString("type_3"));
				temp.setType_4(rs.getString("type_4"));
				temp.setType_5(rs.getString("type_5"));
				temp.setContent_1(rs.getString("content_1"));
				temp.setContent_2(rs.getString("content_2"));
				temp.setContent_3(rs.getString("content_3"));
				temp.setContent_4(rs.getString("content_4"));
				temp.setContent_5(rs.getString("content_5"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		return list; //如果list中没有元素，那么返回null。
		
	}

}
