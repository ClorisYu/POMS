package hit.poms.financeManage.dao;

import hit.poms.dao.entity.SubSellPub;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubSettleDAO {

	public List doQuery(SubSellPub cbs) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		int flag = 0;
		try {
			conn = DBManager.getConnection();
			StringBuffer sql = new StringBuffer("select substation_id from sub_sell_pub where send_time=to_date(?,'yyyy-mm-dd') group by substation_id");
			st = conn.prepareStatement(sql.toString());
			st.setString(1, cbs.getSend_time());
			rs = st.executeQuery();
			while(rs.next()){
				SubSellPub temp = new SubSellPub();
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setGet_money(rs.getDouble("get_money"));
				temp.setSend_time(rs.getString("send_time").substring(0,10));
				list.add(temp);
				list.add(flag);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{		
			DBManager.closeAll(conn, st, rs);
		}
		return list.size()>0 ? list : null;
	}

}
