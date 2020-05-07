<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>       
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Dashboard</title>
</head>
<body>
	<a href="/logout">LogOut</a>
	<h1>Courses</h1>
	<table class="table table-dark">
	  <thead>
	    <tr>
	      <th scope="col">Course</th>
	      <th scope="col">Instructor</th>
	      <th scope="col">Signups</th>
	  	  <th scope="col">Action</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${courses}" var="course">
		<tr> 
 		   	<th scope="row"><a href="/courses/${course.id}">${course.name}</a></th>
 		   	<td> ${course.instructor}</td>
 		    <td>${course.signups}</td> 
			<td>
				<c:choose>
			         <c:when test = "${course.user.id == user_logged.id}">
			            <a href="/courses/${course.id}/edit">Edit</a>
			            <a href="/courses/${course.id}/delete">Delete</a>
			         </c:when>
			         	<c:otherwise>		         
							<c:choose>
						         <c:when test = "${course.attendees.contains(user_logged)}">
						         	<span>joined<a href="/events/${course.id}/cancel">Cancel</a></span>
						         </c:when>				         
				     	<c:otherwise>
				        	<a href="/courses/${course.id}/join">Join</a>
				     </c:otherwise>
				     	</c:choose>
				     </c:otherwise>
			   	</c:choose>
		      </td> 
		 </tr>
		</c:forEach>
	  </tbody>
	</table>
	<a href="/courses/new">Add Course</a>
</body>
</html>