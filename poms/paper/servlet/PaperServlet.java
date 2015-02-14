package hit.poms.paper.servlet;

import hit.poms.paper.dao.PaperDao;
import hit.poms.search.entity.Pub_message;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
///*
// * 报刊操作servlet
// */
//import org.apache.commons.io.FileUtils;
public class PaperServlet extends HttpServlet{

		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			String mode = req.getParameter("mode");
			if("add".equals(mode)){
				
				
				addOperation(req,resp,out);
				//fileUpLoad();
				
			}else if("upload".equals(mode)){
				fileUpLoad(req,resp,out);
			}else if("query".equals(mode)){
				queryOperation(req,resp);
			}else if("edit".equals(mode)){
				editOperation(req,resp);
				
			}else if("delete".equals(mode)){
				deleteOperation(req,resp,out);
			}else if("search".equals(mode)){
				searchOperation(req,resp);
			}else if("queryByDate".equals(mode)){
				queryByDateOperation(req,resp);
			}
			
	
		}
	
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			doGet(req, resp);
		}
		public void addOperation(HttpServletRequest req, HttpServletResponse resp, PrintWriter out){
			String op = req.getParameter("op");//判断是添加还是修改
			String pub_id = req.getParameter("id");//
			String pub_name = req.getParameter("n_name");//
			String pub_time = req.getParameter("date1");//
			int length = req.getParameter("file").length();
			String file = req.getParameter("file");
			String image = null;
			if(op.equals("1")){
			file = file.substring(12,length);
			image = "img"+"\\"+file;//一定要用相对路径
			}else{
			
				file = file.substring(12,length);
				image = "img"+"\\"+file;//一定要用相对路径
					}
			String print_num = req.getParameter("sum");//
			String sell_num = "0";//
			String rest_num = req.getParameter("sum");//
			String temp = req.getParameter("selection");
			String pub_type = null;//
			String pub_price = req.getParameter("pri");
			if(temp.equals("a")){
				pub_type = "城市报纸";
			}else if(temp.equals("b")){
				pub_type = "财经生活";
			}else if(temp.equals("c")){
				pub_type = "文艺娱乐";
			}else if(temp.equals("d")){
				pub_type = "体坛赛事";
			}
			String pub_debook = req.getParameter("order");
			String pub_redirect = "0";
			String pub_month_price = req.getParameter("mo");
			String pub_year_price = req.getParameter("ye");
			String pub_release_rate = req.getParameter("bid");
			String discount1 = req.getParameter("member_dis");
			double d1 = (Double.parseDouble(discount1))/100.0;
			discount1 = d1+"";
			String discount2 = req.getParameter("big_dis");
			double d2 = (Double.parseDouble(discount2))/100.0;
			discount2 = d2+"";
			String delivery_unit = req.getParameter("publishinghouse");
			Pub_message message = new Pub_message();
			message.setDelivery_unit(delivery_unit);
			message.setDiscount1(discount1);
			message.setDiscount2(discount2);
			message.setImage(image);
			message.setPrint_num(print_num);
			message.setPub_debook(pub_debook);
			message.setPub_id(pub_id);
			message.setPub_month_price(pub_month_price);
			message.setPub_name(pub_name);
			message.setPub_price(pub_price);
			message.setPub_redirect(pub_redirect);
			message.setPub_release_rate(pub_release_rate);
			message.setPub_time(pub_time);
			message.setPub_type(pub_type);
			message.setPub_year_price(pub_year_price);
			message.setRest_num(rest_num);
			message.setSell_num(sell_num);
			PaperDao dao = new PaperDao();
			if("1".equals(op)){
				boolean flag = dao.doAdd(message);
				if(flag){
					out.println("<script>");
					out.println("alert('报刊信息添加成功！');");
					out.println("window.location.href='paper_add.jsp';");
					out.println("</script>");
				}else{
					out.println("<script>");
					out.println("alert('报刊信息添加失败，请核对您的信息是否有误！');");
					out.println("history.go(-1);");
					out.println("</script>");
				}
			}else if("2".equals(op)){
				boolean flag = dao.doUpdate(message);
				if(flag){
					out.println("<script>");
					out.println("alert('报刊信息更新成功！');");
					out.println("window.location.href='paperServlet?mode=query';");
					out.println("</script>");
				}else{
					out.println("<script>");
					out.println("alert('报刊信息更新失败，请核对您的信息是否有误！');");
					out.println("window.location.href='paperServlet?mode=query';");
					out.println("</script>");
				}
			}
			
		}
		public void fileUpLoad(HttpServletRequest req, HttpServletResponse resp, PrintWriter out){
			boolean flag = false;
			DiskFileItemFactory f = new DiskFileItemFactory();//磁盘对象
			f.setRepository(new File("d:/a")); //设置临时目录
			f.setSizeThreshold(1024*1024); //1m的缓冲区,文件大于1m则保存到临时目录
			ServletFileUpload upload = new ServletFileUpload(f);//声明解析request的对象
			upload.setHeaderEncoding("UTF-8"); //处理文件名中文
			upload.setFileSizeMax(1024 * 1024 * 5);// 设置每个文件最大为5M
			upload.setSizeMax(1024 * 1024 * 10);// 一共最多能上传10M
			String path = getServletContext().getRealPath("/img");//获取文件要保存的目录
			System.out.println("path"+path);
			try {
				List<FileItem> list = upload.parseRequest(req);// 解析
				for (FileItem ff : list) {
				if (ff.isFormField()) {
				String ds = ff.getString("UTF-8");//处理中文
				System.err.println("说明是:" + ds);
				flag = true; 
				} else {
				String ss = ff.getName();
				ss = ss.substring(ss.lastIndexOf("\\") + 1);//解析文件名
				FileUtils.copyInputStreamToFile(ff.getInputStream(),new File(path + "/" + ss));
                   flag = true; 				
				}
				}
				} catch (Exception e) {
				e.printStackTrace();
				flag = false;
				}
			if(flag){
				out.println("<script>");
				out.println("alert('文件上传成功！');");
				out.println("history.go(-1);");
				out.println("</script>");
				
			}else{
				out.println("<script>");
				out.println("alert('文件上传失败，请稍后再试！');");
				out.println("history.go(-1);");
				out.println("</script>");
				
				
			}


		}
		public void queryOperation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			String id = req.getParameter("paper_id");
			String name = req.getParameter("paper_name");
			PaperDao dao = new PaperDao();
			Pub_message message = new Pub_message();
			message.setPub_id(id);
			message.setPub_name(name);
			List list = dao.doQuery(message);
			req.setAttribute("pub_list", list);
			getServletContext().setAttribute("hehelist", list);
			req.getRequestDispatcher("paper_query.jsp").forward(req, resp);

		}
		public void editOperation(HttpServletRequest req, HttpServletResponse resp){
			String id = req.getParameter("id");
			PaperDao dao = new PaperDao();
			//修改旧信息
			
			
			
			
		}
		public void deleteOperation(HttpServletRequest req, HttpServletResponse resp, PrintWriter out){
			
			String id = req.getParameter("id");
			PaperDao dao = new PaperDao();
			boolean flag = dao.doDelete(id);
			System.out.println("---flag="+flag);
			if(flag){
				out.println("<script>");
				out.println("alert('删除成功！');");
				out.println("window.location.href='paperServlet?mode=query';");
				out.println("</script>");
			}else{
				out.println("<script>");
				out.println("alert('删除失败！');");
				out.println("window.location.href='paperServlet?mode=query';");
				out.println("</script>");
			}
		}
		public void searchOperation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			String id = req.getParameter("id");
			
			//显示旧信息
			PaperDao dao = new PaperDao();
			Pub_message message = dao.doSearch(id);
			req.setAttribute("search_message", message);
			req.getRequestDispatcher("paper_edit.jsp").forward(req, resp);
		}
		public void queryByDateOperation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			String selyear = req.getParameter("year");
			String selmonth = req.getParameter("month");
			String queryStr = null;
			if("".equals(selmonth)){
				queryStr = selyear;
				
			}else{
				queryStr = selyear+"-"+selmonth;//要查询的字符串
			}
			System.out.println("querystr="+queryStr);
			PaperDao dao = new PaperDao();
			List list = dao.doQueryByDate(queryStr);
			req.setAttribute("pub_list", list);
			getServletContext().setAttribute("hehelist", list);
			req.getRequestDispatcher("paper_query.jsp").forward(req, resp);

			
		}
}
