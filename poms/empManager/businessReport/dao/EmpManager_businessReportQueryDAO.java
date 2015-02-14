package hit.poms.empManager.businessReport.dao;

import hit.poms.empManager.businessReport.entity.Report;
import hit.poms.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.registry.infomodel.User;

public class EmpManager_businessReportQueryDAO {
	
	
	public List doQuery(Long id){
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
			StringBuffer sql = new StringBuffer("select e1.emp_id emp_id,emp_name, p1.pub_id pub_id,p1.pub_name,e2.emp_order,e2.emp_delivery,e2.fin_time ")
			                      .append(" from emp_message e1,emp_sell_pub e2,pub_message p1")
			                      .append(" where e1.emp_id=e2.emp_id and p1.pub_id=e2.pub_id ");
			if(id != null){
				sql.append(" and e1.emp_id="+id);
			}                     
			
			System.out.println("---sql=" + sql);
			st = conn.prepareStatement(sql.toString());
			
			//4、执行语句对象。注意：如果语句中有参数，需要先给语句对象传值
			rs = st.executeQuery();
			
			//5、对rs进行遍历，将结果封装到list中
			while(rs.next()){
				//创建User对象，将每条记录的字段值以属性值的方式存储到User对象中
				Report temp = new Report();
				temp.setEmp_name(rs.getString("emp_name"));
				temp.setEmp_id(rs.getLong("emp_id"));
				temp.setPub_id(rs.getLong("pub_id"));
				temp.setPub_name(rs.getString("pub_name"));
				temp.setEmp_order(rs.getLong("emp_order"));
				temp.setEmp_delivery(rs.getLong("emp_delivery"));
				temp.setFin_time(rs.getString("fin_time").substring(0, 10));
				
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
	
}
