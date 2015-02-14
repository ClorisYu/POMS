package hit.poms.bigClientManager.dao;

import hit.poms.bigClientManager.entity.BigClient;
import hit.poms.deliverManager.desManager.entity.DesEmp;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BigClientManager_PayServletDAO {

	public List doAllQuery(BigClient bc) {
		// 1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			// 2、获取数据库连接对象
			conn = DBManager.getConnection();

			// 3、创建语句对象
			// 因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer(
					"select m.customer_id customer_id,m.customer_name customer_name,"
							+ "b.order_id order_id,b.if_paied if_paied,b.pub_money pub_money,"
							+ "b.pub_need_num pub_need_num,b.paied_money paied_money "
							+ " from cus_message m,customer_book_sub b "
							+ " where m.customer_id=b.customer_id and m.customer_grade='2' and m.customer_id="
							+ bc.getCustomer_id());

			if (bc.getOrder_id() != null) {
				sql.append(" and b.order_id=" + bc.getOrder_id());
			}

			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());

			// 4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();

			// 5、对rs进行遍历，将结果封装到list中
			while (rs.next()) {
				// 创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				BigClient temp = new BigClient();
				temp.setCustomer_id(rs.getLong("customer_id"));
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setIf_paied(rs.getString("if_paied"));
				temp.setOrder_id(rs.getLong("order_id"));
				temp.setPaied_money(rs.getDouble("paied_money"));
				temp.setPub_money(rs.getDouble("pub_money"));
				temp.setPub_need_num(rs.getLong("pub_need_num"));

				// 把对象加入到list集合里
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 释放所有数据库资源
			DBManager.closeAll(conn, st, rs);
		}

		return list.size() > 0 ? list : null; // 如果list中没有元素，那么返回null。

	}

	public void doUpdateIfPay(Long order_id, Double order_money) {
		// 1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// 2、获取数据库连接对象
			conn = DBManager.getConnection();

			// 3、创建语句对象
			String sql = "update customer_book_sub set paied_money=?, if_paied='1'"
					+ " where order_id=?";
			st = conn.prepareStatement(sql);
			System.out.println(sql);

			// 4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setDouble(1, order_money);
			st.setLong(2, order_id);

			int i = st.executeUpdate();

			// 5、判断修改是否成功
			if (i > 0) {
				System.out.println("---update ok.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 释放所有数据库资源
			DBManager.closeAll(conn, st, rs);
		}
	}

	public void doUpdatePay(Long order_id, Double paied_money) {
		// 1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// 2、获取数据库连接对象
			conn = DBManager.getConnection();

			// 3、创建语句对象
			String sql = "update customer_book_sub set paied_money=?"
					+ " where order_id=?";
			st = conn.prepareStatement(sql);
			System.out.println(sql);

			// 4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			st.setDouble(1, paied_money);
			st.setLong(2, order_id);

			int i = st.executeUpdate();

			// 5、判断修改是否成功
			if (i > 0) {
				System.out.println("---update ok.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 释放所有数据库资源
			DBManager.closeAll(conn, st, rs);
		}
	}

	public List doQuery(BigClient bc) {
		// 1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			// 2、获取数据库连接对象
			conn = DBManager.getConnection();

			// 3、创建语句对象
			// 因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer(
					"select m.customer_id customer_id,m.customer_name customer_name,"
							+ "b.order_id order_id,b.if_paied if_paied,b.pub_money pub_money,"
							+ "b.pub_need_num pub_need_num,b.paied_money paied_money "
							+ " from cus_message m,customer_book_sub b "
							+ " where m.customer_id=b.customer_id and m.customer_grade='2' and b.if_paied='0' and m.customer_id="
							+ bc.getCustomer_id());

			if (bc.getOrder_id() != null) {
				sql.append(" and b.order_id=" + bc.getOrder_id());
			}

			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());

			// 4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();

			// 5、对rs进行遍历，将结果封装到list中
			while (rs.next()) {
				// 创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				BigClient temp = new BigClient();
				temp.setCustomer_id(rs.getLong("customer_id"));
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setIf_paied(rs.getString("if_paied"));
				temp.setOrder_id(rs.getLong("order_id"));
				temp.setPaied_money(rs.getDouble("paied_money"));
				temp.setPub_money(rs.getDouble("pub_money"));
				temp.setPub_need_num(rs.getLong("pub_need_num"));

				// 把对象加入到list集合里
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 释放所有数据库资源
			DBManager.closeAll(conn, st, rs);
		}

		return list.size() > 0 ? list : null; // 如果list中没有元素，那么返回null。

	}
	
	public List doDetailQuery(BigClient bc) {
		// 1、声明数据库相关对象
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			// 2、获取数据库连接对象
			conn = DBManager.getConnection();

			// 3、创建语句对象
			// 因为需要给查询增加相应的条件控制，所以sql语句需要动态组成
			StringBuffer sql = new StringBuffer(
					"select m.customer_id customer_id,m.customer_name customer_name,"
							+ "b.order_id order_id,b.if_paied if_paied,b.pub_money pub_money,"
							+ "b.pub_need_num pub_need_num,b.paied_money paied_money "
							+ " from cus_message m,customer_book_sub b "
							+ " where m.customer_id=b.customer_id and m.customer_grade='2' "
							);

			if (bc.getOrder_id() != null) {
				sql.append(" and b.order_id=" + bc.getOrder_id());
			}

			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());

			// 4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();

			// 5、对rs进行遍历，将结果封装到list中
			while (rs.next()) {
				// 创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				BigClient temp = new BigClient();
				temp.setCustomer_id(rs.getLong("customer_id"));
				temp.setCustomer_name(rs.getString("customer_name"));
				temp.setIf_paied(rs.getString("if_paied"));
				temp.setOrder_id(rs.getLong("order_id"));
				temp.setPaied_money(rs.getDouble("paied_money"));
				temp.setPub_money(rs.getDouble("pub_money"));
				temp.setPub_need_num(rs.getLong("pub_need_num"));

				// 把对象加入到list集合里
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 释放所有数据库资源
			DBManager.closeAll(conn, st, rs);
		}

		return list.size() > 0 ? list : null; // 如果list中没有元素，那么返回null。

	}
}
