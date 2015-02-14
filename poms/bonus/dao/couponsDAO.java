package hit.poms.bonus.dao;


import hit.poms.bonus.entity.Coupons;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class couponsDAO {
	public static void main(String[] args) {
		couponsDAO t = new couponsDAO();
	}
	/**
	 * 删除时间过期的赠卡
	 * @param 
	 */
	public void doDelete(){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		Date sys_time = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		
		String nowtime = df.format(sys_time);
		System.out.println(nowtime);
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();		
			//3、创建语句对象
			String sql = "delete from coupons_message where end_time < to_date(?,'yyyy-mm-dd')";
			st = conn.prepareStatement(sql);
			st.setString(1, nowtime);
			System.out.println(sql);
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
//			st.setLong(1, user.getId());

			int i = st.executeUpdate();
			
			if(i > 0){
				System.out.println("---delete ok.");
			}	
			//5、判断增加是否成功		
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 修改
	 * @param user
	 */
	public void doUpdate(Coupons coupons){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//４-１：使用序列生成主键值 
			Long id = 0L;
			
			
			//3、创建语句对象
			String sql = "update coupons_message set coupons_type=?,coupons_money=?,start_time=to_date(?, 'yyyy-mm-dd'),end_time=to_date(?, 'yyyy-mm-dd'),Coupons_infor=? where coupons_id=?";
			st = conn.prepareStatement(sql);
			System.out.println(sql);
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
//			st.setLong(1, user.getId());
			st.setString(1,coupons.getCoupons_type());
			st.setString(2,coupons.getCoupons_money());
			st.setString(3,coupons.getStart_time());
			st.setString(4,coupons.getEnd_time());
			st.setString(5,coupons.getCoupons_infor());
			st.setString(6,coupons.getCoupons_id());
			int i = st.executeUpdate();
			
			//5、判断增加是否成功
			if(i > 0){
				System.out.println("---update ok.");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 增加
	 * @param couponsInfor
	 */
	public void doAdd(Coupons coupons ){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//４-１：使用序列生成主键值 
			String Coupons_id = null;
			//Long id = 0L;
			String sql_1 = "select coupons_message_seq.nextval coupons_id from dual";
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			while(rs.next()){
				Coupons_id = rs.getString("coupons_id");
			}
			System.out.println("Coupons_id is "+Coupons_id);
			//3、创建语句对象
			String sql = "insert into coupons_message(coupons_type,coupons_money,start_time,end_time,Coupons_infor,coupons_id) values(?,?,to_date(?, 'yyyy-mm-dd'),to_date(?, 'yyyy-mm-dd'),?,?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			
			st.setString(1,coupons.getCoupons_type());
			st.setString(2,coupons.getCoupons_money());
			st.setString(3,coupons.getStart_time());
			st.setString(4,coupons.getEnd_time());
			st.setString(5,coupons.getCoupons_infor());
			st.setString(6, Coupons_id);
			int i = st.executeUpdate();
			
			//5、判断增加是否成功
			if(i > 0){
				System.out.println("---add ok.");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 按赠卡类型查询
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doQueryByType(Coupons coupons){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from coupons_message where 1 = 1");
			if(coupons.getCoupons_type()!= null && !"".equals(coupons.getCoupons_type())&&!"0".equals(coupons.getCoupons_type())){
				sql.append(" and coupons_type like '%")
					.append(coupons.getCoupons_type())
					.append("%'");
			}
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			//st.setString(1, coupons.getCoupons_type());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				Coupons temp = new Coupons();
				temp.setCoupons_id(rs.getString("coupons_id"));
				temp.setCoupons_type(rs.getString("coupons_type"));
				temp.setCoupons_infor(rs.getString("Coupons_infor"));
				temp.setStart_time(rs.getString("start_time").substring(0,10));
				temp.setEnd_time(rs.getString("end_time").substring(0,10));
				temp.setCoupons_money(rs.getString("coupons_money"));
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
	/**
	 * 修改前按赠卡编号查询
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doQueryByID(Coupons coupons){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer("select * from coupons_message where coupons_id = ?");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, coupons.getCoupons_id());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				Coupons temp = new Coupons();
				temp.setCoupons_id(rs.getString("coupons_id"));
				temp.setCoupons_type(rs.getString("coupons_type"));
				temp.setCoupons_infor(rs.getString("Coupons_infor"));
				temp.setStart_time(rs.getString("start_time").substring(0,10));
				temp.setEnd_time(rs.getString("end_time").substring(0,10));
				temp.setCoupons_money(rs.getString("coupons_money"));
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}
}