package hit.poms.dsz.servlet;

import hit.poms.dao.entity.CompanyMessage;
import hit.poms.dsz.dao.CompanyMessageDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompanyMessageServlet extends HttpServlet{
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	resp.setCharacterEncoding("utf-8");
	String arg=req.getParameter("arg");
	CompanyMessageDAO cmdao=new CompanyMessageDAO();
	List<CompanyMessage> list3 = new ArrayList();
	list3= cmdao.doQuery();
	req.setAttribute("list3", list3);
	if("update".equals(arg)){
		doUpdate(req, resp);	
	}else if("toUpdate".equals(arg)){
		doToUpdate(req, resp);
		}
	req.getRequestDispatcher("companyMessage.jsp").forward(req, resp);
	}
protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
	
	//req.getRequestDispatcher("/userServlet?arg=query").forward(req, resp);
	
}
protected void doToUpdate(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
	req.getRequestDispatcher("companyMessageUpdate.jsp").forward(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}