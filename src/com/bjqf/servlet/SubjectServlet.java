package com.bjqf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjqf.entity.Subject;
import com.bjqf.exception.SubjectException;
import com.bjqf.service.SubjectService;
import com.bjqf.util.PageModel;

@WebServlet("/SubjectServlet")
public class SubjectServlet extends HttpServlet{
	SubjectService SubjectService =new SubjectService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String obj = request.getParameter("obj");
		if(obj.equals("selectall")) {
			selectall(request,response);			
		}else if(obj.equals("addSubject")) {
			addSubject(request,response);
		}else if(obj.equals("selectBySid")) {
			selectBySid(request,response);
		}else if(obj.equals("updateSubject")) {
			updateSubject(request,response);
		}
	}
	/*
	 * 数据修改方法
	 */
	private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取界面值
				int sid = Integer.parseInt(request.getParameter("sid"));
				String scontent = request.getParameter("scontent");
				String sa = request.getParameter("sa");
				String sb = request.getParameter("sb");
				String sc = request.getParameter("sc");
				String sd = request.getParameter("sd");
				String skey = request.getParameter("skey");
				String sstate = request.getParameter("sstate");
				//但是sstate是布尔，得包装转换
				boolean state = Boolean.parseBoolean(sstate);
				
				
				//创建subject对象，并赋值
				Subject subject = new Subject();
				subject.setSid(sid);
				subject.setScontent(scontent);
				subject.setSa(sa);
				subject.setSb(sb);
				subject.setSc(sc);
				subject.setSd(sd);
				subject.setSkey(skey);
				subject.setSstate(state);  //这里把布尔放进去
				HttpSession session = request.getSession(true);
				//调用service方法
				try {
					SubjectService.updateSubject(subject);
					//成功清除错误消息
					session.removeAttribute("error");
					//跳转到显示页面
					response.sendRedirect("SubjectServlet?obj=selectall&pageNo=1");
				} catch (SubjectException e) {
					//调用自定义异常,保存到session中
					
					session.setAttribute("error",e.getMessage());
					//跳转到servlet才有数据
					response.sendRedirect("SubjectServlet?obj=selectBySid&sid="+sid);
				}
	}

	/**
	 * 数据回显的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void selectBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取sid
		int sid = Integer.parseInt(request.getParameter("sid"));
		/*
		 * 调用service层方法
		 * list集合中有且只有一条数据
		 */
		List<Subject> list = SubjectService.selectBySid(sid);
		//将list集合中的subject对象存储在request中
		request.setAttribute("subject", list.get(0));
		//界面跳转
		request.getRequestDispatcher("sys/subject/edit.jsp").forward(request, response);
	}


	/*
	 * 添加题目方法
	 */
	private void addSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取界面值
		String scontent = request.getParameter("scontent");
		String sa = request.getParameter("sa");
		String sb = request.getParameter("sb");
		String sc = request.getParameter("sc");
		String sd = request.getParameter("sd");
		String skey = request.getParameter("skey");
		String sstate = request.getParameter("sstate");
		//但是sstate是布尔，得转换
		boolean state = true;
		if(sstate.equals("1")) {
			state = true;
		}else if(sstate.equals("0")) {
			state = false;
		}
		//创建subject对象，并赋值
		Subject subject = new Subject();
		subject.setScontent(scontent);
		subject.setSa(sa);
		subject.setSb(sb);
		subject.setSc(sc);
		subject.setSd(sd);
		subject.setSkey(skey);
		subject.setSstate(state);  //这里把布尔放进去
		//调用service方法
		try {
			SubjectService.addSubject(subject);
			//跳转到第一页数据，要写servlet的页面才有数据
			response.sendRedirect("SubjectServlet?obj=selectall&pageNo=1");
		} catch (SubjectException e) {
			//调用自定义异常,保存到session中
			HttpSession session = request.getSession(true);
			session.setAttribute("error",e.getMessage());
			//跳转
			response.sendRedirect("sys/subject/add.jsp");;
		}
	}

	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先获取界面层传输过来的pageNo值
		int pageNo = Integer.parseInt(request.getParameter("pageNo")); //需要的是int型的，得转换,此处需要使用包装类
		//设置每一页显示2条数据
		int pageSize = 2;
		
		PageModel pageModel = SubjectService.queryByPage(pageNo, pageSize); //返回PageModel对象
		
		//对象返回给前端
		request.setAttribute("pageModel",pageModel);
		//跳转界面
		request.getRequestDispatcher("sys/subject/list.jsp").forward(request, response);
	}
	
	
	
}
