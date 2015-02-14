package hit.poms.subStation.servlet;

import hit.poms.subStation.dao.SubStationDAO;
import hit.poms.subStation.entity.SubStation;
import hit.poms.subStation.entity.SubStationEmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpEdit extends HttpServlet {
	public EmpEdit() {
		super();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String ID = req.getParameter("dept_id");
		ID = new String(ID.getBytes("ISO-8859-1"), "UTF-8");   
		String flag = req.getParameter("flag");
		flag = new String(flag.getBytes("ISO-8859-1"), "UTF-8");   
		String subStationName = req.getParameter("name");
		subStationName = new String(subStationName.getBytes("ISO-8859-1"), "UTF-8");   
		
		SubStation station = new SubStation();
		station.setFlag(flag);
		station.setSubStationID(Long.parseLong(ID));
		station.setSubStationName(subStationName);
		
		SubStationDAO dao = new SubStationDAO();
		List<SubStationEmp> Leader = dao.queryLeader(station);
		List<SubStationEmp> Emp = dao.queryEmp(station);
		
		//将list存储到request中 
		req.setAttribute("leader", Leader);
		req.setAttribute("emp", Emp);
		req.setAttribute("station", station);
//		System.out.println(Leader);
//		System.out.println(Emp);
//		System.out.println(station);
		//转发到查询结果页面
		req.getRequestDispatcher("zhongxin_setting_department_emp.jsp").forward(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
}
