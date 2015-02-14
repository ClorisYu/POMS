package hit.poms.subStation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hit.poms.subStation.entity.SubStation;
import hit.poms.subStation.entity.SubStationEmp;
import hit.poms.util.*;

public class SubStationDAO {
	public static void main(String[] args) {
	}
	
	public List<SubStationEmp> queryLeader(SubStation station){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SubStationEmp> list = new ArrayList<SubStationEmp>();
		
		try{
			//调用函数建立连接
			conn = DBManager.getConnection();
			
			StringBuffer sql = new StringBuffer("select substation_leader_id, emp_name, emp_gender, emp_post, emp_tel from emp_message, sub_message");
			sql.append(" where substation_id = ")
			.append(station.getSubStationID())
			.append(" and substation_leader_id = emp_id and substation_id = emp_dept_id");
			
			st = conn.prepareStatement(sql.toString());
			rs = st.executeQuery();
			
			//对rs进行遍历，将结果封装到List中
			while(rs.next()){
				SubStationEmp leader = new SubStationEmp();
				leader.setId(rs.getString("substation_leader_id"));
				leader.setName(rs.getString("emp_name"));
				leader.setGender(rs.getString("emp_gender"));
				leader.setJob(rs.getString("emp_post"));
				leader.setTel(rs.getString("emp_tel"));
				//把对象加入到list集合里面
				list.add(leader);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}
	
	public List<SubStationEmp> queryEmp(SubStation station){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SubStationEmp> list = new ArrayList<SubStationEmp>();
		
		try{
			//调用函数建立连接
			conn = DBManager.getConnection();
			
			StringBuffer sql = new StringBuffer("select emp_id, emp_name, emp_gender, emp_post, emp_tel from emp_message, sub_message");
			sql.append(" where substation_id = ")
			.append(station.getSubStationID())
			.append(" and substation_leader_id != emp_id and substation_id = emp_dept_id and emp_belong_dept = ")
			.append(station.getFlag());
			
			st = conn.prepareStatement(sql.toString());
			rs = st.executeQuery();
			
			//对rs进行遍历，将结果封装到List中
			while(rs.next()){
				SubStationEmp emp = new SubStationEmp();
				emp.setId(rs.getString("emp_id"));
				emp.setName(rs.getString("emp_name"));
				emp.setGender(rs.getString("emp_gender"));
				emp.setJob(rs.getString("emp_post"));
				emp.setTel(rs.getString("emp_tel"));
				//把对象加入到list集合里面
				list.add(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}
	
	public List<SubStation> queryProvince(int i){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SubStation> list = new ArrayList();
		
		try{
			//调用函数建立连接
			conn = DBManager.getConnection();
			
			StringBuffer sql = new StringBuffer("select substation_provice from sub_message");
			if(i == 1){
				sql.append(" where substation_name like '%总站%'");
			}else{
				sql.append(" where substation_name like '%分站%'");
			}
			st = conn.prepareStatement(sql.toString());
			rs = st.executeQuery();
			
			//对rs进行遍历，将结果封装到List中
			while(rs.next()){
				SubStation station = new SubStation();
				station.setSubStationProvince(rs.getString("substation_provice"));
				//把对象加入到list集合里面
				list.add(station);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}

	public List<SubStation> queryCity(String targetId, String targettype) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SubStation> list = new ArrayList();
		
		try{
			//调用函数建立连接
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer();
			if(targetId.equals("0")){
			}else{
				if(targettype.equalsIgnoreCase("1")){
					sql = new StringBuffer("select substation_city from sub_message");
					sql.append(" where substation_name like '%总站%' and substation_provice like '%")
					.append(targetId)
					.append("%'");
				}else if(targettype.equalsIgnoreCase("2")){
					sql = new StringBuffer("select substation_city from sub_message");
					sql.append(" where substation_name like '%分站%' and substation_provice like '%")
					.append(targetId)
					.append("%'");
				}
			}
			
			st = conn.prepareStatement(sql.toString());
			rs = st.executeQuery();
			
			//对rs进行遍历，将结果封装到List中
			while(rs.next()){
				
				SubStation station = new SubStation();
				station.setSubStationCity(rs.getString("substation_city"));
				//把对象加入到list集合里面
				list.add(station);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}
	
	public List<SubStation> queryTown(String targetId, String targettype, String targetprovince) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SubStation> list = new ArrayList();
		
		try{
			//调用函数建立连接
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer();
			if(targettype.equalsIgnoreCase("1")){
				sql = new StringBuffer("select substation_town from sub_message");
				sql.append(" where substation_name like '%总站%' and substation_provice like '%")
				.append(targetprovince)
				.append("%' and substation_city like '%")
				.append(targetId)
				.append("%'");
			}else if(targettype.equalsIgnoreCase("2")){
				sql = new StringBuffer("select substation_town from sub_message");
				sql.append(" where substation_name like '%分站%' and substation_provice like '%")
				.append(targetprovince)
				.append("%' and substation_city like '%")
				.append(targetId)
				.append("%'");
			}
			
			st = conn.prepareStatement(sql.toString());
			rs = st.executeQuery();
			
			//对rs进行遍历，将结果封装到List中
			while(rs.next()){
				SubStation station = new SubStation();
				station.setSubStaitonTown(rs.getString("substation_town"));
				//把对象加入到list集合里面
				list.add(station);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}
	
	public void modification(SubStation station){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try{
			conn = DBManager.getConnection();
			if(station.getFlag().equalsIgnoreCase("1")){
				
			}
			
			//更新数据语句
			String sql = "update sub_message set ";
			if(station.getFlag().equalsIgnoreCase("1")){
				sql += "substation_hr_infor=?";
			}else if(station.getFlag().equalsIgnoreCase("2")){
				sql += "substation_pr_infor=?";
			}else if(station.getFlag().equalsIgnoreCase("3")){
				sql += "substation_bj_infor=?";
			}else if(station.getFlag().equalsIgnoreCase("4")){
				sql += "substation_jd_infor=?";
			}
			sql += " where substation_id=?";
			st = conn.prepareStatement(sql);
			
			if(station.getFlag().equalsIgnoreCase("1")){
				st.setString(1, station.getSubStation_hr_infor());
			}else if(station.getFlag().equalsIgnoreCase("2")){
				st.setString(1, station.getSubStation_pr_infor());
			}else if(station.getFlag().equalsIgnoreCase("3")){
				st.setString(1, station.getSubStation_bj_infor());
			}else if(station.getFlag().equalsIgnoreCase("4")){
				st.setString(1, station.getSubStation_jd_infor());
			}
			st.setLong(2, station.getSubStationID());
			
			//执行语句对象
			int i = st.executeUpdate();
			
			if(i > 0){
				System.out.println("update finished.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
	}

	public List<SubStation> queryStation(SubStation station, String type, String department) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SubStation> list = new ArrayList();
		
		try{
			//调用函数建立连接
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer();
			StringBuffer chooseDepartment = new StringBuffer();
			
			if(department.equalsIgnoreCase("1")){
				chooseDepartment = new StringBuffer("substation_hr_infor");
			}else if(department.equalsIgnoreCase("2")){
				chooseDepartment = new StringBuffer("substation_pr_infor");
			}else if(department.equalsIgnoreCase("3")){
				chooseDepartment = new StringBuffer("substation_bj_infor");
			}else if(department.equalsIgnoreCase("4")){
				chooseDepartment = new StringBuffer("substation_jd_infor");
			}
			
			if(type.equalsIgnoreCase("1")){
				sql = new StringBuffer("select substation_name, substation_id, emp_name, emp_tel, ");
				sql.append(chooseDepartment)
				.append(" from sub_message, emp_message where emp_message.emp_id = sub_message.substation_leader_id and")
				.append(" substation_name like '%总站%' and substation_provice like '%")
				.append(station.getSubStationProvince())
				.append("%' and substation_city like '%")
				.append(station.getSubStationCity())
				.append("%' and substation_town like '%")
				.append(station.getSubStaitonTown())
				.append("%'");
			}else if(type.equalsIgnoreCase("2")){
				sql = new StringBuffer("select substation_name, substation_id, emp_name, emp_tel, ");
				sql.append(chooseDepartment)
				.append(" from sub_message, emp_message where emp_message.emp_id = sub_message.substation_leader_id and")
				.append(" substation_name like '%分站%' and substation_provice like '%")
				.append(station.getSubStationProvince())
				.append("%' and substation_city like '%")
				.append(station.getSubStationCity())
				.append("%' and substation_town like '%")
				.append(station.getSubStaitonTown())
				.append("%'");
			}
			
			st = conn.prepareStatement(sql.toString());
			rs = st.executeQuery();
			
			//对rs进行遍历，将结果封装到List中
			while(rs.next()){
				SubStation result_station = new SubStation();
				result_station.setSubStationID(Long.parseLong(rs.getString("substation_id")));
				result_station.setSubStationName(rs.getString("substation_name"));
				result_station.setSubStationLeaderName(rs.getString("emp_name"));
				result_station.setSubStationLeaderTel(rs.getString("emp_tel"));
				if(department.equalsIgnoreCase("1")){
					result_station.setSubStation_hr_infor(rs.getString("substation_hr_infor"));
				}else if(department.equalsIgnoreCase("2")){
					result_station.setSubStation_pr_infor(rs.getString("substation_pr_infor"));
				}else if(department.equalsIgnoreCase("3")){
					result_station.setSubStation_bj_infor(rs.getString("substation_bj_infor"));
				}else if(department.equalsIgnoreCase("4")){
					result_station.setSubStation_jd_infor(rs.getString("substation_jd_infor"));
				}
				result_station.setFlag(department);
				//把对象加入到list集合里面
				list.add(result_station);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}
}
