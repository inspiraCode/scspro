<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="shortcut icon" href="/scspro/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="/scspro/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="/scspro/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="/scspro/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="/scspro/images/ico/apple-touch-icon-57-precomposed.png">

<link type="text/css"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,800italic,400,700,800">
<link type="text/css" rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Oswald:400,700,300">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/resources/styles/font-awesome.min.css" />'>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/resources/styles/bootstrap.min.css" />'>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/resources/styles/animate.css" />'
	href="styles/animate.css">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/resources/styles/all.css" />'
	href="styles/all.css">
<link type="text/css" rel="stylesheet"
	href='<c:url value="/resources/styles/main.css" />'>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/resources/styles/style-responsive.css" />'>
</head>
<body
	style="background: url('<c:url value="/resources/images/bg/bg.png" />') center center fixed;">
	<div class="page-form">
		<div class="panel panel-blue">
			<div class="panel-body pan">
				<form name="f" action="login.do" method="post" class="form-horizontal">
					<div class="form-body pal">
						<div class="col-md-12 text-center">
							<h1 style="margin-top: -90px; font-size: 48px;">SCS Pro</h1>
							<br />
						</div>
						<div class="form-group">
							<div class="col-md-3">
								<img
									src='<c:url value="/resources/images/avatar/profile-pic.png" />'
									class="img-responsive" style="margin-top: -35px;" />
							</div>
							<div class="col-md-9 text-center">
								<h1>Hold on, please.</h1>
								<br />
								<p>Just sign in and we'll send you on your way</p>
							</div>
						</div>
						<div class="form-group">
							<label for="j_username" class="col-md-3 control-label">
								Username:</label>
							<div class="col-md-9">
								<div class="input-icon right">
									<i class="fa fa-user"></i> <input id="j_username"
										name="j_username" type="text" placeholder=""
										class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="j_password" class="col-md-3 control-label">
								Password:</label>
							<div class="col-md-9">
								<div class="input-icon right">
									<i class="fa fa-lock"></i> <input type="password"
										id="j_password" name="j_password" type="text" placeholder=""
										class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group mbn">
							<div class="col-lg-12" align="right">
								<div class="form-group mbn">
									<div class="col-lg-3">&nbsp;</div>
									<div class="col-lg-9">
										<a href="/scspro/" class="btn btn-default">Go back</a>&nbsp;&nbsp;
										<button type="submit" class="btn btn-default">Sign In</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-12 text-center">
			<c:if test="${not empty loginErr}">
				<p style="color: red;">${loginErr}</p>
			</c:if>
			<c:if test="${not empty logoutMsg}">
				<p style="color: red;">${logoutMsg}</p>
			</c:if>
			<c:if test="${not empty param.status}">
				<c:if test="${param.status == 403}">
					<p style="color: red;">Invalid username or password</p>
				</c:if>
			</c:if>
		</div>
	</div>
</body>
</html>
