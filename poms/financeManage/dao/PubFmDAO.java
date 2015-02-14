package hit.poms.financeManage.dao;

import hit.poms.dao.entity.SubSellPub;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PubFmDAO {

	/**
	 * 查询投递量
	 * @param ssp 某分站某刊物某天的投递量
	 * @return 
	 */
	public List doQuery(SubSellPub ssp) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBManager.getConnection();
			if(ssp.getSubstation_id()==0 && ssp.getPub_id() == 0){
				StringBuffer sql = new StringBuffer("select * from sub_sell_pub where send_time = to_date(?,'yyyy-mm-dd') " +
						"order by substation_id");
				st = conn.prepareStatement(sql.toString());
				st.setString(1,ssp.getSend_time());
			}else if(ssp.getSubstation_id() != 0 && ssp.getPub_id()==0){
				StringBuffer sql = new StringBuffer("select * from sub_sell_pub where send_time = to_date(?,'yyyy-mm-dd') " +
						"and substation_id = ? order by pub_id");
				st = conn.prepareStatement(sql.toString());
				st.setString(1,ssp.getSend_time());
				st.setLong(2, ssp.getSubstation_id());
			}else if(ssp.getSubstation_id() == 0 && ssp.getPub_id() != 0){
				StringBuffer sql = new StringBuffer("select * from sub_sell_pub where send_time = to_date(?,'yyyy-mm-dd') " +
				"and pub_id = ? order by substation_id");
				st = conn.prepareStatement(sql.toString());
				st.setString(1,ssp.getSend_time());
				st.setLong(2, ssp.getPub_id());
			}else{
				StringBuffer sql = new StringBuffer("select * from sub_sell_pub where send_time = to_date(?,'yyyy-mm-dd') " +
				"and substation_id = ? and pub_id = ?");
				st = conn.prepareStatement(sql.toString());
				st.setString(1,ssp.getSend_time());
				st.setLong(2, ssp.getSubstation_id());
				st.setLong(3, ssp.getPub_id());
			}
			
			rs = st.executeQuery();
			while(rs.next()){
				SubSellPub temp = new SubSellPub();
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setPub_id(rs.getLong("pub_id"));
				temp.setPub_send_num(rs.getLong("pub_send_num"));
				temp.setSend_time(rs.getString("send_time").substring(0,10));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{		
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}
	

}
