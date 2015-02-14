package hit.poms.login.servlet;

import hit.poms.dao.entity.Employee;
import hit.poms.login.dao.LoginDAO;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		ServletContext context = getServletContext();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String flag = req.getParameter("remenber_pw");////////////////////////记住密码的复选框
		if(flag==null) flag = "0";
		Long id = 0L;
		if(!"".equals(username)||username!=null){
			id = Long.parseLong(username);
			//System.out.print(name);

		if(!"".equals(username)||username!=null){
			id = Long.parseLong(username);

		}
		Employee emp = new Employee();
		LoginDAO dao = new LoginDAO();
		emp = dao.login(id, password);
		if(emp!=null){
			context.setAttribute("empName", emp.getEmp_name());	///////////////将员工姓名封装到全局变量里
			context.setAttribute("empPost", emp.getEmp_post());	///////////////将员工权限封装到全局变量里
		//	if()
		//		context.setAttribute("mysuper", 1);
			//else{
		//		context.removeAttribute("mysuper");
		//	}
			context.setAttribute("empDeptId", emp.getEmp_dept_id());///////////将部门编号封装到全局变量里
			System.out.print("asd"+context.getAttribute("empDeptId"));
			context.setAttribute("empid", emp.getEmp_id());///////////将员工编号封装到全局变量里
			/*-------------设置cookie-------------*/
			Cookie newCookie = new Cookie(username, username+" "+password+" "+flag);
			if(flag.equals("1")){
				newCookie.setMaxAge(24*60*60);
			}else{
				newCookie.setMaxAge(0);
			}
			resp.addCookie(newCookie);
			
			req.getRequestDispatcher("index.jsp").forward(req, resp);///////////////登陆成功跳转到主页
		}else{
			req.setAttribute("message", "您输入的用户名和密码错误，请重新输入！");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
