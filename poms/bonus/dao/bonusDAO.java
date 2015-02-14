package hit.poms.bonus.dao;


import hit.poms.bonus.entity.BonusInfor;
import hit.poms.bonus.entity.PubSelect;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;



public class bonusDAO {
	public static void main(String[] args) {
		bonusDAO t = new bonusDAO();
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
			String sql = "delete from bonus_card where end_time < to_date(?,'yyyy-mm-dd')";
			st = conn.prepareStatement(sql);
			st.setString(1, nowtime);
			System.out.println(sql);
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
//			st.setLong(1, user.getId());

			st.executeUpdate();
			
			
			//5、判断增加是否成功		
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		
	}
	/**
	 * 增加赠卡前需要先遍历书籍库
	 * @param 
	 */
	public List doToADD(){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//４-１：使用序列生成主键值 
			Long id = 0L;
			
			
			//3、创建语句对象
			String sql = "select pub_id,pub_name from pub_message";
			st = conn.prepareStatement(sql);
			System.out.println(sql);
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
//			st.setLong(1, user.getId());

			rs = st.executeQuery();
			while(rs.next()){
				PubSelect temp = new PubSelect();
				temp.setPub_name(rs.getString("pub_name"));
				temp.setPub_id(rs.getString("pub_id"));
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
			}
			
			//5、判断增加是否成功		
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()==0) System.out.println("list is  null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
	}
	/**
	 * 修改
	 * @param user
	 */
	public void doUpdate(BonusInfor bonus){
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
			String sql = "update bonus_card set gift_type=?,pub_id=?,start_time=to_date(?, 'yyyy-mm-dd'),end_time=to_date(?, 'yyyy-mm-dd'),card_infor=? where gift_id=?";
			st = conn.prepareStatement(sql);
			System.out.println(sql);
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
//			st.setLong(1, user.getId());
			st.setString(1,bonus.getCard_type());
			st.setString(2,bonus.getPub_id());
			st.setString(3,bonus.getStart_time());
			st.setString(4,bonus.getEnd_time());
			st.setString(5,bonus.getCard_infor());
			st.setString(6,bonus.getCard_id());
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
	 * @param BonusInfor
	 */
	public void doAdd(BonusInfor bonus){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();

			//４-１：使用序列生成主键值 
			String card_id = null;
			//Long id = 0L;
			String sql_1 = "select bonus_card_seq.nextval gift_id from dual";
			st = conn.prepareStatement(sql_1);
			rs = st.executeQuery();
			while(rs.next()){
				card_id = rs.getString("gift_id");
			}
			System.out.println("card_id is "+card_id);
			//3、创建语句对象
			String sql = "insert into bonus_card(gift_type,pub_id,start_time,end_time,card_infor,gift_id) values(?,?,to_date(?, 'yyyy-mm-dd'),to_date(?, 'yyyy-mm-dd'),?,?)";
			st = conn.prepareStatement(sql);
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			
			st.setString(1,bonus.getCard_type());
			st.setString(2,bonus.getPub_id());
			st.setString(3,bonus.getStart_time());
			st.setString(4,bonus.getEnd_time());
			st.setString(5,bonus.getCard_infor());
			st.setString(6, card_id);
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
	public List doQueryByType(BonusInfor bonus){
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
			StringBuffer sql = new StringBuffer("select * from bonus_card natural join pub_message where 1 = 1");
			if(bonus.getCard_type()!= null && !"".equals(bonus.getCard_type())&&!"0".equals(bonus.getCard_type())){
				sql.append(" and gift_type like '%")
					.append(bonus.getCard_type())
					.append("%'");
			}
			sql.append(" order by gift_type ");
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			//st.setString(1, bonus.getCard_type());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				BonusInfor temp = new BonusInfor();
				temp.setCard_id(rs.getString("gift_id"));
				temp.setCard_type(rs.getString("gift_type"));
				temp.setCard_infor(rs.getString("card_infor"));
				temp.setStart_time(rs.getString("start_time").substring(0,10));
				temp.setEnd_time(rs.getString("end_time").substring(0,10));
				temp.setPub_id(rs.getString("pub_name"));
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
	 * 按赠卡编号查询
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	public List doQueryByID(BonusInfor bonus){
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
			StringBuffer sql = new StringBuffer("select * from bonus_card where gift_id = ?");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, bonus.getCard_id());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				BonusInfor temp = new BonusInfor();
				temp.setCard_id(rs.getString("gift_id"));
				temp.setCard_type(rs.getString("gift_type"));
				temp.setCard_infor(rs.getString("card_infor"));
				temp.setStart_time(rs.getString("start_time").substring(0,10));
				temp.setEnd_time(rs.getString("end_time").substring(0,10));
				temp.setPub_id(rs.getString("pub_id"));
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
	 * 每日报刊汇总
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	/*public List doSummary(BonusInforInfor bonus){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		int i = 0;
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sum(send_num),pub_id from ((sub_message natural join pub_message) natural join summary_infor)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			
			StringBuffer sql = new StringBuffer("select new_order_num,new_real_num,pub_id,pub_name from(select sum(order_num)as new_order_num ,sum(real_num)as new_real_num,pub_id from all_message where summary_date = to_date(?,'yyyy-mm-dd') group by pub_id)natural join pub_message  ");
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				SummaryInfor temp = new SummaryInfor();
				System.out.println(rs.getLong("pub_id"));
				temp.setPub_id(rs.getLong("pub_id"));                   
				temp.setPub_name(rs.getString("pub_name"));
//				temp.setSubstation_id(rs.getLong("substation_id"));
//				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setOrder_num(rs.getLong("new_order_num"));
				temp.setReal_num(rs.getLong("new_real_num"));
				temp.setSummary_date(suminfo.getSummary_date());
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
		
	}*/
	/**
	 * 下数单汇总钱需要判断是否存在汇总表中
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	/*public boolean doQuery(BonusInforInfor bonus){
		//1、声明数据库相关对象
		boolean flag = false;
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sell_num as order_num,substation_id,substation_name ,pub_id,pub_name from ((sub_message natural join pub_message) natural join sub_sell_pub)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			StringBuffer sql = new StringBuffer("select * from all_message  where summary_date = to_date('"+summary_date+"','yyyy-mm-dd')");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，如果有数据，这将flag标志为true
			if(rs.next()){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return flag ; //如果list中没有元素，那么返回null。
		
	}*/
	/**
	 * 下数单汇总从汇总表中取
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	/*public List doOrderSummaryHuizong(BonusInforInfor bonus){
		//1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sell_num as order_num,substation_id,substation_name ,pub_id,pub_name from ((sub_message natural join pub_message) natural join sub_sell_pub)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			StringBuffer sql = new StringBuffer("select * from ((sub_message natural join all_message) natural join pub_message)  where summary_date=to_date(?,'yyyy-mm-dd') order by substation_id");
			
				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, suminfo.getSummary_date());
			System.out.println(suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				SummaryInfor temp = new SummaryInfor();
				System.out.println(rs.getLong("pub_id"));
				temp.setPub_id(rs.getLong("pub_id"));                   
				temp.setPub_name(rs.getString("pub_name"));
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setOrder_num(rs.getLong("order_num"));
				temp.setReal_num(rs.getLong("real_num"));
				temp.setSummary_date(rs.getString("summary_date").substring(0,10));
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
				//将找到的结果存到汇总表中
				StringBuffer sql1 = new StringBuffer("");
				
				System.out.println("---sql=" + sql);
				st = conn.prepareStatement(sql.toString());
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源			
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
		
	}*/
	/**
	 * 下数单汇总，订单表中取
	 * @param suminfo
	 * @param summary_date
	 * @return
	 */
	/*public List doOrderSummary(BonusInforInfor bonus){
		//1、声明数据库相关对象
		Connection conn = null;
		
		PreparedStatement st = null;
		PreparedStatement st1 = null;
	
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		List list = new ArrayList();
		try{
			//2、获取数据库连接对象
			conn = DBManager.getConnection();
			//3、创建语句对象
			//因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
//			StringBuffer sql = new StringBuffer("select sell_num as order_num,substation_id,substation_name ,pub_id,pub_name from ((sub_message natural join pub_message) natural join sub_sell_pub)  where send_time=to_date('"+summary_date+"','yyyy-mm-dd') group by substation_id,pub_id");
			StringBuffer sql = new StringBuffer("select order_num,pub_id,pub_name,substation_id,substation_name from((select sum(pub_need_num)as order_num,pub_id,substation_id from  customer_book_sub  where start_time=to_date(?,'yyyy-mm-dd') group by pub_id,substation_id) natural join sub_message) natural join pub_message");
//			select sum(pub_need_num)as order_num,pub_id,substation_id from ((sub_message natural join pub_message) natural join customer_book_sub)  where start_time=to_date('2014-7-15','yyyy-mm-dd') group by pub_id,substation_id

				
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			st.setString(1, suminfo.getSummary_date());
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建suminfo对象，将每条记录的字段值以属性值的方式存储到suminfo对象中
				SummaryInfor temp = new SummaryInfor();
				System.out.println(rs.getLong("pub_id"));
				temp.setPub_id(rs.getLong("pub_id"));                   
				temp.setPub_name(rs.getString("pub_name"));
//				temp.setPub_name(rs.getString("pub_name"));
//				Long pub_id = rs.getLong("pub_id");
//				Long sub_id = rs.getLong("substation_id");
//				StringBuffer sql2 = new StringBuffer("select pub_name from pub_message where pub_id=? ");
//				StringBuffer sql3 = new StringBuffer("select substation_name from sub_message where substation_id_id=? ");
				
				
				
				temp.setSubstation_id(rs.getLong("substation_id"));
				temp.setSubstation_name(rs.getString("substation_name"));
				temp.setOrder_num(rs.getLong("order_num"));
				temp.setReal_num(rs.getLong("order_num"));
				temp.setSummary_date(suminfo.getSummary_date());
				//flag_id = rs.getLong("pub_id");
				//把对象加入到list集合里
				list.add(temp);
				
				
				//将找到的结果存到汇总表中
				StringBuffer sql1 = new StringBuffer("insert into all_message(substation_id,pub_id,order_num,real_num,summary_date) values(?,?,?,?,to_date(?, 'yyyy-mm-dd'))");
				
				System.out.println("---sql=" + sql1);
				st1 = conn.prepareStatement(sql1.toString());
				st1.setLong(1, rs.getLong("substation_id"));
				st1.setLong(2, rs.getLong("pub_id"));
				st1.setLong(3, rs.getLong("order_num"));
				st1.setLong(4, rs.getLong("order_num"));
				st1.setString(5, suminfo.getSummary_date());
				st1.executeUpdate();
				System.out.println(suminfo.getSummary_date());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//释放所有数据库资源		
			
			//DBManager.closeAll(conn, st1, rs1);
			DBManager.closeAll(conn, st, rs);
		}
		if(list.size()!=0) System.out.println("list is not null");
		return list.size()>0 ? list : null; //如果list中没有元素，那么返回null。
	}*/	
}
