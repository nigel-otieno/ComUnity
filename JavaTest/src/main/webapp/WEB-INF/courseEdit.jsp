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
	<h1>UPDATE EVENT</h1>
	<p><form:errors path="event.*"/></p>
		<form:form action="/courses/${course.id}/update" method="post" modelAttribute="course">
		<input type="hidden" name="_method" value="put">
		<form:hidden path="user"/>
		<form:hidden path="attendees"/>
	    <p>
	        <form:label path="name">Course Name:</form:label>
	        <form:textarea path="name"/>
	    </p>
	    <p>
	        <form:label path="signup">Signup Date:</form:label>
	        <form:input path="signup" type="date"/>
	    </p>   
	    <p>
	        <form:label path="location">Location:</form:label>
	        <form:input path="location"/>
	        <form:select path="state">
			<c:forEach items="${states}" var="state">
				<form:option value="${state}">${state}</form:option>
			</c:forEach>
			</form:select>
	    </p>  
	    <input type="submit" value="Update"/>
	   </form:form>
</body>
</html>