<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>       
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><c:out value="${course.name}"></c:out></h1><br>
	
	<p>Instructor:<c:out value="${course.user.firstName}"></c:out></p>
	<p>Signups:<c:out value="${course.attendees.size()}"></c:out></p>
 	
	<table class="table table-dark">
	  <thead>
	    <tr>
	      <th scope="col">Name</th>
	      <th scope="col">Location</th>
	      <th scope="col">Action</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${course.attendees}" var="attendee">
		<tr> 
			<td> ${attendee.firstName}</td>
		    <td> ${attendee.signups}</td>
		    <td>
				<c:choose>
			         <c:when test = "${course.user.id == user_logged.id}">
			            <a href="/courses/${course.id}/edit"></a>
			            <a href="/courses/${course.id}/delete"></a>
			         </c:when>
			         	<c:otherwise>		         
							<c:choose>
						         <c:when test = "${course.attendees.contains(user_logged)}">
						         	<span>joined<a href="/events/${course.id}/remove">Remove</a></span>
						         </c:when>				         
				     	<%-- <c:otherwise>
				        	<a href="/courses/${course.id}/join">Join</a>
				     </c:otherwise> --%>
				     	</c:choose>
				     </c:otherwise>
			   	</c:choose>
		      </td> 
		 </tr>
		</c:forEach>	
	  </tbody>
	</table>
</body>
</html>