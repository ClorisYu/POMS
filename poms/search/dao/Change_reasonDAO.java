package hit.poms.search.dao;

import hit.poms.search.entity.Change_reason;
import hit.poms.search.entity.Customer_book_sub;
import hit.poms.search.entity.Sub_sell_pub;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Change_reasonDAO {
	
	public static void main(String[] args) {
	}
	
	/**
	 * 增加
	 * @param student
	 */
	public String doCreate(Change_reason change_reason){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String id = "0";
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			
			String sql_1 = "select change_quen.nextval change_id from dual";
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			while(rs.next()){
				id = rs.getString("change_id");
			}
			System.out.println(id);
			//3、创建语句对象
			String sql = "insert into change_reason values( ?, ?, ?, ?, ?, ?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, id);
			st.setString(2, change_reason.getCustomer_id());	
			st.setString(3, change_reason.getOrder_id());
			st.setString(4, change_reason.getChange_type());
			st.setString(5, change_reason.getReason_type());
			st.setString(6, change_reason.getReason());
			
			
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
}
