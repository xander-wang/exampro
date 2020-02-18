package com.bjqf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.service.PaperService;
import com.bjqf.util.PageModel;

@WebServlet("/PaperServlet")
public class PaperServlet extends HttpServlet{
	PaperService paperservice =new PaperService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String obj = request.getParameter("obj");
		if(obj.equals("selectall")) {
			selectall(request,response);			
		}else if(obj.equals("addpaper")){
			addpaper(request,response);
		}else if(obj.equals("selectsub")){
			selectsub(request,response);
		}else if(obj.equals("studentpaper")){
			studentpaper(request,response);
		}
	}
	
	/**
	 * 	学生开始答题，显示试题信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void studentpaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("pname");
		int count = Integer.parseInt(request.getParameter("count"));
		List<Subject> list = paperservice.selectSub(pname);
		request.setAttribute("list", list);
		request.setAttribute("pname", pname);
		request.setAttribute("count", count);
		request.getRequestDispatcher("user/paper/paper.jsp").forward(request, response);
		
	}

	/**
	 * 	管理员查看试卷具体的试题信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectsub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("pname");
		List<Subject> list = paperservice.selectSub(pname);
		request.setAttribute("list", list);
		//跳转界面
		request.getRequestDispatcher("paper/subjects.jsp").forward(request, response);
	}

	//增加试卷
	private void addpaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("pname");
		int num = Integer.parseInt(request.getParameter("num"));	
		PaperService paperservice = new PaperService();
		HttpSession session = request.getSession();
		try {
			paperservice.addPaper(pname, num);
			//清除session数据
			session.removeAttribute("error_add_paper");
			//跳转
			request.getRequestDispatcher("PaperServlet?obj=selectall&pageNo=1").forward(request, response);
		} catch (PaperException e) {
			//捕获异常，返回给前端			
			session.setAttribute("error_add_paper",e.getMessage());
			response.sendRedirect("paper/add.jsp");
		}
		
	}

	//分页查询试卷
	private void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先获取界面层传输过来的pageNo值
		int pageNo = Integer.parseInt(request.getParameter("pageNo")); //需要的是int型的，得转换,此处需要使用包装类
		//设置每一页显示2条数据
		int pageSize = 3;
		PageModel pageModel = paperservice.queryByPage(pageNo, pageSize);
		//存储list在request域
		request.setAttribute("pageModel", pageModel);
		//跳转界面
		request.getRequestDispatcher("paper/list.jsp").forward(request, response);
	}
	
	
	
}
