package hit.poms.paper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import hit.poms.search.entity.Pub_message;
import hit.poms.util.DBManager;

public class PaperDao {
	public boolean doAdd(Pub_message message){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "insert into PUB_MESSAGE values (?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, message.getPub_id());
			st.setString(2, message.getPub_name());
			st.setString(3, message.getPrint_num());
			st.setString(4, message.getSell_num());
			st.setString(5, message.getRest_num());
			st.setString(6, message.getImage());
			st.setString(7, message.getPub_price());
			st.setString(8, message.getPub_type());
			st.setString(9, message.getPub_debook());
			st.setString(10,message.getPub_redirect());
			st.setString(11, message.getPub_month_price());
			st.setString(12, message.getPub_year_price());
			st.setString(13, message.getPub_release_rate());
			st.setString(14, message.getPub_time());
			st.setString(15, message.getDelivery_unit());
			st.setString(16, message.getDiscount1());
			st.setString(17, message.getDiscount2());
			st.executeQuery();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
			
		}finally{
			DBManager.closeAll(conn, st, rs);
			
			
		}
		
	}
	public List doQueryByDate(String queryStr){
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "select pub_id,pub_name,substr(pub_time,1,10),pub_release_rate,pub_price,image,discount1,discount2 from pub_message where to_char(pub_time,'yyyy-MM') like ? ";
			st = conn.prepareStatement(sql);
			String change = "%"+queryStr+"%";
			st.setString(1, change);
			rs = st.executeQuery();
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Pub_message temp = new Pub_message();
				temp.setImage(rs.getString(6));
				temp.setPub_id(rs.getString(1));
				temp.setPub_name(rs.getString(2));
				temp.setPub_time(rs.getString(3));
				temp.setPub_release_rate(rs.getString(4));
				temp.setPub_price(rs.getString(5));
				temp.setDiscount1(rs.getString(7));
				temp.setDiscount2(rs.getString(8));
				
				
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		return list;
	}

	public List doQuery(Pub_message message){
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
			StringBuffer sql = new StringBuffer("select pub_id,pub_name,substr(pub_time,1,10),pub_release_rate,pub_price,image,discount1,discount2 from pub_message where 1=1 ");
			if(message.getPub_id() != null && !"".equals(message.getPub_id())){
				sql.append(" and pub_id like '%")
					.append(message.getPub_id())
					.append("%'");
			}
			if(message.getPub_name() != null && !"".equals(message.getPub_name())){
				sql.append(" and pub_name like '%")
					.append(message.getPub_name())
					.append("%'");
			}		
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Pub_message temp = new Pub_message();
				temp.setImage(rs.getString(6));
				temp.setPub_id(rs.getString(1));
				temp.setPub_name(rs.getString(2));
				temp.setPub_time(rs.getString(3));
				temp.setPub_release_rate(rs.getString(4));
				temp.setPub_price(rs.getString(5));
				temp.setDiscount1(rs.getString(7));
				temp.setDiscount2(rs.getString(8));
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
	
	
	public boolean doDelete(String id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			String sql = "delete from pub_message where pub_id = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.executeQuery();
			flag =true;
			
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
		
		return flag;
	}
	/*
	 * 暂时不用
	 */
     public boolean doUpdate(Pub_message message){
    	 //修改旧信息dao
    	 boolean flag =false;
    	 Connection conn = null;
 		PreparedStatement st = null;
 		ResultSet rs = null;
 		try{
			conn = DBManager.getConnection();
			String sql_1 = "delete from PUB_MESSAGE where pub_id=? ";
			st = conn.prepareStatement(sql_1);
			st.setString(1, message.getPub_id());
			st.executeQuery();
			st.close();
			String sql = "insert into PUB_MESSAGE values (?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, message.getPub_id());
			st.setString(2, message.getPub_name());
			st.setString(3, message.getPrint_num());
			st.setString(4, message.getSell_num());
			st.setString(5, message.getRest_num());
			st.setString(6, message.getImage());
			st.setString(7, message.getPub_price());
			st.setString(8, message.getPub_type());
			st.setString(9, message.getPub_debook());
			st.setString(10,message.getPub_redirect());
			st.setString(11, message.getPub_month_price());
			st.setString(12, message.getPub_year_price());
			st.setString(13, message.getPub_release_rate());
			st.setString(14, message.getPub_time());
			st.setString(15, message.getDelivery_unit());
			st.setString(16, message.getDiscount1());
			st.setString(17, message.getDiscount2());
			st.executeQuery();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
			
		}finally{
			DBManager.closeAll(conn, st, rs);
			
			
		}
    	
     }
     
     public Pub_message doSearch(String id){
    	 //修改旧信息dao
    	 Pub_message message = new Pub_message();
    	 Connection conn = null;
 		 PreparedStatement st = null;
 		 ResultSet rs = null;
 		try{
			conn = DBManager.getConnection();
			String sql = "select * from pub_message where pub_id = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs=st.executeQuery();
			while(rs.next()){
				message.setPub_id(rs.getString(1));
				message.setPub_name(rs.getString(2));
				message.setPrint_num(rs.getString(3));
				message.setSell_num(rs.getString(4));
				message.setRest_num(rs.getString(5));
				message.setImage(rs.getString(6));
				message.setPub_price(rs.getString(7));
				String type = rs.getString(8);
				if("城市报纸".equals(type)){
					type="a";
				}else if("财经生活".equals(type)){
					type="b";
				}else if("文艺娱乐".equals(type)){
					type="c";
				}else if("体坛赛事".equals(type)){
					type="d";
				}
				message.setPub_type(type);
				message.setPub_debook(rs.getString(9));
				message.setPub_redirect(rs.getString(10));
				message.setPub_month_price(rs.getString(11));
				message.setPub_year_price(rs.getString(12));
				message.setPub_release_rate(rs.getString(13));
				String time = rs.getString(14).substring(0, 10);
				message.setPub_time(time);
				message.setDelivery_unit(rs.getString(15));
				String temp1 = rs.getString(16);
				String temp2 = rs.getString(17);
				double a = (Double.parseDouble(temp1))*100;
				temp1 = a+"";
				
				double b = (Double.parseDouble(temp2))*100;
				temp2 = b+"";
				message.setDiscount1(temp1);
				message.setDiscount2(temp2);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
			
			
		}finally{
			DBManager.closeAll(conn, st, rs);
		}
    	 return message;
     }
}
