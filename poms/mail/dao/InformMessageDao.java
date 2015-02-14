package hit.poms.mail.dao;

import hit.poms.mail.entity.InformMessage;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 消息通知的Dao层代码
 */
public class InformMessageDao {
	
	public List doFromQuery(String get_emp_id){
		//boolean flag = true;
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			String sql = "select send_emp_id,get_emp_id,if_read,substr(content,1,30),send_time,inform_id from inform_message where get_emp_id =? order by inform_id desc ";
			st = conn.prepareStatement(sql);
			st.setString(1, get_emp_id);
			rs = st.executeQuery();
			while(rs.next()){
				InformMessage temp = new InformMessage();
				temp.setSend_emp_id(rs.getLong(1));
				temp.setIf_read(rs.getString(3));
				temp.setContent(rs.getString(4));
				temp.setSend_time(rs.getString(5));
				temp.setInform_id(rs.getLong(6));
				list.add(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list;
	}
	public List doFromQueryByDate(String get_emp_id,String date ){
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "select * from inform_message where get_emp_id =? and substr(send_time,1,10)=? order by inform_id desc ";
			st = conn.prepareStatement(sql);
			st.setString(1, get_emp_id);
			st.setString(2, date);
			rs = st.executeQuery();
			
			while(rs.next()){
				
				InformMessage temp = new InformMessage();
				temp.setSend_emp_id(rs.getLong(1));
				temp.setIf_read(rs.getString(3));
				temp.setContent(rs.getString(4));
				temp.setSend_time(rs.getString(5));
				temp.setInform_id(rs.getLong(6));
				list.add(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list;
	}
	public List doToQuery(String send_emp_id){
		//boolean flag = true;
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			String sql = "select send_emp_id,get_emp_id,if_read,substr(content,1,30),send_time,inform_id from inform_message where send_emp_id =? order by inform_id desc ";
			st = conn.prepareStatement(sql);
			st.setString(1, send_emp_id);
			rs = st.executeQuery();
			while(rs.next()){
				InformMessage temp = new InformMessage();
				temp.setGet_emp_id(rs.getLong(2));
				temp.setIf_read(rs.getString(3));
				temp.setContent(rs.getString(4));
				temp.setSend_time(rs.getString(5));
				temp.setInform_id(rs.getLong(6));
				list.add(temp);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list;
	}
	public boolean doDelete(String id){
		boolean flag = true;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "delete from inform_message where inform_id = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.executeQuery();
			
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return flag;
	}
	public InformMessage doDetail(String id){
		InformMessage im = new InformMessage();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "select *from inform_message where inform_id = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				im.setSend_emp_id(rs.getLong(1));
				im.setIf_read(rs.getString(3));
				im.setContent(rs.getString(4));
				im.setInform_id(Long.parseLong(id));
				}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return im;
	}
	public boolean doMark(String id){
		boolean flag = true;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "update inform_message set if_read = '1' where inform_id = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.executeQuery();
			
			
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return flag;
	}
	public boolean doSearch(String get_emp_id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "select * from EMP_MESSAGE where EMP_ID = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, get_emp_id);
			rs = st.executeQuery();
			while(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return flag;
	}
	public boolean doInsert(String send_emp_id,String get_emp_id,String content){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try{
			conn = DBManager.getConnection();
			long inform_id = 0L;
			String sql_1 = "select inform_quen.nextval inform_id from dual ";
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			while(rs.next()){
				inform_id = rs.getLong(1);
			}
			rs.close();
			st.close();
 			String sql = "insert into inform_message  values (?,?,?,?,?,?)";
 			st = conn.prepareStatement(sql);
 			st.setString(1, send_emp_id);
 			System.out.println("--a="+send_emp_id);
 			st.setString(2, get_emp_id);
 			System.out.println("--b="+get_emp_id);
 			st.setString(3, "0");
 			System.out.println("--c");
 			st.setString(4, content);
 			System.out.println("--d="+content);
 			st.setString(5, df.format(new Date()));
 			//System.out.println("--e");
 			st.setLong(6, inform_id);
 			System.out.println("--e="+inform_id);
 			st.executeQuery();
 			flag = true;
			
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
}
