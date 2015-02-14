package hit.poms.filter;

import hit.poms.dao.entity.Employee;
import hit.poms.login.dao.LoginDAO;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class QuitFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		System.out.println("-------username-------"+username);
		Employee emp = new Employee();
		LoginDAO dao = new LoginDAO();
		Long id = 0L;
		if(!"".equals(username)&&username!=null){
			id = Long.parseLong(username);
		}
		//System.out.println("-------id-----"+id);
		
		emp = dao.doQueryById(id);
		ServletContext context = ((HttpServletRequest)request).getSession().getServletContext();
		Object context1=context.getAttribute("dsz_flag");
		context1=context.getAttribute("dsz_flag");
		//System.out.println(context1);
		if(context1==null){
			context1="0";
		}
		int i=Integer.parseInt((context1).toString());
		if(emp!=null&&! emp.getEmp_post().equals("supermanager")){
			if(emp.getIf_online().equals("1")){
			if(1==i||2==i){
				request.getRequestDispatcher("LogoutServlet").forward(request, response);
			}
			else{
				chain.doFilter(request, response);//本行代码一定要有，表示允许请求继续向后走 
				}
			}if(emp.getIf_online().equals("0")){
				if(1==i||2==i){
					System.out.print("asda");
					request.setAttribute("message", "系统正在维护或已关闭，请稍后登陆！！！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}	
				else{
					chain.doFilter(request, response);//本行代码一定要有，表示允许请求继续向后走 	
				}
			}
			
		}	else{
			chain.doFilter(request, response);//本行代码一定要有，表示允许请求继续向后走 
		}
		//System.out.println("---------iiii-----"+i);
		//System.out.println("---------ifonline-----"+emp.getIf_online());
//		if(!"supermanager".equals(emp.getEmp_post())){
//		if((1==i||2==i)&&emp.getIf_online().equals("1")){
//			System.out.println("---------1-----"+emp.getEmp_post());
//			System.out.println("---------1-----"+emp.getEmp_id());
//		//	response.s
//			request.getRequestDispatcher("LogoutServlet").forward(request, response);
//	}else if((1== i||2==i)&&emp.getIf_online().equals("0")){
//		System.out.println("---------2-----"+emp.getIf_online());
//		request.setAttribute("message", "系统正在维护或已关闭，请稍后登陆！！！");
//		request.getRequestDispatcher("login.jsp").forward(request, response);
//	}}else{
//		System.out.println("---------3-----"+emp.getIf_online());
//				chain.doFilter(request, response);//本行代码一定要有，表示允许请求继续向后走 
//			}
	}

}
