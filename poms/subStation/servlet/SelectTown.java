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

public class SelectTown extends HttpServlet {
	public SelectTown() {
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

		String targetId = java.net.URLDecoder.decode(req.getParameter("id"),
				"UTF-8");
		String targettype = req.getParameter("args");
		String targetProvince = java.net.URLDecoder.decode(req
				.getParameter("province"), "UTF-8");

		String xml_start = "<selects>";
		String xml_end = "</selects>";
		String xml = "";

		// 根据请求参数，设置返回的xml格式的字符串
		// 没有选择

		if (targetId.equalsIgnoreCase("0")) {
		} else {
			SubStationDAO dao = new SubStationDAO();
			List<SubStation> town = dao.queryTown(targetId, targettype,
					targetProvince);
			for (int i = 0; i < town.size() - 1; i++) {
				for (int j = town.size() - 1; j > i; j--) {
					if (town.get(j).getSubStaitonTown().equals(
							town.get(i).getSubStaitonTown())) {
						town.remove(j);
					}
				}
			}

			for (SubStation e : town) {
				xml = "<select><value>0</value><text>县/区</text></select>";
				xml += "<select><value>" + e.getSubStaitonTown()
						+ "</value><text>";
				xml += e.getSubStaitonTown();
				xml += "</text></select>";
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
