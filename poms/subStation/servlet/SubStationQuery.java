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

public class SubStationQuery extends HttpServlet {
	public SubStationQuery() {
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
		String type = java.net.URLDecoder.decode(req.getParameter("type"),"UTF-8");
		String province = java.net.URLDecoder.decode(req.getParameter("province"),"UTF-8");
		String city = java.net.URLDecoder.decode(req.getParameter("city"),"UTF-8");
		String town = java.net.URLDecoder.decode(req.getParameter("town"),"UTF-8");
		String department = java.net.URLDecoder.decode(req.getParameter("department"),"UTF-8");
		
		SubStation station = new SubStation();
		station.setSubStationProvince(province);
		station.setSubStationCity(city);
		station.setSubStaitonTown(town);
		
		SubStationDAO dao = new SubStationDAO();
		List<SubStation> list = dao.queryStation(station,type,department);
		
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
