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
	<link href="css/showItems.css" type="text/css" rel="stylesheet"><jsp:text /></link>
	<script type="text/javascript" src="js/scripts.js">
	__tag_16$52_
</script>
	<title>Black list</title>
	</head>
	<body>
	<jsp:directive.include file="/WEB-INF/jspf/logInfo.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/title.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/navigation.jsp" />
	<table class="items">
		<caption>Black list</caption>
		<tr class="title">
			<th>User</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${usersList}" var="bidder">
			<tr>
				<td><c:out value="${bidder.fullName}"></c:out> </td>
				<td><a href="FrontController?action=removeFromBlackList&amp;userID=${bidder.userID}">remove</a></td>
			</tr>
		</c:forEach>
	</table>
	</body>
	</html>	
</jsp:root>