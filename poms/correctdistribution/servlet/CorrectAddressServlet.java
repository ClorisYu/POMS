package hit.poms.correctdistribution.servlet;

import hit.poms.correctdistribution.dao.CorrectAddressDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorrectAddressServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String mode = req.getParameter("mode");
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			if("view".equals(mode)){
				System.out.println("view");
				viewDetail(req,resp);
			}
			else if("modify".equals(mode)){
				System.out.println("modify");
				modifyDetail(req,resp,out);
			}
			else if("search".equals(mode)){
				searchDetail(req,resp);
			}
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doGet(req,resp);
		
		}
		public void viewDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			CorrectAddressDao dao = new CorrectAddressDao();
			List list_3 = dao.correct();
			req.setAttribute("wrong_distribution", list_3);
			req.getRequestDispatcher("correct_distribution.jsp").forward(req, resp);
		}
		public void modifyDetail(HttpServletRequest req, HttpServletResponse resp,PrintWriter out){
			String order_id = req.getParameter("id");
			String newSubstation = req.getParameter("newsub_id");
			CorrectAddressDao dao = new CorrectAddressDao();
			boolean flag = dao.update(order_id,newSubstation);
			if(flag){
				out.println("<script>");
				out.println("alert('配送站变更成功！')");
				out.println("window.location.href='correctAddressServlet?mode=view'");
                out.println("</script>");
			}else{
				out.println("<script>");
				out.println("alert('配送站不存在！')");
				out.println("window.location.href='correctAddressServlet?mode=view'");
                out.println("</script>");
				
			}
			
		}
		public void searchDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			String order_id = req.getParameter("order_id");
			CorrectAddressDao dao = new CorrectAddressDao();
			List list = dao.search(order_id);
			req.setAttribute("wrong_distribution", list);
			req.getRequestDispatcher("correct_distribution.jsp").forward(req, resp);
		}
}
