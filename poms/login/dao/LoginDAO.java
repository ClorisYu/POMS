package hit.poms.login.dao;

import hit.poms.dao.entity.Employee;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class LoginDAO {

	public Employee login(Long id, String password) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Employee temp = null;
		try{
			conn = DBManager.getConnection();
			String sql = "select * from emp_message where emp_id=? and emp_password=?";
			
			st = conn.prepareStatement(sql);
			st.setLong(1, id);
			st.setString(2, password);
			rs = st.executeQuery();
			if(rs.next()){
				System.out.print("adasdad");
				 temp= new Employee();
				temp.setEmp_id(rs.getLong("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setEmp_gender(rs.getString("emp_gender"));
				temp.setEmp_dept_id(rs.getLong("emp_dept_id"));
				temp.setEmp_post(rs.getString("emp_post"));
				temp.setEmp_salary(rs.getDouble("emp_salary"));
				temp.setEmp_bonus(rs.getDouble("emp_bonus"));
				temp.setEmp_password(rs.getString("emp_password"));
				temp.setEmp_Idnum(rs.getString("emp_Idnum"));
				temp.setEmp_order(rs.getLong("emp_order"));
				temp.setEmp_delivery(rs.getLong("emp_delivery"));
				temp.setEmp_age(rs.getLong("emp_age"));
				temp.setEmp_tel(rs.getString("emp_tel"));
				temp.setIf_online(rs.getString("if_online"));
				//System.out.println("--------------in loginDAO------"+temp);//调试
				//return temp;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	///	DBManager.closeAll(conn, st, rs);
		}
		
		if(temp!=null){
		System.out.print("adasdad");
		try {
			String sql2="update emp_message set if_online='1' where emp_id=?";
			st = conn.prepareStatement(sql2);
			st.setLong(1, temp.getEmp_id());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return temp==null?null:temp;
	}
	public void logout(Long id) {
		Connection conn = null;
		PreparedStatement st = null;
		//ResultSet rs = null;
			conn = DBManager.getConnection();
			String sql = "update  emp_message set if_online='0' where emp_id=?";
			try {
				st = conn.prepareStatement(sql);
				st.setLong(1, id);
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
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

			String sql = "delete from emp_message where emp_id=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值

			st.setLong(1, id);
			int i = st.executeUpdate();		
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
		//	DBManager.closeAll(conn, st, rs);
		}
		
	}

	/**
	 * 修改
	 * @param user
	 */
	public void doUpdate(Employee emp){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			 
			//3、创建语句对象

			String sql = "update emp_message emp_id=?,emp_name=?,emp_gender=?,"+
					"emp_dept_id=?,emp_post=?,emp_salary=?,emp_bonus=?,emp_password=?,"+
         "emp_Idnum=?,emp_order=?,emp_delivery=?,emp_age=?,emp_tel=?,if_online=?";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值

			st.setLong(1,emp.getEmp_id());
			st.setString(2,emp.getEmp_name());
			st.setString(3,emp.getEmp_gender());
			st.setLong(4,emp.getEmp_dept_id());
			st.setString(5,emp.getEmp_post());
			st.setDouble(6,emp.getEmp_salary());
			st.setDouble(7,emp.getEmp_bonus());
			st.setString(8,emp.getEmp_password());
			st.setString(9,emp.getEmp_Idnum());
			st.setLong(10,emp.getEmp_order());
			st.setLong(11,emp.getEmp_delivery());
			st.setLong(12,emp.getEmp_age());
			st.setString(13,emp.getEmp_tel());
			st.setString(14,emp.getIf_online());
			int i = st.executeUpdate();		
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			//DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 查询
	 * @param user
	 * @return
	 */
	public List doQuery(Employee emp){
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

			StringBuffer sql = new StringBuffer("select * from emp_message where 1=1 ");
			if(emp.getEmp_name() != null && !"".equals(emp.getEmp_name())){
				sql.append(" and emp_name like '%")
					.append(emp.getEmp_name())
					.append("%'");
			}
			if(emp.getEmp_id() != null && !"".equals(emp.getEmp_id())){
				sql.append(" and emp_id like '%")
					.append(emp.getEmp_id())
					.append("%'");
			}
			if(emp.getIf_online() != null && !"".equals(emp.getIf_online() )){
				sql.append(" and if_online = '")
					.append(emp.getIf_online() )
					.append("'");
			}
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值

			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中

			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中

				Employee temp = new Employee();
				temp.setEmp_id(rs.getLong("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setEmp_gender(rs.getString("emp_gender"));
				temp.setEmp_dept_id(rs.getLong("emp_dept_id"));
				temp.setEmp_post(rs.getString("emp_post"));
				temp.setEmp_salary(rs.getDouble("emp_salary"));
				temp.setEmp_bonus(rs.getDouble("emp_bonus"));
				temp.setEmp_password(rs.getString("emp_password"));
				temp.setEmp_Idnum(rs.getString("emp_Idnum"));
				temp.setEmp_order(rs.getLong("emp_order"));
				temp.setEmp_delivery(rs.getLong("emp_delivery"));
				temp.setEmp_age(rs.getLong("emp_age"));
				temp.setEmp_tel(rs.getString("emp_tel"));
				temp.setIf_online(rs.getString("if_online"));
				
				//把对象加入到list集合里

				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			//DBManager.closeAll(conn, st, rs);
		}
		
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。

		
	}
	public Employee doQueryById(Long id){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Employee temp = null;
		System.out.println("-----in byid---------");
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象

			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成

			
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值

			StringBuffer sql = new StringBuffer("select * from emp_message where 1=1 ");
				sql.append("and emp_id like '%")
					.append(id)
					.append("%'");
			System.out.println(sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值

			rs = st.executeQuery();
			
			
			while(rs.next()){
				temp = new Employee();
				temp.setEmp_id(rs.getLong("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setEmp_gender(rs.getString("emp_gender"));
				temp.setEmp_dept_id(rs.getLong("emp_dept_id"));
				temp.setEmp_post(rs.getString("emp_post"));
				temp.setEmp_salary(rs.getDouble("emp_salary"));
				temp.setEmp_bonus(rs.getDouble("emp_bonus"));
				temp.setEmp_password(rs.getString("emp_password"));
				temp.setEmp_Idnum(rs.getString("emp_Idnum"));
				temp.setEmp_order(rs.getLong("emp_order"));
				temp.setEmp_delivery(rs.getLong("emp_delivery"));
				temp.setEmp_age(rs.getLong("emp_age"));
				temp.setEmp_tel(rs.getString("emp_tel"));
				temp.setIf_online(rs.getString("if_online"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
		//	DBManager.closeAll(conn, st, rs);
		}
//	System.out.println("-----in byid---------"+temp.getEmp_post());
		return temp; 
		
	}
	/**
	 * 增加
	 * @param user
	 */
	public void doAdd(Employee emp){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//3、创建语句对象

			String sql = "insert into emp_message(emp_id,emp_name,emp_gender,"+
					"emp_dept_id,emp_post,emp_salary,emp_bonus,emp_password,"+
         "emp_Idnum,emp_order,emp_delivery,emp_age,emp_tel,if_online)"+
			"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值

			st.setLong(1,emp.getEmp_id());
			st.setString(2,emp.getEmp_name());
			st.setString(3,emp.getEmp_gender());
			st.setLong(4,emp.getEmp_dept_id());
			st.setString(5,emp.getEmp_post());
			st.setDouble(6,emp.getEmp_salary());
			st.setDouble(7,emp.getEmp_bonus());
			st.setString(8,emp.getEmp_password());
			st.setString(9,emp.getEmp_Idnum());
			st.setLong(10,emp.getEmp_order());
			st.setLong(11,emp.getEmp_delivery());
			st.setLong(12,emp.getEmp_age());
			st.setString(13,emp.getEmp_tel());
			st.setString(14,emp.getIf_online());
			int i = st.executeUpdate();
			
			//5、判断增加是否成功

			if(i > 0){
				System.out.println("---add ok.");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			//DBManager.closeAll(conn, st, rs);
		}
		
	}
}
