<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
    <title>在线答题</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/paper.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/bootstrap.js"></script>
</head>

<body>
    <nav class="navbar navbar-default" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">在线考试系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="" style="color:black;">试题列表</a></li>
                <li class="active"><a href="">查看错题</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user.getUserid() !=null}">
						<li>
							<a style="color:black;">
								${user.getUsertruename() }
							</a>
						</li>
						<li>
							<a href="UserServlet?obj=logout" style="color:black;">注销</a>
						</li>
					</c:when>				
					<c:otherwise>
						<li><a href="login.jsp" style="color:black;">登录</a></li>
					</c:otherwise>	
				</c:choose>							
			</ul>
        </div>
    </nav>

    <main class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">
                    	${pname }
                </h3>
            </div>
            <div class="panel-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#Radio">错题库</a>
                        </h4>
                    </div>
                    <div id="Radio" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ol>
                            	<c:forEach items="${pageModel.dataList }" var="paper">
                            		<div class="subject" data-sid="${paper.getSid() }" data-key="${paper.getSkey() }" data-skey="${paper.getStudentkey() }">
	                                     <li>${paper.getScontent() }</li>
	                                     <ol>
	                                         <li><label data-value="A">${paper.getSa() }</label></li>
	                                         <li><label data-value="B">${paper.getSb() }</label></li>
	                                         <li><label data-value="C">${paper.getSc() }</label></li>
	                                         <li><label data-value="D">${paper.getSd() }</label></li>
	                                     </ol>
	                                 </div>
                            	</c:forEach>
                                 
                            </ol>
                            <div class="pagination pagination-right">
						<ul>
							<li>
								<a>共计：${pageModel.getTotalPage()}页记录</a>
							</li>
							<li>
								<a href="StudentPaperServlet?obj=selectAll&userid=${user.getUserid() }&pname=${pname}&spid=${spid}&pageNo=1">首页</a>
							</li>
							<li>
								<a href="StudentPaperServlet?obj=selectAll&userid=${user.getUserid() }&pname=${pname}&spid=${spid}&pageNo=${pageModel.getPrePage() }">上一页</a>
							</li>						
							<li>
								<a href="StudentPaperServlet?obj=selectAll&userid=${user.getUserid() }&pname=${pname}&spid=${spid}&pageNo=${pageModel.getNextPage() }">下一页</a>
							</li>
							<li>
								<a href="StudentPaperServlet?obj=selectAll&userid=${user.getUserid() }&pname=${pname}&spid=${spid}&pageNo=${pageModel.getTotalPage() }">末页</a>
							</li>
						</ul>
					</div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </main>
    <script>
    $(function(ev) {
        var len = $('.subject').length;
        $('.subject').each(function(index){
        var i = index
        var self = $(this)
        
        self.find('label').each(function(){
        	var label = $(this)
        	if(self.data('key')==label.data('value')){
        		label.parent().addClass('correct')
        	}
        	if(self.data('skey')==label.data('value')){
        		label.parent().addClass('error')
        	}
        })
        
        })

    })
    </script>
</body>
</html>