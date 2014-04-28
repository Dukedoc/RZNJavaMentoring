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
	<link href="css/styles.css" type="text/css" rel="stylesheet"><jsp:text /></link>
	<script type="text/javascript" src="js/scripts.js"><jsp:text /></script>
	<script type="text/javascript" src="js/registration.js"><jsp:text /></script>
	<title>Registration</title>
	</head>
	<body>
	<h1><a href="showItems.html">Online Marketplace</a></h1>
	<!-- Registration form -->
	<form action="FrontController" onsubmit="return submitForm(this)"
		method="post">
	<input type="hidden" name="action" value="addUser" />
	<p class="error"><c:out value="${errorMsg}" /></p>
	<table class="form">
		<tr>
			<td colspan="2">
			<table>
				<tr>
					<td><label for="fullName">Full Name:</label></td>
					<td><input class="field" type="text" name="fullName"
						id="fullName" value="${user.fullName}" /></td>
				</tr>

				<tr>
					<td><label for="address">Billing Address:</label></td>
					<td><input class="field" type="text" name="address"
						id="address" value="${user.billingAddress}" /></td>
				</tr>

				<tr>
					<td><label for="phone">Phone:</label></td>
					<td><input class="field" type="text" name="phone" id="phone"
						value="${user.phone}" /></td>
				</tr>

				<tr>
					<td><label for="email">E-mail:</label></td>
					<td><input class="field" type="text" name="email" id="email" 
						value="${user.email}"/>
					</td>
				</tr>
				<tr>
					<td><label for="login">Login:</label></td>
					<td><input class="field" type="text" name="login" id="login" 
						value="${user.login}"/></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><input class="field" type="password" name="password"
						id="password" /></td>
				</tr>
				<tr>
					<td><label for="rePassword">Re-enter Password:</label></td>
					<td><input class="field" type="password" name="rePassword"
						id="rePassword" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr align="center">
			<td colspan="2"><a class="button"
				href="FrontController?action=login"><input type="button"
				value="Back" /> </a><input type="reset" value="Reset"
				onclick="markErrorField(false);" /> <input type="submit"
				value="Registration" /></td>
		</tr>
	</table>
	</form>

	</body>
	</html>
</jsp:root>