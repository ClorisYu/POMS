package hit.poms.dao;
import hit.poms.bean.Print_subBean;
import hit.poms.util.DBManager;
import hit.poms.util.Manytree;
import hit.poms.util.Manytreenode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class pathDao {
	public Connection getConnection() {
		return DBManager.getConnection();
	}

	public List<String> getpathlist(Connection con,String from,String to) {
		List<String> list=new ArrayList<String>(); 
	//	System.out.print(from);
	//	System.out.print(to);
		///select * from path_map where distance like '%长春印刷%德惠分社%';
		String sql=" select * from path_map where distance like '%"+from+"%"+to+"%'";
		//System.out.print(sql);
		try {
			Statement state=con.createStatement();
			ResultSet set=state.executeQuery(sql);
			while(set.next()){
				String temp=set.getString(3);
				String str=temp.substring(temp.indexOf(from), temp.indexOf(to)+to.length());
				list.add(str);
				System.out.println(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size()<=0?null:list;
	//	Manytree tree=this.gettree(from,con);
		//System.out.print(from);
		//System.out.print(iteratorTree(tree.getRoot()));
	}
public String iteratorTree(Manytreenode treeNode){
	StringBuilder sb = new StringBuilder();
	
	if (treeNode != null) {
		
		if ("长春新华分社".equals(treeNode.getBean().getNodename())) {
			sb.append(treeNode.getBean().getKey() + ",");
		}
		
		for (Manytreenode index : treeNode.getList()) {
			
			sb.append(index.getBean().getKey() + ",");
			
			if (index.getList() != null && index.getList().size() > 0 ) {
				
				sb.append(iteratorTree(index));
				
			}
		}
	}
	
	return sb.toString();
	}
public void confirmpath(String from,String to,Connection con,String path){
	String sql="update PRINT_SEND_MESSAGE set path =? where print_id=" +
			"( select print_id from PRINT_POINT where print_name=?)"+" and sub_id=(" +
					"select substation_id from sub_message where substation_name=?)";
	try {
		PreparedStatement pr=con.prepareStatement(sql);
		pr.setString(1, path);
		pr.setString(2, from);
	    pr.setString(3, to);
		System.out.print(sql);
		System.out.print(from);
		System.out.print(to);
		pr.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public List<Print_subBean> getprint_sub( Connection con){
	String sql="select p.path,p.print_id,p.sub_id,s.substation_name,pp.print_name from print_send_message p,sub_message s,print_point pp where p.sub_id=s.substation_id and " +
			" p.print_id=pp.print_id";
	List<Print_subBean> list =new ArrayList<Print_subBean>();
	try {
		Statement state=con.createStatement();
		ResultSet set=state.executeQuery(sql);
		while(set.next()){
			Print_subBean bean=new Print_subBean();
			bean.setPath(set.getString(1));
			bean.setPrint_id(set.getLong(2));
			bean.setSub_id(set.getLong(3));
			bean.setSub_name(set.getString(4));
			bean.setPrint_name(set.getString(5));
			list.add(bean);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
	
}
public List<Print_subBean> getprint_sub( Connection con,String key){
	System.out.print(key);
	String sql="select p.path,p.print_id,p.sub_id,s.substation_name,pp.print_name from print_send_message p,sub_message s,print_point pp where p.sub_id=s.substation_id and " +
			" p.print_id=pp.print_id and pp.print_name like '%"+key+"%'";
	List<Print_subBean> list =new ArrayList<Print_subBean>();
	try {
		Statement state=con.createStatement();
		ResultSet set=state.executeQuery(sql);
		while(set.next()){
			Print_subBean bean=new Print_subBean();
			bean.setPath(set.getString(1));
			bean.setPrint_id(set.getLong(2));
			bean.setSub_id(set.getLong(3));
			bean.setSub_name(set.getString(4));
			bean.setPrint_name(set.getString(5));
			list.add(bean);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
	
}
/*
 * 这代码风格极其恶劣，写这段代码的本人因为过于愧疚而至今处于昏迷状态
 * 这是极不友好的编程方式
 * ps:有待实现
 * 
 */
 /*    public Manytree gettree(String  from ,Connection con){
    	
    	Manytree tree=new Manytree("1");
    	String sql="select * from path_map where fromname = ?";
    	PreparedStatement st;
    	ResultSet set1,set2,set3,set4;
    	int i=1;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, from);
			set1=st.executeQuery();
			while(set1.next()){
				
				Manytreenode node1=new Manytreenode("1"+i);
				node1.getBean().setNodename(set1.getString(2));
				tree.getRoot().getList().add(node1);
				i++;
				int j=1;
				st = con.prepareStatement(sql);
				st.setString(1, set1.getString(2));
				set2=st.executeQuery();
				while(set2.next()){
                    System.out.print(set2.getString(2));
					Manytreenode node2=new Manytreenode("1"+i+j);
					node2.getBean().setNodename(set2.getString(2));
					tree.getRoot().getList().get(i-2).getList().add(node2);
					j++;
					int k=1;
					st = con.prepareStatement(sql);
					st.setString(1, set2.getString(2));
					set3=st.executeQuery();
					while(set3.next()){

						Manytreenode node3=new Manytreenode("1"+i+j+k);
						node3.getBean().setNodename(set3.getString(2));
						tree.getRoot().getList().get(i-2).getList().get(j-2).getList().add(node3);
						k++;
						int h=1;
						st = con.prepareStatement(sql);
						st.setString(1, set3.getString(2));
						set4=st.executeQuery();
						while(set4.next()){

							Manytreenode node4=new Manytreenode("1"+i+j);
							node4.getBean().setNodename(set4.getString(2));
							tree.getRoot().getList().get(i-2).getList().get(j-2).getList().get(k-2).getList().add(node4);
							h++;
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    return tree;
    
    }*/
}
