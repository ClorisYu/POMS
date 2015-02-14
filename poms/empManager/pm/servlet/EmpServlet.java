package hit.poms.empManager.pm.servlet;

import hit.poms.empManager.pm.dao.EmpDAO;
import hit.poms.empManager.pm.entity.Employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String arg = req.getParameter("arg");
		if("add".equals(arg)){
			doAdd(req,resp);
		}
		if("query".equals(arg)){
			doQuery(req,resp);
		}
		if("toUpdate".equals(arg)){
			doToUpdate(req, resp);
		}
		if("update".equals(arg)){
			doUpdate(req,resp);
		}
		if("delete".equals(arg)){
			doDelete(req,resp);
		}
		if("toSetPost".equals(arg)){
			doToSetPost(req,resp);
		}
		if("setPost".equals(arg)){
			doSetPost(req,resp);
		}
	}

	/**
	 * 职务设置
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doSetPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("emp_id");
		String emp_post = req.getParameter("emp_post");
		Long emp_id = Long.parseLong(id);
		Employee emp = new Employee();
		emp.setEmp_id(emp_id);
		emp.setEmp_post(emp_post);
		EmpDAO dao = new EmpDAO();
		dao.SetPost(emp);
		req.getRequestDispatcher("empServlet?arg=query").forward(req, resp);
	}


	/**
	 * 职务设置前的处理
	 * @param req
	 * @param resp
	 */
	private void doToSetPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		String id = req.getParameter("emp_id");
		Long emp_id  = Long.parseLong(id);
		EmpDAO dao = new EmpDAO();
		Employee emp = dao.doQueryById(emp_id);
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("pm_set_post.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	/**
	 * 添加员工
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//获取输入
		String id_1 = req.getParameter("emp_id");
		String emp_name = req.getParameter("emp_name");
		String emp_gender = req.getParameter("emp_gender");
		String emp_dept_id_1 = req.getParameter("emp_dept_id");
		String emp_post = req.getParameter("emp_post");
		String emp_idnum = req.getParameter("emp_idnum");
		String emp_age_1 = req.getParameter("emp_age");
		String emp_tel = req.getParameter("emp_tel");
		
		//将字符串改为应用时的数据类型
		Long emp_id = 0L;
		Long emp_dept_id = 0L;
		int emp_age = 0;
		if(!"".equals(id_1)){
			emp_id = Long.parseLong(id_1);
		}
		if(!"".equals(emp_dept_id_1)){
			emp_dept_id = Long.parseLong(emp_dept_id_1);
		}
		if(!"".equals(emp_age_1)){
			emp_age = Integer.parseInt(emp_age_1);
		}
		
		Employee emp = new Employee(emp_id,emp_name,emp_gender,emp_dept_id,emp_post,emp_idnum,emp_age,emp_tel);
		EmpDAO dao = new EmpDAO();
		String message = dao.doAdd(emp);
		req.setAttribute("message", message);
		req.getRequestDispatcher("pm_add.jsp").forward(req, resp);
		//System.out.println(message);  ////////////////////////测试
		
	}
	
	/**
	 * 根据员工姓名或编号
	 *查询员工信息
	 * @param req
	 * @param resp
	 */
	private void doQuery(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("emp_id");
		String name = req.getParameter("emp_name");
		long empDeptId=(Long) getServletContext().getAttribute("empDeptId");
		System.out.println("doQuery------------------"+empDeptId);//调试用
		Long emp_id = 0L;
		if(!"".equals(id)&&id!=null){
			emp_id = Long.parseLong(id);
		}
		Employee emp = new Employee();
		emp.setEmp_id(emp_id);
		emp.setEmp_name(name);
		emp.setEmp_dept_id(empDeptId);
		EmpDAO dao = new EmpDAO();
		dao.doQuery(emp);		
		List list = dao.doQuery(emp);
		req.setAttribute("list", list);
		req.getRequestDispatcher("pm_query.jsp").forward(req, resp);
	}
	
	/**
	 * 修改
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String id_1 = req.getParameter("emp_id");
		String emp_name = req.getParameter("emp_name");
		String emp_gender = req.getParameter("emp_gender");
		String emp_dept_id_1 = req.getParameter("emp_dept_id");
		String emp_post = req.getParameter("emp_post");
		String emp_idnum = req.getParameter("emp_idnum");
		String emp_age_1 = req.getParameter("emp_age");
		String emp_tel = req.getParameter("emp_tel");
		
		//将字符串改为应用时的数据类型
		Long emp_id = Long.parseLong(id_1);
		Long emp_dept_id = 0L;
		int emp_age = 0;
		if(!"".equals(emp_dept_id_1)){
			emp_dept_id = Long.parseLong(emp_dept_id_1);
		}
		if(!"".equals(emp_age_1)){
			emp_age = Integer.parseInt(emp_age_1);
		}
		
		Employee emp = new Employee(emp_id,emp_name,emp_gender,emp_dept_id,emp_post,emp_idnum,emp_age,emp_tel);
		EmpDAO dao = new EmpDAO();
		dao.doUpdate(emp);
		req.getRequestDispatcher("/empServlet?arg=query").forward(req, resp);
	}
	
	/**
	 * 前台选择修改
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doToUpdate(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		String id_1 = req.getParameter("emp_id");
		Long id = 0L;
		if(!"".equals(id_1)){
			id = Long.parseLong(id_1);
		}
		EmpDAO dao = new EmpDAO();
		Employee emp = dao.doQueryById(id);
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("pm_update.jsp").forward(req, resp);		
		//System.out.println("---------id_1 = "+id);////////////////////////调试
	}
	
	/**
	 * 删除员工
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id_1 = req.getParameter("empid");
		Long id = Long.parseLong(id_1);
		EmpDAO dao = new EmpDAO();
		System.out.println(id);
		dao.doDelete(id);
		req.getRequestDispatcher("/empServlet?arg=query").forward(req, resp);
	}
}

