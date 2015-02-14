package hit.poms.deliverManager.desManager.dao;

import hit.poms.deliverManager.desManager.entity.DesEmp;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliverManager_desManagerDAO {
	
	
	public DesEmp doQueryById(Long id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		DesEmp temp=null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from sub_destination where des_id=? ");
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setLong(1, id);
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				temp=new DesEmp();
				temp.setDes_id(rs.getLong("des_id"));
				temp.setDestination(rs.getString("destination"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return temp; 
		
	}
	
	public void doUpdate(DesEmp de){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "update sub_destination set destination=?" +
					" where des_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setString(1, de.getDestination());
			st.setLong(2, de.getDes_id());
			
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
	
	public void doDelete(Long id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "delete from sub_destination where des_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setLong(1, id);
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
	
	
	public List doQuery(DesEmp de){
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
			StringBuffer sql = new StringBuffer("select * from sub_destination where 1=1 ");
			if(de.getDes_id() != null ){
				sql.append(" and des_id="+de.getDes_id());
			}
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				DesEmp temp = new DesEmp();
				temp.setDes_id(rs.getLong("des_id"));
				temp.setDestination(rs.getString("destination"));
				temp.setEmp_id(rs.getLong("emp_id"));
				
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
	
	public Long doAdd(DesEmp de){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Long des_id = 0L;
		int i=0;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//４-１：使用序列生成主键值 
			
			String sql_1 = "select des_seq.nextval id from dual";
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			while(rs.next()){
				des_id = rs.getLong("id");
			}
			
			//3、创建语句对象
			String sql = "insert into sub_destination(des_id, destination)" +
					" values(?, ?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
//			st.setLong(1, user.getId());
			st.setLong(1, des_id);
			st.setString(2, de.getDestination());
			
			i = st.executeUpdate();
			
			//5、判断增加是否成功
			if(i > 0){
				System.out.println("---add ok.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		return i>0 ? des_id : 0L;
	}
}
