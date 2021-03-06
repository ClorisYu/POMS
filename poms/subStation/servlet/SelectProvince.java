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

public class SelectProvince extends HttpServlet {
	public SelectProvince() {
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

		resp.setContentType("text/xml;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");

		String targetId = req.getParameter("id").toString();

		String xml_start = "<selects>";
		String xml_end = "</selects>";
		String xml = "";

		// 根据请求参数，设置返回的xml格式的字符串
		// 没有选择
		if(targetId.equalsIgnoreCase("0")){
		}else{
			if (targetId.equalsIgnoreCase("1")) {
				SubStationDAO dao = new SubStationDAO();
				List<SubStation> province = dao.queryProvince(1);
				xml += "<select><value>0</value><text>省份</text></select>";
				for (SubStation e : province) {
					xml += "<select><value>" + e.getSubStationProvince() + "</value><text>";
					xml += e.getSubStationProvince();
					xml += "</text></select>";
				}
			}
			else if (targetId.equalsIgnoreCase("2")) {
				SubStationDAO dao = new SubStationDAO();
				List<SubStation> province = dao.queryProvince(2);

				for (int i = 0; i < province.size() - 1; i++) {
					for (int j = province.size() - 1; j > i; j--) {
						if (province.get(j).getSubStationProvince().equals(
								province.get(i).getSubStationProvince())) {
							province.remove(j);
						}
					}
				}

				xml += "<select><value>0</value><text>省份</text></select>";
				for (SubStation e : province) {
					xml += "<select><value>" + e.getSubStationProvince() + "</value><text>";
					xml += e.getSubStationProvince();
					xml += "</text></select>";
				}
			}
			String last_xml = xml_start + xml + xml_end;

			PrintWriter out = resp.getWriter();
			out.write(last_xml);
		}
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
