<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>       

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>CREATE A NEW COURSE</h1>
		<form:form action="/courses" method="post" modelAttribute="course">
	    <p>
	        <form:label path="name">Name: </form:label>
	        <form:errors path="name"/>
	        <form:textarea path="name" placeholder="Name"/>
	    </p>
	    <p>
	        <form:label path="instructor">Instructor: </form:label>
	        <form:errors path="instructor"/>
	        <form:input path="instructor" placeholder="Instructor"/>
	  
	    </p>   
	    <p>
	        <form:label path="signups">Signups</form:label>
	        <form:errors path="signups"/>
	        <form:input path="signups" placeholder="0"/>
	    </p>  
	    <input type="submit" value="Create"/>
	    </form:form>
</body>
</html>