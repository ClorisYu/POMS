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

public class UpdateServlet extends HttpServlet{
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	String arg=req.getParameter("arg");
	CompanyMessageDAO cmdao=new CompanyMessageDAO();
	List<CompanyMessage> list4 = new ArrayList();
	list4= cmdao.doQuery();
	//System.out.println(list4);
	req.setAttribute("list4", list4);
	if("update".equals(arg)){
		doUpdate(req, resp);	
	}else if("toUpdate".equals(arg)){
		doToUpdate(req, resp);
		}
	//req.getRequestDispatcher("companyMessage.jsp").forward(req, resp);
	}
protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
	resp.setContentType(CONTENT_TYPE);
	req.setCharacterEncoding("UTF-8");
	String type_1=req.getParameter("type_1");
	String type_2=req.getParameter("type_2");
	String type_3=req.getParameter("type_3");
	String type_4=req.getParameter("type_4");
	String type_5=req.getParameter("type_5");
	String content_1=req.getParameter("content_1");
	String content_2=req.getParameter("content_2");
	String content_3=req.getParameter("content_3");
	String content_4=req.getParameter("content_4");
	String content_5=req.getParameter("content_5");
	CompanyMessage cm=new CompanyMessage(type_1,type_2,type_3,type_4,type_5,content_1,content_2,content_3,content_4,content_5);
	CompanyMessageDAO cmdao1=new CompanyMessageDAO();
	cmdao1.doUpdate(cm);
	req.getRequestDispatcher("/companyMessage").forward(req, resp);
	
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