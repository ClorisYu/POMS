package hit.poms.dsz.dao;

import hit.poms.dao.entity.Employee;
import hit.poms.dao.entity.Substation;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubDAO {
	/**
	 * 删除
	 * @param id
	 */
	public void doDelete(Long id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "delete from sub_message where substation_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setLong(1, id);
			int i = st.executeUpdate();		
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}

	/**
	 * 修改
	 * @param user
	 */
	public void doUpdate(Substation sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			 
			//3、创建语句对象
			String sql = "update sub_message emp_id=?,substation_id=?,substation_name=?,"+
					"substation_leader_id=?,substation_provice=?,substation_city=?,substation_town=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setLong(1,sub.getSubstation_id());
			st.setString(2,sub.getSubstation_name());
			st.setLong(3,sub.getSubstation_leader_id());
			st.setString(4,sub.getSubstation_provice());
			st.setString(5,sub.getSubstation_city());
			st.setString(6,sub.getSubstation_town());
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
	public List doQuery(Substation sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		//System.out.println("aaaaaaaaaaaaaaaaaaaa");
		//System.out.println("----2-----"+emp.getIf_online());
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from sub_message where 1=1 ");
			if(sub.getSubstation_name() != null && !"".equals(sub.getSubstation_name())){
				sql.append(" and substation_name like '%")
					.append(sub.getSubstation_name())
					.append("%'");
			}
			if(sub.getSubstation_id() != null && !"".equals(sub.getSubstation_id())){
				sql.append("and substation_id like '%")
					.append(sub.getSubstation_id() )
					.append("%'");
			}
			//System.out.println(sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Substation temp = new Substation();
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setSubstation_leader_id(rs.getLong("substation_leader_id"));
				temp.setSubstation_provice(rs.getString("substation_provice"));
				temp.setSubstation_city(rs.getString("substation_city"));
				temp.setSubstation_town(rs.getString("substation_town"));
				
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
	 * 增加
	 * @param user
	 */
	public void doAdd(Substation sub){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象
			String sql = "insert into sub_message(substation_id,substation_name,"+
					"substation_leader_id,substation_provice,substation_city,substation_town)"+
			"values(?,?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setLong(1,sub.getSubstation_id());
			st.setString(2,sub.getSubstation_name());
			st.setLong(3,sub.getSubstation_leader_id());
			st.setString(4,sub.getSubstation_provice());
			st.setString(5,sub.getSubstation_city());
			st.setString(6,sub.getSubstation_town());
			int i = st.executeUpdate();
			
			//5、判断增加是否成功
			if(i > 0){
				System.out.println("---add ok.");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}

}
