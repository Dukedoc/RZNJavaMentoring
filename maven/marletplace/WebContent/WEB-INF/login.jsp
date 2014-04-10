<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/login.js"> <jsp:text /> </script>
	<script type="text/javascript" src="js/scripts.js"> <jsp:text /> </script>
	<title>Login</title>
	</head>
	<body>
	<h1><a href="showItems.html">Online Marketplace</a></h1>
	<form id="loginForm" action="FrontController"
		onsubmit="return validateForm(this)" method="post">
	<input type="hidden" name="action" value="login" />
	<table class="form">
		<tr>
			<td>
			<table>
				<tr>
					<td><label for="login">Login</label></td>
					<td><input type="text" class="field" name="login" id="login" /></td>
				</tr>
				<tr>
					<td><label for="password">Password</label></td>
					<td><input class="field" type="password" name="password"
						id="password" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<p class="error"><c:out value="${errorMsg}"></c:out></p>
					</td>
				</tr>
			</table>
			</td>
			<td rowspan="2" class="loginNavigation">
			<table>
				<tr>
					<td><!--<a
				href="javascript: void(0);"
				onclick="if (validateForm(document.forms['loginForm'])) document.forms['loginForm'].submit(); return false;">Sign
			in</a> --> <input type="submit" value="Sign in" class="link" /></td>
				</tr>
				<tr>
					<td><a href="FrontController?action=loginAsGuest">Enter as
					guest</a><br />
					</td>
				</tr>
				<tr>
					<td><a href="FrontController?action=registration">Registration</a>
					</td>
				</tr>
			</table>
			</td>

		</tr>
	</table>
	</form>
	</body>
	</html>
</jsp:root>