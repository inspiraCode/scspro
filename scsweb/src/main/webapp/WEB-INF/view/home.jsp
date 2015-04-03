<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Aberaty</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/aberaty.css" />'>
<style>
#container {
	position: fixed;
	top: 30%;
	left: 50%;
	transform: translate(-50%, -50%);
}

#user-baner {
	font-size: 40px;
	color: #222;
	text-align: center;
}

#user-name-baner {
	font-size: 32px;
	color: #222;
	text-align: center;
}

button {
	background-attachment: scroll;
	background-clip: border-box;
	background-color: transparent;
	background-image: -moz-linear-gradient(50% 0%, #F5F5F5, #F1F1F1);
	background-origin: padding-box;
	background-position: 0% 0%;
	background-repeat: repeat;
	background-size: auto auto;
	border-bottom-color: rgba(0, 0, 0, 0.1);
	border-bottom-left-radius: 2px;
	border-bottom-right-radius: 2px;
	border-bottom-style: solid;
	border-bottom-width: 1px;
	border-image-outset: 0 0 0 0;
	border-image-repeat: stretch stretch;
	border-image-slice: 100% 100% 100% 100%;
	border-image-source: none;
	border-image-width: 1 1 1 1;
	border-left-color: rgba(0, 0, 0, 0.1);
	border-left-style: solid;
	border-left-width: 1px;
	border-right-color: rgba(0, 0, 0, 0.1);
	border-right-style: solid;
	border-right-width: 1px;
	border-top-color: rgba(0, 0, 0, 0.1);
	border-top-left-radius: 2px;
	border-top-right-radius: 2px;
	border-top-style: solid;
	border-top-width: 1px;
	color: #444;
	cursor: default;
	display: inline-block;
	font-family: MS Shell Dlg;
	font-size: 11px;
	font-size-adjust: none;
	font-stretch: normal;
	font-style: normal;
	font-variant: normal;
	font-weight: 700;
	height: 29px;
	line-height: 29px;
	margin-bottom: 16px;
	margin-left: 8px;
	margin-right: 8px;
	margin-top: 16px;
	min-width: 54px;
	padding-bottom: 0px;
	padding-left: 8px;
	padding-right: 8px;
	padding-top: 0px;
	text-align: center;
	text-decoration: none;
	white-space: nowrap;
	-moz-border-bottom-colors: none;
	-moz-border-left-colors: none;
	-moz-border-right-colors: none;
	-moz-border-top-colors: none;
	-moz-font-feature-settings: normal;
	-moz-font-language-override: normal;
	-moz-text-decoration-color: #444;
	-moz-text-decoration-line: none;
	-moz-text-decoration-style: solid;
	-moz-user-select: none;
}

input {
	background-color: transparent;
	background-origin: padding-box;
	background-position: 0% 0%;
	background-repeat: repeat;
	background-size: auto auto;
	width: 250px;
	height: 25px;
	padding-left: 15px;
	font-size: 15px;
}
</style>
<script>
	function findProduct() {
		var userInput = document.getElementById('textUserInput');
		window.location = "products.html?search=" + userInput.value;
	}
</script>
</head>
<body>
	<div id="container">
		<div id="user-baner">SALES INC.</div>
		<div id="search-div">
			<button onclick="findProduct()">Find</button>
			<input id="textUserInput" type="text" value="" />
			<button>Make Business</button>
			<button>Email</button>
		</div>
		<div id="user-name-baner">Hello Diego Torres</div>
	</div>

</body>
</html>