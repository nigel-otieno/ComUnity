<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/eventcreator.css">
<script type="text/javascript" src="js/timeline.js"></script>
<title>Create Event</title>
</head>
<body>

	<!-- NAVIGATION -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExample08" aria-controls="navbarsExample08"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse justify-content-md-center"
			id="navbarsExample08">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="/timeline">Home <span class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link"
					href="/profile/${user_id}">Profile</a></li>
				<li class="nav-item"><a class="nav-link" href="/create">Create
						Event</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="dropdown08"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>
					<div class="dropdown-menu" style="float: right"
						aria-labelledby="dropdown08">
						<a class="dropdown-item" href="/user/${user_id}/edit">Edit
							Profile</a> <a class="dropdown-item" href="/contactus">Contact Us</a> <a
							class="dropdown-item" href="/terms_conditions">Term &
							Conditions</a> <a class="dropdown-item" href="/logout">Logout</a>
					</div></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="row">

			<div class="col-md-10 col-md-offset-2">

				<h1>Create Event</h1>

				<form:form action="/event/create" method="post"
					modelAttribute="event" enctype="multipart/form-data">

					<div class="form-group">
						<label for="name">Title <span class="require">*</span></label> <input
							type="text" class="form-control" name="name" />
					</div>

					<div class="form-group">
						<label for="city">City <span class="require">*</span></label> <input
							type="text" class="form-control" name="city" />
					</div>

					<div class="form-group">
						<label for="zip_code">Zip Code <span class="require">*</span></label>
						<input type="text" class="form-control" name="zip_code" />
					</div>

					<div class="form-group">
						<label for="date">Date <span class="require">*</span></label> <input
							type="date" class="form-control" name="date" />
					</div>

					<div class="form-group">
						<label for="description">Description</label>
						<textarea rows="5" class="form-control" name="description"></textarea>
					</div>

					<div class="form-group">
						<label for="state">State <span class="require">*</span></label>
						<form:select path="state">
							<c:forEach items="${state}" var="state">
								<form:option value="${state}">${state}</form:option>
							</c:forEach>
						</form:select>
					</div>

					<div class="form-group">
						<input type="file" name="file" />
					</div>

					<div class="form-group">
						<p>
							<span class="require">*</span> - required fields
						</p>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary">Create</button>

					</div>

				</form:form>
			</div>

		</div>
	</div>

	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="js/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
</body>
</html>