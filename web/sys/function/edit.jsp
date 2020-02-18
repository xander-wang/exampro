<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑系统功能</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('编辑系统功能');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="FunctionServlet?obj=updatefun">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="" class="btn">取消</a>

				</div>

				<div class="well">
					<div class="tab-pane active in">
						<label>
							父功能名称：
						</label>
						<input type="hidden" name="funid" value="${fun.getFunid() }" />
						<input type="hidden" name="funpid" value="${fun.getFunpid() }" />
						<input type="text" name="funpname" value="${fun.getParentname() }"
							readonly="readonly">
						<label>
							功能名称：
						</label>
						<input type="text" name="funname" maxlength="10"
							value="${fun.getFunname() }">
						<label>
							功能地址：
						</label>
						<c:if test="${fun.getParentname() == null }">
							<input type="text" name="funurl" maxlength="100" readonly="readonly"">
						</c:if>	
						<c:if test="${fun.getParentname() != null }">
							<input type="text" name="funurl" maxlength="100" required="required" value="${fun.getFunurl() }">
						</c:if>	
						<label>
							功能状态：
						</label>
						<select name="funstate">
							<c:if test="${fun.getFunstate() == 1 }">
								<option value="1" selected="selected">正常</option>
								<option value="0">锁定</option>	
							</c:if>	
							<c:if test="${fun.getFunstate() == 0 }">
								<option value="0" selected="selected">锁定</option>
								<option value="1">正常</option>	
							</c:if>		
						</select>
						<div style="color: red">
							${error_upfun }
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>