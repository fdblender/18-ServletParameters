<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bullhorn</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"> <img alt="Brand"
						src="images/bullhornlogo50x50.png">
					</a>
				</div>
			</div>
			</nav>
			<!--<a class="navbar-brand" href="#">Bullhorn</a>-->
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
			
				<li class="active"><a href="HomeServlet">Home <span
						class="sr-only">(current)</span></a></li>
						
				<li class="active"><a href="Newsfeed">Newsfeed <span
						class="sr-only">(current)</span></a></li>
						
				<!-- display the gravatar and the user email -->		
				<% if (session.getAttribute("user") != null) { %>
					<li><a href="ProfileServlet?useremail=${user.useremail}&action=viewprofile"><img alt="${user.username}" src="${gravatarURL}"/>&nbsp;${user.username}</a></li>
 				<% } %>				
				
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">User Actions<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="Newsfeed?userid=${user.bhuserid}">Show My Posts</a></li>
						<li><a href="profile.jsp">Edit My Profile</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="feedback.jsp">Feedback</a></li>
						<li role="separator" class="divider"></li>
					</ul></li>
					
				<li>
					<form class="navbar-form navbar-left" role="search" action="Newsfeed" method="get">
						<div class="form-group">
							<input name="searchtext" id="searchtext" type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</li>
			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
</body>
</html>