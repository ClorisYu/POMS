package hit.poms.correctdistribution.dao;

import hit.poms.correctdistribution.entity.Correction;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CorrectAddressDao {
	
	public List correct(){
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		//System.out.println("*************");
		try{
			conn = DBManager.getConnection();
			String sql = "select order_id,customer_address,customer_id,customer_name,SUBSTR(start_time,1,10),SUBSTR(end_time,1,10),substation_id from CUSTOMER_BOOK_SUB natural join CUS_MESSAGE where if_toudi='0'  ";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				Correction correction = new Correction();
				correction.setOrder_id(rs.getLong(1));
				correction.setAddress(rs.getString(2));
				correction.setCustomer_id(rs.getLong(3));
				correction.setCustomer_name(rs.getString(4));
				correction.setStart_time(rs.getString(5));
				correction.setEnd_time(rs.getString(6));
				correction.setSub_id(rs.getLong(7));
				list.add(correction);
//				System.out.println(rs.getLong(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getLong(3));
//				System.out.println("!!!!!!!!!!!!!!!!!");
			}
			
		}catch(Exception e){
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list;
	}
	public boolean update(String order_id,String newsubstation){
		long sub = Long.parseLong(newsubstation);
		boolean flag = false;
		boolean flag1 = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "select substation_id from SUB_MESSAGE where substation_id=? ";
			st = conn.prepareStatement(sql);
			st.setLong(1, sub);
			rs = st.executeQuery();
			while(rs.next()){
				flag1 = true;
				break;
				
			}
			rs.close();
			st.close();
			if(flag1){
				String sql_1 = "update CUSTOMER_BOOK_SUB set substation_id=? where order_id=? ";
				st = conn.prepareStatement(sql_1);
				st.setLong(1, sub);
				st.setString(2,order_id);
				st.executeQuery();
				flag = true;
			}
			else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return flag;
	}
	public List search(String order_id){
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		//System.out.println("*************");
		try{
			conn = DBManager.getConnection();
			String sql = "select order_id,customer_address,customer_id,customer_name,SUBSTR(start_time,1,10),SUBSTR(end_time,1,10),substation_id from CUSTOMER_BOOK_SUB natural join CUS_MESSAGE where if_toudi='0' and order_id=? ";
			st = conn.prepareStatement(sql);
			st.setString(1, order_id);
			rs = st.executeQuery();
			while(rs.next()){
				Correction correction = new Correction();
				correction.setOrder_id(rs.getLong(1));
				correction.setAddress(rs.getString(2));
				correction.setCustomer_id(rs.getLong(3));
				correction.setCustomer_name(rs.getString(4));
				correction.setStart_time(rs.getString(5));
				correction.setEnd_time(rs.getString(6));
				correction.setSub_id(rs.getLong(7));
				list.add(correction);
//				System.out.println(rs.getLong(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getLong(3));
//				System.out.println("!!!!!!!!!!!!!!!!!");
			}
			
		}catch(Exception e){
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list;
	}
}
