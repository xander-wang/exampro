package com.bjqf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjqf.entity.Function;
import com.bjqf.exception.FunctionException;
import com.bjqf.exception.RoleException;
import com.bjqf.service.FunctionService;
import com.bjqf.util.PageModel;

@WebServlet("/FunctionServlet")
public class FunctionServlet extends HttpServlet{
	FunctionService functionservice = new FunctionService();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String obj = request.getParameter("obj");
		if(obj.equals("selectall")) {
			selectall(request,response);			
		}else if(obj.equals("addfunction")) {
			addfunction(request,response);			
		}else if(obj.equals("addfundisplay")) {
			addfundisplay(request,response);			
		}else if(obj.equals("upfundisplay")) {
			upfundisplay(request,response);			
		}else if(obj.equals("updatefun")) {
			updatefun(request,response);			
		}
	}
	
	/**
	 * 	编辑功能
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updatefun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int funid = Integer.parseInt(request.getParameter("funid"));
		int funpid = Integer.parseInt(request.getParameter("funpid"));
		String funname = request.getParameter("funname");
		String funurl = request.getParameter("funurl");
		int funstate = Integer.parseInt(request.getParameter("funstate"));
		HttpSession session = request.getSession(true);
		try {
			functionservice.updatefunction(funname, funurl, funstate, funpid,funid);
			session.removeAttribute("error_upfun"); //成功就清除错误消息
			response.sendRedirect("FunctionServlet?obj=selectall&pageNo=1"); //跳转list
		} catch (FunctionException e) {
			session.setAttribute("error_upfun",e.getMessage()); //添加session错误消息
			response.sendRedirect("FunctionServlet?obj=upfundisplay&funid="+funid);
		}
	}

	/**
	 * 	编辑function时数据回显
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void upfundisplay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int funid = Integer.parseInt(request.getParameter("funid"));
		List<Function> list = functionservice.updatefun_display(funid);
		request.setAttribute("fun", list.get(0)); //就一行直接取
		request.getRequestDispatcher("sys/function/edit.jsp").forward(request, response);
	}

	/**
	 * 	增加子功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addfundisplay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int funid = Integer.parseInt(request.getParameter("funid"));
		String funpname = request.getParameter("funpname");
		request.setAttribute("funid",funid);
		request.setAttribute("funpname",funpname);
		request.getRequestDispatcher("sys/function/add.jsp").forward(request, response);
		
	}


	/**
	 * 	增加顶层功能
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addfunction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int funpid = Integer.parseInt(request.getParameter("funpid"));
		String funname = request.getParameter("funname");
		String funurl = request.getParameter("funurl");
		int funstate = Integer.parseInt(request.getParameter("funstate"));
		HttpSession session = request.getSession(true);
		try {					
			functionservice.addfunction(funname, funurl, funstate, funpid);		
			session.removeAttribute("error_addfun"); //成功就清除错误消息
			response.sendRedirect("FunctionServlet?obj=selectall&pageNo=1"); //跳转list
		} catch (FunctionException e) {
			session.setAttribute("error_addfun",e.getMessage()); //添加session错误消息
			response.sendRedirect("sys/function/add.jsp");
		}
	}
	
	
	
	/**
	 * 	分页查询全部功能
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先获取界面层传输过来的pageNo值
		int pageNo = Integer.parseInt(request.getParameter("pageNo")); //需要的是int型的，得转换,此处需要使用包装类
		//设置每一页显示2条数据
		int pageSize = 4;	
		PageModel pageModel = functionservice.queryByPage(pageNo, pageSize); //返回PageModel对象
		
		//对象返回给前端
		request.setAttribute("pageModel",pageModel);
		//跳转界面
		request.getRequestDispatcher("sys/function/list.jsp").forward(request, response);
		
	}
	

}
