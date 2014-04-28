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
	<title>Show history</title>
	</head>
	<body>
	<jsp:directive.include file="/WEB-INF/jspf/logInfo.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/title.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/navigation.jsp" />
	<p class="error"><c:out value="${errorMsg}" /></p>
	<table class="items">
		<caption>Bids history</caption>
		<tr class="title">
			<th>Bidder</th>
			<th>Bid</th>
			<c:if
				test="${!empty user &amp;&amp; user.userID == bidsDetailList[0].sellerID}">
				<th>Black List</th>
			</c:if>

		</tr>
		<c:forEach items="${bidsDetailList}" var="bidDetails">
			<tr>
				<td><c:out value="${bidDetails.bidder}" /></td>
				<td><c:out value="${bidDetails.bid}" /></td>
				<c:if
					test="${!empty user &amp;&amp; user.userID == bidsDetailList[0].sellerID}">
					<td><a
						href="FrontController?action=addToBlackList&amp;userID=${bidDetails.bidderID}&amp;itemID=${bidDetails.itemID}">add</a>					
					</td>
				</c:if>

			</tr>
		</c:forEach>
	</table>
	</body>
	</html>
</jsp:root>