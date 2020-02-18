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
import com.bjqf.exception.RoleException;
import com.bjqf.service.FunRoleService;
import com.bjqf.service.RoleService;
import com.bjqf.util.PageModel;

@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet{
	RoleService roleservice = new RoleService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String obj = request.getParameter("obj");
		if(obj.equals("selectall")){
			selectall(request,response);
		}else if(obj.equals("addrole")) {
			addrole(request,response);
		}else if(obj.equals("selectbyid")) {
			selectbyid(request,response);
		}else if(obj.equals("updatebyid")) {
			updatebyid(request,response);
		}else if(obj.equals("rolefun")) {
			rolefun(request,response);			
		}else if(obj.equals("updaterolefun")) {
			updaterolefun(request,response);			
		}
	}
	
	/**
	 * 	修改角色权限
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updaterolefun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		String id[] = request.getParameterValues("ckrr");
		//转int数组
		int funid[] =new int[id.length];
		for(int i=0;i<id.length;i++){
			funid[i] = Integer.parseInt(id[i]);
	    }
		
		FunRoleService service = new FunRoleService();
		service.updatepro(roleid, funid);
		response.sendRedirect("RoleServlet?obj=selectall&pageNo=1");
	}
	
	/**
	 * 	查询角色权限
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void rolefun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FunRoleService service = new FunRoleService();
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		List<FunRole> list = service.Rolefun(roleid);
		request.setAttribute("list", list);
		request.setAttribute("roleid", roleid);
		request.getRequestDispatcher("sys/role/right.jsp").forward(request, response);
	}

	/**
	 * 根据roleid更新role表数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updatebyid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		String rolename = request.getParameter("rolename");
		int rolestate = Integer.parseInt(request.getParameter("rolestate"));
		HttpSession session = request.getSession(true);
		try {
			
			roleservice.updaterole(rolename, rolestate, roleid);		
			session.removeAttribute("error_updaterole"); //成功就清除错误消息
			response.sendRedirect("RoleServlet?obj=selectall&pageNo=1"); //跳转list
		} catch (RoleException e) {
			session.setAttribute("error_updaterole",e.getMessage()); //添加session错误消息
			response.sendRedirect("sys/role/edit.jsp");
		}
	}
	
	/**
	 * 根据roleid查询role表数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		List<Role> list = roleservice.selectbyid(roleid);
		request.setAttribute("role", list.get(0)); //就一行，直接取了
		request.getRequestDispatcher("sys/role/edit.jsp").forward(request, response);
	}

	/**
	 * 增加角色
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addrole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int rolestate = Integer.parseInt(request.getParameter("rolestate"));
		String rolename = request.getParameter("rolename");
		HttpSession session = request.getSession(true);
		try {
			roleservice.addrole(rolename, rolestate);			
			session.removeAttribute("error_addrole"); //成功就清除错误消息
			response.sendRedirect("RoleServlet?obj=selectall&pageNo=1"); //跳转list
		} catch (RoleException e) {
			session.setAttribute("error_addrole",e.getMessage()); //添加session错误消息
			response.sendRedirect("sys/role/add.jsp");
		}
		
	}

	/**
	 * 分页查询角色
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 3;
		PageModel pagemodel = roleservice.selectall(pageNo, pageSize);
		request.setAttribute("pageModel", pagemodel);
		request.getRequestDispatcher("sys/role/list.jsp").forward(request, response);
	}
	
	
}
