<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>系统功能列表</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<div class="container-fluid">
		<div class="row-fluid">
			<form class="form-inline" method="post"
				action="">
				<input class="input-xlarge" placeholder="功能名称..." name="sname"
					type="text" value="">
				<input class="btn icon-search" type="submit" value="查询" />
				<a class="btn btn-primary" href="sys/function/add.jsp">
					<i class="icon-plus"></i> 新建顶层功能
				</a>
			</form>

			<div class="well">
				<table class="table">
					<thead>
						<tr>
							<th>
								父功能
							</th>
							<th>
								功能名称
							</th>
							<th>
								功能地址
							</th>
							<th>
								功能状态
							</th>
							<th style="width: 90px;">
								编辑
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageModel.dataList}" var="fun">
							<tr>
								<td>
									${fun.getParentname() }
								</td>
								<td>
									${fun.getFunname() }
								</td>
								<td>
									${fun.getFunurl() }
								</td>
								<td>
									<c:if test="${fun.getFunstate() == 1 }">正常</c:if>	
									<c:if test="${fun.getFunstate() == 0 }">禁用</c:if>			
								</td>
								<td>
									<a href="FunctionServlet?obj=upfundisplay&funid=${fun.getFunid() }">编辑</a>
									&ensp;
									<c:if test="${fun.getParentname() == null }">
										<a href="FunctionServlet?obj=addfundisplay&funid=${fun.getFunid() }&funpname=${fun.getFunname() }">子功能</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination pagination-right">
					<ul>
						<li>
							<a>共计：${pageModel.getTotalPage()}页记录</a>
						</li>
						<li>
							<a href="FunctionServlet?obj=selectall&pageNo=1">首页</a>
						</li>
						<li>
							<a href="FunctionServlet?obj=selectall&pageNo=${pageModel.getPrePage() }">上一页</a>
						</li>						
						
						<li>
							<a href="FunctionServlet?obj=selectall&pageNo=${pageModel.getNextPage() }">下一页</a>
						</li>
						<li>
							<a href="FunctionServlet?obj=selectall&pageNo=${pageModel.getTotalPage() }">尾页</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>