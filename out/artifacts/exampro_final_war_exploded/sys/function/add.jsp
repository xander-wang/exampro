<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="webRoot" value="<%=basePath%>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>增加系统功能</title>
	<link rel="stylesheet" type="text/css" href="${webRoot }/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${webRoot }/css/theme.css">
	<script src="${webRoot }/js/jquery.js" type="text/javascript"></script>

</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('新建系统功能');
	</script>

	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="${webRoot }/FunctionServlet?obj=addfunction">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="" class="btn">取消</a>
				</div>

				<div class="well">
					<div class="tab-pane active in">
						<label>
							父功能名称：
						</label>
							<c:if test="${funid == null}">
								<input type="hidden" name="funpid" value="0" />
							</c:if>
							<c:if test="${funid != null}">
								<input type="hidden" name="funpid" value="${funid }" />
							</c:if>
							<c:if test="${funpname == null }">
								<input type="text" name="funpname" value="" readonly="readonly">
							</c:if>
							<c:if test="${funpname != null }">
								<input type="text" name="funpname" value="${funpname }" readonly="readonly">
							</c:if>			
						<label>
							功能名称：
						</label>
						<input type="text" name="funname" maxlength="10" required="required">
						<label>
							功能地址：
						</label>
						<c:if test="${funpname == null }">
							<input type="text" name="funurl" maxlength="100" readonly="readonly"">
						</c:if>	
						<c:if test="${funpname != null }">
							<input type="text" name="funurl" maxlength="100" required="required">
						</c:if>						
						<label>
							功能状态：
						</label>
						<select name="funstate">
							<option value="1">
								正常
							</option>
							<option value="0">
								锁定
							</option>
						</select>
						<div style="color: red">
							${error_addfun }
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>