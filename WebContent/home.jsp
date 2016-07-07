<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>

	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>User</th>
				<th>Post</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${posts}">
				<tr>
					<td><c:out value="${post.bhuser.useremail}" /></td>
					<td><c:out value="${post.posttext}" /></td>
					<td><fmt:formatDate value="${post.postdate}"
							pattern="yy-MMM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form role="form" action="PostServlet" method="post"
		onsubmit="return validate(this);">
		<div class="form-group">
			<label for="post">Create New Post (100 char):</label>
			<textarea name="posttext" id="posttext" class="form-control" rows="4"
				cols="8" maxlength="100" placeholder="enter your post here...">
		 	</textarea>
			<div id="textarea_feedback"></div>
		</div>
		<div class="form-group">
			<!-- <button type="submit" class="btn btn-default">Submit</button> -->
			<input type="submit" value="Submit" id="submit" /> <input
				type="reset" value="Clear" />
		</div>
	</form>

	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		function validate(form) {
			valid = true;
			return valid;
		}
	</script>

</body>
</html>