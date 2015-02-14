package hit.poms.dao;

import java.sql.Connection;
import java.util.List;

import hit.poms.bean.transportBean;

public interface transportDao {
public void addtransport(transportBean bean,Connection con);
public Connection getConnection();
public List<transportBean> gettransportlist(Connection con);
public List<transportBean> gettransportlist(Connection con,String keyword);
public void Edittransport(transportBean bean,Connection con);
public void Deletetransport(transportBean bean,Connection con ,long id);
}
