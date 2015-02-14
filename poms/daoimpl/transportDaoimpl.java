package hit.poms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hit.poms.bean.transportBean;
import hit.poms.dao.transportDao;
import hit.poms.util.DBManager;
//create table TRANSPORT_MESSAGE
//(
//  transport_id           NUMBER(10) not null,
//  transport_company_name NVARCHAR2(20),
//  transport_person       NVARCHAR2(20),
//  transport_person_tel   NUMBER(11),
//  avaliable_time         VARCHAR2(20),
//  transport_address      VARCHAR2(200)
//)
public class transportDaoimpl implements transportDao{
	long id;
	
	public void addtransport(transportBean bean,Connection con) {
		String seq="select transport_company_seq.nextval id from dual";
		
		String sql="insert into transport_message values(?,?,?,?,?,?)";
		try {
			PreparedStatement st=con.prepareStatement(seq);
			ResultSet set=st.executeQuery();
			while(set.next())id=set.getLong("id");
			st=con.prepareStatement(sql);
		    st.setLong(1, id);
		    st.setString(2, bean.getCompname());
		    st.setString(3, bean.getLeadername());
		    st.setLong(4, bean.getLeadercontract());
		    st.setString(6,bean.getAddress());
		    st.setString(5, bean.getDavitime());
		    st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void Edittransport(transportBean bean,Connection con) {	
		String sql="update transport_message set TRANSPORT_PERSON=?,TRANSPORT_PERSON_TEL=?,AVALIABLE_TIME=?,TRANSPORT_ADDRESS=? where TRANSPORT_ID=?";
			
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setString(1, bean.getLeadername());
				st.setLong(2,bean.getLeadercontract());
				st.setString(3, bean.getDavitime());
				st.setString(4, bean.getAddress());
				st.setLong(5, bean.getId());
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
public void Deletetransport(transportBean bean,Connection con ,long id) {	
	String sql="delete from transport_message where TRANSPORT_ID=?";
		
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	public Connection getConnection() {
		return DBManager.getConnection();
	}

	public List<transportBean> gettransportlist( Connection con) {
		String sql="select * from  transport_message";
		List<transportBean> list=new ArrayList<transportBean>();
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			ResultSet set=st.executeQuery();
			while(set.next()){
				transportBean bean=new transportBean();
				bean.setAddress(set.getString(6));
				bean.setCompname(set.getString(2));
				bean.setDavitime(set.getString(5));
				bean.setLeadercontract(set.getLong(4));
				bean.setLeadername(set.getString(3));
				bean.setId(set.getLong(1));
				list.add(bean);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size()>0?list:null;
		
		
	}
	public List<transportBean> gettransportlist(Connection con,String keyword) {
		String sql="select * from  transport_message where TRANSPORT_COMPANY_NAME like '%"+
		keyword+"%'";
	//	System.out.print(keyword);
		List<transportBean> list=new ArrayList<transportBean>();
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
		//	st.setString(1, keyword);
			ResultSet set=st.executeQuery();
			while(set.next()){
				transportBean bean=new transportBean();
				bean.setAddress(set.getString(6));
				bean.setCompname(set.getString(2));
				bean.setDavitime(set.getString(5));
				bean.setLeadercontract(set.getLong(4));
				bean.setLeadername(set.getString(3));
				bean.setId(set.getLong(1));
				list.add(bean);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size()>0?list:null;
		
		
	}
}
