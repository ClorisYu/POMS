package hit.poms.mail.servlet;

import hit.poms.mail.dao.InformMessageDao;
import hit.poms.mail.entity.InformMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InformMessageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String mode = req.getParameter("mode");
		System.out.println("----mode="+mode);
		if("viewFrom".equals(mode)){
			viewFromMessage(req,resp);
		}
        else if("viewTo".equals(mode)){
			viewToMessage(req,resp);
		}
        else if("viewFromByDate".equals(mode)){
        	viewFromMessageByDate(req,resp);
        	
        }
        else if("viewToByDate".equals(mode)){
        	
        }
		else if("deleteFrom".equals(mode)){
			deleteFromMessage(req,resp,out);
		}
		else if("deleteTo".equals(mode)){
			deleteToMessage(req,resp,out);
		}
		else if("detailFrom".equals(mode)){
			detailFromMessage(req,resp);
		}
		else if("detailTo".equals(mode)){
			detailToMessage(req,resp);
		}
		else if("mark".equals(mode)){
			markMessage(req,resp,out);
		}
		else if("send".equals(mode)){
			sendMessage(req,resp,out);
		}
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
	public void viewFromMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html; charset=utf-8");
		ServletContext context = getServletContext();
		InformMessageDao dao = new InformMessageDao();
		List list = dao.doFromQuery(""+ context.getAttribute("empid"));
		System.out.print(context.getAttribute("empid"));
		req.setAttribute("messagelist", list);
	    req.getRequestDispatcher("receive_list.jsp").forward(req, resp);
	}
	public void viewFromMessageByDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String date = req.getParameter("endDate");//提取前台提交的指定日期
		InformMessageDao dao = new InformMessageDao();
		List list2 = dao.doFromQueryByDate(""+ getServletContext().getAttribute("empid"),date);
		req.setAttribute("messagelist", list2);
		req.getRequestDispatcher("receive_list.jsp").forward(req, resp);
		
	}
    public void viewToMessageByDate(){
		
	}
	public void viewToMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//HttpSession session = req.getSession();
		//String get_emp_id = (String)session.getAttribute("User");
		//String send_emp_id = "100000";
		InformMessageDao dao = new InformMessageDao();
		List list1 = dao.doToQuery(""+  getServletContext().getAttribute("empid"));
		req.setAttribute("messagelist1", list1);
		req.getRequestDispatcher("send_list.jsp").forward(req, resp);
	}
	public void deleteFromMessage(HttpServletRequest req, HttpServletResponse resp,PrintWriter out) throws ServletException, IOException{
		String id = req.getParameter("id");
		InformMessageDao dao = new InformMessageDao();
		boolean flag = dao.doDelete(id);
		
		if(flag){
			out.println("<script>");
			out.println("alert('删除成功！')");
			out.println("</script>");
		}
		req.getRequestDispatcher("informMessageServlet?mode=viewFrom").forward(req, resp);
		
		
	}
	public void deleteToMessage(HttpServletRequest req, HttpServletResponse resp,PrintWriter out) throws ServletException, IOException{
		String id = req.getParameter("id");
		InformMessageDao dao = new InformMessageDao();
		boolean flag = dao.doDelete(id);
		
		if(flag){
			out.println("<script>");
			out.println("alert('删除成功！')");
			out.println("</script>");
		}
		req.getRequestDispatcher("informMessageServlet?mode=viewTo").forward(req, resp);
		
		
	}
	public void detailFromMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		InformMessage im = new InformMessage();
		InformMessageDao dao = new InformMessageDao();
		System.out.println("**********");
		im = dao.doDetail(id);
		req.setAttribute("detailmessage",im);
		req.getRequestDispatcher("receive.jsp?id="+id).forward(req, resp);
	}
	public void detailToMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		InformMessage im = new InformMessage();
		InformMessageDao dao = new InformMessageDao();
		System.out.println("**********");
		im = dao.doDetail(id);
		req.setAttribute("detailmessage1",im);
		req.getRequestDispatcher("send_detail.jsp?id="+id).forward(req, resp);
	}
	public void markMessage(HttpServletRequest req, HttpServletResponse resp,PrintWriter out) throws ServletException, IOException{
		String inform_id = req.getParameter("inform_id");
		InformMessageDao dao = new InformMessageDao();
		boolean flag = dao.doMark(inform_id);
		if(flag){
            out.println("<script>");
			out.println("alert('标记成功！');");
			out.println("window.location.href='informMessageServlet?mode=detailFrom&id="+inform_id+"'");
			out.println("</script>");

		}else{
			out.println("<script>");
			out.println("alert('标记失败！')");
			out.println("window.location.href='informMessageServlet?mode=detailFrom&id="+inform_id+"'");
			out.println("</script>");
		}
	}
	public void sendMessage(HttpServletRequest req, HttpServletResponse resp,PrintWriter out){
		//HttpSession session = req.getSession();
		//String send_emp_id = (String)session.getAttribute("User");
		String get_emp_id = req.getParameter("send_text_name");
	//	String send_emp_id = "100000";
		boolean flag = judgeGet_emp_id(get_emp_id);
		if(flag){
			String content = req.getParameter("send_text_area");
			InformMessageDao dao = new InformMessageDao();
			boolean flag1 = dao.doInsert(""+ getServletContext().getAttribute("empid"),get_emp_id, content);
			if(flag1){
				out.println("<script>");
				out.println("alert('发送成功！')");
				out.println("window.location.href='send.jsp'");
                out.println("</script>");
			}
		}else{
			out.println("<script>");
			out.println("alert('您要发送的用户不存在，请仔细核对ID！')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
		
	}
	public boolean judgeGet_emp_id(String get_emp_id){
		boolean flag = false;
		InformMessageDao dao = new InformMessageDao();
		flag = dao.doSearch(get_emp_id);
		return flag;
	}

}
