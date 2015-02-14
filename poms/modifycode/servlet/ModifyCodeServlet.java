package hit.poms.modifycode.servlet;
/*
 * 修改密码servlet
 */
import hit.poms.modifycode.dao.ModifyCodeDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyCodeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//HttpSession session = req.getSession();
		//String userId = (String)session.getAttribute("UserNo"); //最后模块拼接使用
		//String userId = "200001"; //测试使用
	    String oldPassword = req.getParameter("pwd");
	    String newPassword = req.getParameter("newpwd");
	    ModifyCodeDao dao = new ModifyCodeDao();
	    int value = dao.modifyCode(""+ getServletContext().getAttribute("empid"), oldPassword, newPassword);
	    if(value == 2){
	    	out.println("<script>");
	    	out.println("alert('修改成功！')");
	    	out.println("window.location.href='modify_code.jsp' ");
	    	out.println("</script>");
	    }
	    else if(value == 3){
	    	out.println("<script>");
	    	out.println("alert('您输入的原密码不正确！')");
	    	out.println("history.go(-1)");
	    	out.println("</script>");
	    	System.out.println("script");
	    	
	    }
	    else{
	    	out.println("<script>");
	    	out.println("alert('更新过程中出现了异常，未更新成功！')");
	    	out.println("history.go(-1)");
	    	out.println("</script>");
	    }
		
		
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doGet(req, resp);
    }

}
