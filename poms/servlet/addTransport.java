package hit.poms.servlet;

import hit.poms.bean.transportBean;
import hit.poms.dao.transportDao;
import hit.poms.daoimpl.transportDaoimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addTransport extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addTransport() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

//	<form action="<%=basePath %>addTransport" onsubmit="return check()" method="post">
//	 <div class="function-panel">
//		<table class="table table-bordered table-hover">
//			<tr style="background-color:#F6F6F6">
//			<th width="180px">名称</th>
//			<th>填写项</th>	
//			</tr>
//			<tr>
//			<td>公司名称</td>
//				<td><input type="text" name="compname" id="compname"/></td></tr>
//			<tr>
//				<td>负责人</td>
//				<td><input type="text" name="leader" id="leader"/></td>
//			</tr>
//			<tr>
//				<td>负责人联系方式</td>
//				<td><input type="text" name="leadercontract" id="leadercontract"/></td>
//			</tr>
//			<tr>
//				<td>公司地点</td>
//				<td>
//				<select id="province" onchange="getchild(this.value)" >
//	          <option>
//			        请选择
//			    </option>
//				<option>吉林</option>
//				<option>黑龙江</option>
//				</select>&nbsp;
//			    <select id="down">
//			    <option>
//			        请选择
//			    </option>
//				</select>&nbsp;
//				<input type="text" name="address" id="address"/>		</tr>
//			<tr>
//				<td>可用时段</td>
//				<td><input type="checkbox" name="avitime1" id="avitime1"/>8:00-11:00<input type="checkbox" name="avitime2" name="avitime2" id="avitime2"/>13:00-17:00<input type="checkbox" name="avitime3" id="avitime3"/>18:00-21:00</td>
//			</tr>
//			<tr>
//				<td colspan="2"><input type="submit" value="提交" style="padding-left:20px;padding-right:20px;padding-top:5;padding-bottom:5">
//				<input type="reset" value="重置" style="margin-left:50px; padding-left:20px;padding-right:20px;padding-top:5;padding-bottom:5">
//				</td>
//			</tr>
//		</table>
//		</div>
//	</form>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
 if(request.getParameter("type")!=null&&request.getParameter("type").equals("delete")){
			transportDao dao2=new transportDaoimpl();
			long id=Long.parseLong(request.getParameter("id"));
			dao2.Deletetransport(null,dao2.getConnection(), id);
			request.getRequestDispatcher("transportsearch").forward(request, response);
		}
		else	if(request.getParameter("type")==null||!request.getParameter("type").equals("edit")){
String compname=request.getParameter("compname");
String leader=request.getParameter("leader");
String leadercontract=request.getParameter("leadercontract");
String province=request.getParameter("province");
String[] down=request.getParameterValues("down");
String x1=request.getParameter("avitime1");
String x2=request.getParameter("avitime2");
String x3=request.getParameter("avitime3");
String address=request.getParameter("address");
StringBuilder s=new StringBuilder();
if(x1!=null)s.append("8:00-11:00");
if(x2!=null)s.append("#"+"13:00-17:00");
if(x3!=null)s.append("#"+"18:00-21:00");
String finaladdress=province+"省"+down[0]+"市"+address;
transportBean bean=new transportBean();
transportDao dao=new transportDaoimpl();
bean.setAddress(finaladdress);
bean.setCompname(compname);
bean.setLeadercontract(Long.parseLong(leadercontract));
bean.setDavitime(s.toString());
bean.setLeadername(leader);
Connection con=dao.getConnection();
dao.addtransport(bean, con);
request.getRequestDispatcher("transportsearch").forward(request, response);}
		else{
			String compname=request.getParameter("compname");
			String leader=request.getParameter("leader");
			String leadercontract=request.getParameter("leadercontract");
			String province=request.getParameter("province");
			String[] down=request.getParameterValues("down");
			String x1=request.getParameter("avitime1");
			String x2=request.getParameter("avitime2");
			String x3=request.getParameter("avitime3");
			long id=Long.parseLong(request.getParameter("compname"));
			String address=request.getParameter("address");
			StringBuilder s=new StringBuilder();
			if(x1!=null)s.append("8:00-11:00");
			if(x2!=null)s.append("#"+"13:00-17:00");
			if(x3!=null)s.append("#"+"18:00-21:00");
			String finaladdress=province+"省"+down[0]+"市"+address;
			transportBean bean=new transportBean();
			transportDao dao2=new transportDaoimpl();
			bean.setId(id);
			bean.setAddress(finaladdress);
			bean.setCompname(compname);
			bean.setLeadercontract(Long.parseLong(leadercontract));
			bean.setDavitime(s.toString());
			bean.setLeadername(leader);
			Connection con=dao2.getConnection();
		    dao2.Edittransport(bean, con);
			request.getRequestDispatcher("transportsearch").forward(request, response);			
			
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
