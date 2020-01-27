<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
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
<link rel="stylesheet" type="text/css" href="/css/editprofile.css">
<script type="text/javascript" src="js/timeline.js"></script>

<title>Insert title here</title>
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
							Profile</a> <a class="dropdown-item" href="/contactus">Contact Us</a>
						<a class="dropdown-item" href="/terms_conditions">Term &
							Conditions</a> <a class="dropdown-item" href="/logout">Logout</a>
					</div></li>
			</ul>
		</div>
	</nav>
	<!-- SEARCH -->
	<div class="search"
		style="padding-left: 40%; width: 60%; padding-top: 1%;">
		<form class="form" method="Get" action="/search">
			<input class="form-control" name="event" type="text"
				style="text-align: center;" placeholder="Search Events"
				aria-label="Search">
		</form>
	</div>
	<div class="container">
		<div class="row">

			<div class="col-md-10 col-md-offset-2">

				<h1>Update Event</h1>

				<form:form action="/events/${event.id}/update" method="post"
					modelAttribute="event" enctype="multipart/form-data">
					<input type="hidden" name="_method" value="put">
					<form:hidden path="user" />
					<form:hidden path="attendees" />

					<div class="form-group">
						<label for="name">Name <span class="require">*</span></label> <input
							type="text" class="form-control" name="name" />
					</div>

					<div class="form-group">
						<label for="city">City <span class="require">*</span></label> <input
							type="text" class="form-control" name="city" />
					</div>

					<div class="form-group">
						<label for="date">Date <span class="require">*</span></label> <input
							type="text" class="form-control" name="date" />
					</div>
					<div class="form-group">
						<label for="zip_code">Zip Code <span class="require">*</span></label>
						<input type="text" class="form-control" name="zip_code" />
					</div>
					<div class="form-group">
						<label for="description">Description <span class="require">*</span></label>
						<input type="text" class="form-control" name="description" />
					</div>
					<div class="form-group">
						<form:select path="state">
							<c:forEach items="${state}" var="state">
								<form:option value="${state}">${state}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group">
						<p>
							<span class="require">*</span> - required fields
						</p>
					</div>

					<div class="form-group">
						<input type="file" name="file" />

					</div>
					<div class="form-group">
						<input type="submit" value="Update" />
					</div>

				</form:form>
			</div>

		</div>
	</div>

	<%-- <div class="postedit">
		<h1>Update Event</h1>
		<p>
			<form:errors path="event.*" />
		</p>
		<form:form action="/events/${event.id}/update" method="post"
			modelAttribute="event" enctype="multipart/form-data">
			<input type="hidden" name="_method" value="put">
			<form:hidden path="user" />
			<form:hidden path="attendees" />
			<p>
				<form:label path="name">Name</form:label>
				<form:errors path="name" />
				<form:input path="name" />
			</p>
			<p>
				<form:label path="city">City</form:label>
				<form:errors path="city" />
				<form:input path="city" />

			</p>
			<p>
				<form:label path="date">Date</form:label>
				<form:errors path="date" />
				<form:input path="date" />
			</p>
			<p>
				<form:label path="zip_code">Zip Code</form:label>
				<form:errors path="zip_code" />
				<form:input path="zip_code" />

			</p>
			<p>
				<form:label path="description">Description</form:label>
				<form:errors path="description" />
				<form:textarea path="description" />
			<p>
				<form:label path="state">State</form:label>
				<form:errors path="state" />
				<form:select path="state">
					<c:forEach items="${state}" var="state">
						<form:option value="${state}">${state}</form:option>
					</c:forEach>
				</form:select>
			</p>
			<input type="file" name="file" />
			<input type="submit" value="Update" />
		</form:form>
	</div> --%>


	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="js/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
</body>
</html>