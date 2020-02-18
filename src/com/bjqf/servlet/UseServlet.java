package com.bjqf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjqf.entity.FunRole;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.SubjectException;
import com.bjqf.exception.UserException;
import com.bjqf.service.PaperService;
import com.bjqf.service.FunRoleService;
import com.bjqf.service.UserService;
import com.bjqf.util.PageModel;

@WebServlet("/UserServlet")
public class UseServlet extends HttpServlet{
	UserService userService = new UserService();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		String obj = request.getParameter("obj");
		if(obj.equals("login")){
			login(request,response);
		}else if(obj.equals("logout")) {
			logout(request,response);
		}else if(obj.equals("selectall")) {
			selectall(request,response);
		}else if(obj.equals("adduser")) {
			adduser(request,response);
		}else if(obj.equals("selectbyusername")) {
			selectbyusername(request,response);
		}else if(obj.equals("updateuser")) {
			updateuser(request,response);
		}else if(obj.equals("selectrole")) {
			selectrole(request,response);
		}else if(obj.equals("student_select")) {
			student_select(request,response);
		}
	}
	
	
	/**
	 * 学生获取试卷信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void student_select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用PaperService层中的分页查询方法
		PaperService paperservice = new PaperService();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		PageModel pagemodel = paperservice.queryByPage(pageNo, 3); //pageSize=3
		request.setAttribute("pageModel", pagemodel);
		request.getRequestDispatcher("user/index.jsp").forward(request,response);
	}


	/**
	 * 获取角色表，在后台增加用户时供动态选择
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectrole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Role> list_role = userService.selectrole();//获取角色信息
		request.setAttribute("list_role",list_role); //把填完的pagemodel传给前端准备取值
		request.getRequestDispatcher("sys/user/add.jsp").forward(request, response); //跳转到页面			
	}

	/**
	 * 以下均为后台管理模块
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	//修改用户信息
	private void updateuser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String username = request.getParameter("username");
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		String userpwd = request.getParameter("userpwd");
		String usertruename = request.getParameter("usertruename");

		HttpSession session = request.getSession(true);
		try {
			userService.updateuser(userid, roleid, userpwd, usertruename);
			session.removeAttribute("error_updateuser"); //成功就清除错误消息
			response.sendRedirect("UserServlet?obj=selectall&pageNo=1"); //跳转list
		} catch (UserException e) {
			session.setAttribute("error_updateuser",e.getMessage()); //添加session错误消息
			response.sendRedirect("UserServlet?obj=selectbyusername&username="+username);
		}
	}
	//通过用户名查询全部数据
	private void selectbyusername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		List<User> list = userService.selectbyusername(username);
		List<Role> list_role = userService.selectrole();
		
		request.setAttribute("user",list.get(0)); //只有一个结果，把填完的list第一结果传给前端
		request.setAttribute("list_role", list_role);//role表的内容也放入请求域
		request.getRequestDispatcher("sys/user/edit.jsp").forward(request, response); //跳转到页面
		
		
	}
	//增加用户
	private void adduser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int roleid = Integer.parseInt(request.getParameter("roleid")); //这四句获取前台传参
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		String usertruename = request.getParameter("usertruename");
		
		HttpSession session = request.getSession(true);
		try {
			userService.adduser(roleid, username, userpwd, usertruename); //传给service进行封装					
			session.removeAttribute("error_adduser"); //成功就清除错误消息
			response.sendRedirect("UserServlet?obj=selectall&pageNo=1"); //跳转list
		} catch (UserException e) {
			session.setAttribute("error_adduser",e.getMessage()); //添加session错误消息
			response.sendRedirect("sys/user/add.jsp");
		} 
		
	}
	//查询全部用户数据
	private void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int pageNo = Integer.parseInt(request.getParameter("pageNo")); //直接转成int
		int pageSize = 2; //设置每页显示数量	
		PageModel pageModel = userService.selectall(pageNo, pageSize); //取得service层返回的pagemodel对象，至此填入完成
		request.setAttribute("pageModel",pageModel); //把填完的pagemodel传给前端准备取值
		request.getRequestDispatcher("sys/user/list.jsp").forward(request, response); //跳转到页面				
	}
	
	/**
	 * 以下为登录模块
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.sendRedirect("login.jsp");
	}
	
	/**
	 * 	登录方法，按权限显示功能菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		try {
			List<User> list = userService.login(username, userpwd);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", list.get(0));
			if(list.get(0).getRolename().equals("学生")) {
				response.sendRedirect("UserServlet?obj=student_select&pageNo=1");
				
			}
			else{			
				FunRoleService rolefunservice = new FunRoleService();
				//调用角色权限查询的方法进行查询
				List<FunRole> rolefun = rolefunservice.Rolefun(list.get(0).getRoleid());
				request.setAttribute("rolefun", rolefun);
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
		} catch (UserException e) {
			//调用自定义异常
			request.setAttribute("error", e.getMessage());
			//跳转
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		
	}
	
	
}
