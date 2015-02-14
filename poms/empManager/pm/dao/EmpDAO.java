package hit.poms.empManager.pm.dao;

import hit.poms.empManager.pm.entity.Employee;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	
	/**
	 * 添加员工到数据库
	 * @param emp
	 */
	public String doAdd(Employee emp) {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String sql_1 = "select * from emp_message where emp_id = "+emp.getEmp_id();
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			
			if(rs.next()){
				return "员工编号 "+emp.getEmp_id()+" 已经存在，请重新输入！";
			}else{
				String sql = "insert into emp_message(emp_id,emp_name,emp_gender,emp_dept_id," +
						"emp_post,emp_password,emp_idnum,emp_age,emp_tel) values " +
						"( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				st = conn.prepareStatement(sql);
				st.setLong(1, emp.getEmp_id());
				st.setString(2, emp.getEmp_name());
				st.setString(3, emp.getEmp_gender());
				st.setLong(4, emp.getEmp_dept_id());
				st.setString(5, emp.getEmp_post());
				st.setLong(6, emp.getEmp_id());
				st.setString(7, emp.getEmp_idnum());
				st.setInt(8, emp.getAge());
				st.setString(9, emp.getEmp_tel());
				
				st.executeUpdate();
			}
			if(emp.getEmp_post().equals("manager")){
				return "成功添加了一位经理！";
			}else if(emp.getEmp_post().equals("minister")){
				return "成功添加了一位部长！";
			}else if(emp.getEmp_post().equals("employer")){
				return "成功添加了一位员工！";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {		
			DBManager.closeAll(conn, st, rs);
		}
		return "";
	}

	/**
	 * 到数据库查询满足输入的员工信息
	 * @param emp
	 * @return
	 */
	public List doQuery(Employee emp) {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		boolean flag = false;
		try {
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer("select * from emp_message where 1=1");
			if(emp.getEmp_name() != null && !"".equals(emp.getEmp_name())){
				sql.append(" and emp_name like '%")
					.append(emp.getEmp_name())
					.append("%'");
			}
			if(emp.getEmp_dept_id() != null && !"".equals(emp.getEmp_dept_id())&&!(""+emp.getEmp_dept_id()).subSequence(0,2).equals("10")){
				sql.append(" and emp_dept_id = '")
					.append(emp.getEmp_dept_id())
					.append("'");
			}
			Long empId = emp.getEmp_id();
			if(empId != 0 && !"".equals(empId)){
				sql.append(" and emp_id = ")
				.append(empId);
			}
			System.out.print("adasd"+emp.getEmp_dept_id());
			sql.append(" order by emp_id");
			st = conn.prepareStatement(sql.toString());
//			System.out.println("EmpDAO qurey ---sql=" + sql);//////////////////调试用
//			System.out.println("---emp_id=" + empId);//////////////////调试用
			rs = st.executeQuery();
			while(rs.next()){
				Employee temp = new Employee();
				flag = true;
				temp.setEmp_id(rs.getLong("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setEmp_gender(rs.getString("emp_gender"));
				temp.setEmp_dept_id(rs.getLong("emp_dept_id"));
				temp.setEmp_post(rs.getString("emp_post"));
				temp.setEmp_idnum(rs.getString("emp_idnum"));
				temp.setAge(rs.getInt("emp_age"));
				temp.setEmp_tel(rs.getString("emp_tel"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}

	/**
	 * 根据用户的选择的内容的id查询
	 * @param id
	 * @return
	 */
	public Employee doQueryById(Long id) {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Employee temp = new Employee();
		try {
			conn = DBManager.getConnection();
			String sql = "select * from emp_message where emp_id = "+id;
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				temp.setEmp_id(rs.getLong("emp_id"));
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setEmp_gender(rs.getString("emp_gender"));
				temp.setEmp_dept_id(rs.getLong("emp_dept_id"));
				temp.setEmp_post(rs.getString("emp_post"));
				temp.setEmp_idnum(rs.getString("emp_idnum"));
				temp.setAge(rs.getInt("emp_age"));
				temp.setEmp_tel(rs.getString("emp_tel"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{	
			DBManager.closeAll(conn, st, rs);
		}
		return temp;
	}
	
	/**
	 * 修改员工信息
	 * @param emp
	 * @return
	 */

	public void doUpdate(Employee emp) {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "update emp_message set  emp_name=?, emp_gender=?, " +
			"emp_dept_id=?, emp_post=?, emp_idnum=?, emp_age=?, emp_tel=? " +
			"where emp_id=?";
		st = conn.prepareStatement(sql);
		
		//st.setLong(1, emp.getEmp_id());
		st.setString(1,emp.getEmp_name());
		st.setString(2,emp.getEmp_gender());
		st.setLong(3, emp.getEmp_dept_id());
		st.setString(4,emp.getEmp_post());
		st.setString(5,emp.getEmp_idnum());
		st.setInt(6,emp.getAge());
		st.setString(7,emp.getEmp_tel());
		st.setLong(8,emp.getEmp_id());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
	}

	/**
	 * 删除员工
	 * @param id
	 */
	public void doDelete(Long id) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "delete from emp_message where emp_id=?";
			st = conn.prepareStatement(sql);
			st.setLong(1, id);	
			st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{	
			DBManager.closeAll(conn, st, rs);
		}

	}

	/**
	 * 修改职务
	 * @param emp
	 */
	public void SetPost(Employee emp) {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "update emp_message set emp_post=? where emp_id=?";
		st = conn.prepareStatement(sql);
		st.setString(1,emp.getEmp_post());
		st.setLong(2,emp.getEmp_id());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}	
	}
}
