package hit.poms.modifycode.dao;
import hit.poms.util.DBManager;
/*
 * 修改密码Dao层
 */

import java.sql.*;
public class ModifyCodeDao {
	public int modifyCode(String userId,String oldPassword,String newPassword){
		
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
		 conn = DBManager.getConnection();
		 String sql_1 = "select emp_password from emp_message where emp_id = ?  ";
		 st = conn.prepareStatement(sql_1);
		 st.setString(1, userId);
		 rs = st.executeQuery();
		 while(rs.next()){
			 String password = rs.getString(1);
			 if(password.equals(oldPassword)){
			 flag =true;
			 }
		 }
		 rs.close();
		 st.close();
		 if(flag){
			 
			 String sql = "update emp_message set emp_password=? where emp_id=? ";
			 st = conn.prepareStatement(sql);
			 st.setString(1, newPassword);
			 st.setString(2, userId);
			 st.executeQuery();
			 return 2;
			 
		 }
		 else{
			 return 3;
		 }
		 }catch(Exception e){
			 e.printStackTrace();
			 return 4;
			
		 }finally{
			DBManager.closeAll(conn, st, rs);
		 }
		
	}

}
