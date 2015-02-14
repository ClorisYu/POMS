package hit.poms.subStation.servlet;

import hit.poms.subStation.dao.SubStationDAO;
import hit.poms.subStation.entity.SubStation;

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

public class ModificationDepartment extends HttpServlet {
	public ModificationDepartment() {
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
		String arg = req.getParameter("arg");
		if(arg.equalsIgnoreCase("forword")){
			doForword(req, resp);
		}else if(arg.equalsIgnoreCase("modification")){
			doModification(req, resp);
		}
	}
	
	protected void doForword(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String ID = req.getParameter("id");
		ID = new String(ID.getBytes("ISO-8859-1"), "UTF-8");   
		String flag = req.getParameter("flag");
		flag = new String(flag.getBytes("ISO-8859-1"), "UTF-8");   
		String subStationName = req.getParameter("name");
		subStationName = new String(subStationName.getBytes("ISO-8859-1"), "UTF-8");   
		String subStationLeaderName = req.getParameter("leader");
		subStationLeaderName = new String(subStationLeaderName.getBytes("ISO-8859-1"), "UTF-8");   
		String subStationLeaderTel = req.getParameter("tel");
		subStationLeaderTel = new String(subStationLeaderTel.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_hr_infor = req.getParameter("hr");
		subStation_hr_infor = new String(subStation_hr_infor.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_pr_infor = req.getParameter("pr");
		subStation_pr_infor = new String(subStation_pr_infor.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_bj_infor = req.getParameter("bj");
		subStation_bj_infor = new String(subStation_bj_infor.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_jd_infor = req.getParameter("jd");
		subStation_jd_infor = new String(subStation_jd_infor.getBytes("ISO-8859-1"), "UTF-8");   
		
		if(subStation_hr_infor.equalsIgnoreCase("")){
			subStation_hr_infor=null;
		}else if(subStation_pr_infor.equalsIgnoreCase("")){
			subStation_pr_infor=null;
		}else if(subStation_bj_infor.equalsIgnoreCase("")){
			subStation_bj_infor=null;
		}else if(subStation_jd_infor.equalsIgnoreCase("")){
			subStation_jd_infor=null;
		}
		
		SubStation temp = new SubStation();
		
		temp.setFlag(flag);
		temp.setSubStationID(Long.parseLong(ID));
		temp.setSubStationName(subStationName);
		temp.setSubStationLeaderName(subStationLeaderName);
		temp.setSubStationLeaderTel(subStationLeaderTel);
		temp.setSubStation_hr_infor(subStation_hr_infor);
		temp.setSubStation_pr_infor(subStation_pr_infor);
		temp.setSubStation_bj_infor(subStation_bj_infor);
		temp.setSubStation_jd_infor(subStation_jd_infor);
		
		req.setAttribute("temp", temp);
		req.getRequestDispatcher("zhongxin_setting_department_edit.jsp").forward(req, resp);
	}
	
	protected void doModification(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String flag = req.getParameter("flag");
		flag = new String(flag.getBytes("ISO-8859-1"), "UTF-8");   
		String subStationID = req.getParameter("id");
		subStationID = new String(subStationID.getBytes("ISO-8859-1"), "UTF-8");
		String subStationName = req.getParameter("name");
		subStationName = new String(subStationName.getBytes("ISO-8859-1"), "UTF-8");
		String subStationLeader = req.getParameter("leader");
		subStationLeader = new String(subStationLeader.getBytes("ISO-8859-1"), "UTF-8");
		String subStationLeaderTel = req.getParameter("tel");
		subStationLeaderTel = new String(subStationLeaderTel.getBytes("ISO-8859-1"), "UTF-8");
		String subStation_hr_infor = req.getParameter("hr");
		subStation_hr_infor = new String(subStation_hr_infor.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_pr_infor = req.getParameter("pr");
		subStation_pr_infor = new String(subStation_pr_infor.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_bj_infor = req.getParameter("bj");
		subStation_bj_infor = new String(subStation_bj_infor.getBytes("ISO-8859-1"), "UTF-8");   
		String subStation_jd_infor = req.getParameter("jd");
		subStation_jd_infor = new String(subStation_jd_infor.getBytes("ISO-8859-1"), "UTF-8");
		String infor = req.getParameter("infor");
		
		SubStation temp = new SubStation();
		temp.setFlag(flag);
		temp.setSubStationID(Long.parseLong(subStationID));
		temp.setSubStationName(subStationName);
		temp.setSubStationLeaderName(subStationLeader);
		temp.setSubStationLeaderTel(subStationLeaderTel);
		if(flag.equalsIgnoreCase("1")){
			temp.setSubStation_hr_infor(infor);
		}else if(flag.equalsIgnoreCase("2")){
			temp.setSubStation_pr_infor(infor);
		}else if(flag.equalsIgnoreCase("3")){
			temp.setSubStation_bj_infor(infor);
		}else if(flag.equalsIgnoreCase("4")){
			temp.setSubStation_jd_infor(infor);
		}
		
		SubStationDAO dao = new SubStationDAO();
		dao.modification(temp);
		
		List<SubStation> list = new ArrayList();
		list.add(temp);
		
		//将list存储到request中 
		req.setAttribute("list", list);
		
		//转发到查询结果页面
		req.getRequestDispatcher("zhongxin_setting_department.jsp").forward(req, resp);
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
