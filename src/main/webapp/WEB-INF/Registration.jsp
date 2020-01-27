<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="css/registration.css">
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-10 col-xl-9 mx-auto">
				<div class="card card-signin flex-row my-2">
					<div class="">
					</div>
					<div class="card-body">
						<h5 class="card-title text-center">Register</h5>
						<p>
							<form:errors path="user.*" />
						</p>
						<form method="post" action="/register">
							<div class="form-label-group">
								<input type="text" name="name" id="firstName"
									class="form-control" placeholder="First Name" required>
								<label for="firstName">First Name</label>
							</div>
							<div class="form-label-group">
								<input type="text" name="name" id="lastName"
									class="form-control" placeholder="lastName" required> <label
									for="lastName">Last Name</label>
							</div>
							<div class="form-label-group">
								<input type="email" name="email" id="email" class="form-control"
									placeholder="Email" required> <label for="email">Email</label>
							</div>
							<div class="form-label-group">
								<input type="password" name="password" id="password"
									class="form-control" placeholder="Password" required> <label
									for="password">Password</label>
							</div>
							<div class="form-label-group">
								<input type="password" id="passwordConfirmation"
									class="form-control" placeholder="Password" required> <label
									for="passwordConfirmation">Confirm password</label>
							</div>
							<form:select path="country" items="${country}" />
							<form:select path="state" items="${state}" />
							<hr class="my-4">
							
							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">REGISTER</button>
							<a class="d-block text-center mt-2 small" href="/">Login?</a>
							<hr class="my-4">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>