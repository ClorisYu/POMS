package hit.poms.dao;

import hit.poms.bean.TotalprintBean;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GettotalprintDAO {
	public Connection getConnection() {
		return DBManager.getConnection();
	}
public List<TotalprintBean> gettotalprint(Connection con){
	List<TotalprintBean> list=new ArrayList<TotalprintBean>();
	String sql="select  c.substation_id,c.pub_id,s.substation_name,p.pub_name,sum(pub_need_num) from customer_book_sub c,sub_message s, pub_message p where "+
     " c.substation_id=s.substation_id and c.pub_id=p.pub_id and sysdate >= c.start_time and sysdate < c.end_time group by c.substation_id,c.pub_id,s.substation_name,p.pub_name";
	
	try {
		Statement state=con.createStatement();
		ResultSet set=state.executeQuery(sql);
		while(set.next()){
			TotalprintBean bean=new TotalprintBean();
			bean.setCount(set.getInt(5));
			bean.setNewspaper_id(set.getLong(2));
			bean.setNewspaper_name(set.getString(4));
			bean.setSub_id(set.getLong(1));
			bean.setSub_name(set.getString(3));
			list.add(bean);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
}
