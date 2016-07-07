<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BH Posts</title>
<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>

<body>
<form action="LoginServlet" method="post">
<input type="text" name="email" id="email" value=""></input>
<input type="password" name="password" id="password" value=""></input>
<input type="hidden" name="secretvalue" id="secretvalue" value="LoginForm"></input>
<input type="submit" name="submit" id="submit" value="Login"></input>
</form>
</body>
</html>