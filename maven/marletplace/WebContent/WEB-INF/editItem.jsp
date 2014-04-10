<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
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
	<script type="text/javascript" src="js/editItem.js"><jsp:text /></script>
	<title>Add/Edit Item</title>
	</head>
	<body>
	<jsp:directive.include file="/WEB-INF/jspf/logInfo.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/title.jsp" />
	<form action="FrontController" onsubmit="return submitForm(this)">
	<input type="hidden" name="action" value="publishItem" />
	<input type="hidden" name="itemID" value="${item.itemID}" />
	<p class="error"><c:out value="${errorMessage}" /></p>
	<table class="form">
		<tr>
			<td><label for="title">Title of item:</label></td>
			<td><c:choose>
				<c:when test="${empty item}">
					<input class="field" type="text" name="title" id="title"
						value="${param.title}" />
				</c:when>
				<c:otherwise>
					<input class="field" type="text" name="title" id="title"
						value="${item.title}" />
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td><label for="description">Description: </label></td>
			<td>
				<c:choose>
				<c:when test="${empty item}">
					<textarea class="field" name="description" id="description"
						cols="20" rows="3"> <c:out value="${param.description}" /></textarea>
				</c:when>
				<c:otherwise>
					<textarea class="field" name="description" id="description"
						cols="20" rows="3"> <c:out value="${item.description}" /></textarea>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="category">Category: </label></td>
			<td><select name="categoryID">
				<c:forEach items="${categoriesList}" var="category">
					<c:choose>
						<c:when test="${item.categoryID == category.categoryID || param.categoryID == category.categoryID}">
							<option value="${category.categoryID}" selected="selected"><c:out
								value="${category.title}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${category.categoryID}"><c:out
								value="${category.title}"></c:out></option>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><label for="startPrice">Start price:</label></td>
			<td>
				<c:choose>
				<c:when test="${empty item}">
					<input class="field" name="startPrice"
						value="${param.startPrice}" id="startPrice" type="text" />
				</c:when>
				<c:otherwise>
				<input class="field" name="startPrice"
						value="${item.startPrice}" id="startPrice" type="text" />
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td><label for="timeLeft">Time left:</label></td>
			<td><c:choose>
				<c:when test="${empty item}">
					<input class="field" name="timeLeft" id="timeLeft" type="text"
						value="${param.timeLeft}" />
				</c:when>
				<c:otherwise>
					<c:set var="timeSplit" scope="page"
						value="${fn:split(item.timeLeft,'.')}" />
					<c:set var="hours" scope="page" value="${timeSplit[0]}" />
					<c:choose>
						<c:when test="${timeSplit[1] == 0}">
							<input class="field" name="timeLeft" value="${hours}"
								id="timeLeft" type="text" />
						</c:when>
						<c:otherwise>
							<fmt:formatNumber var="minutes" scope="page"
								value="${timeSplit[1]*0.6}" maxFractionDigits="2" />
							<input class="field" name="timeLeft" value="${hours}:${minutes}"
								id="timeLeft" type="text" />
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td><label for="buyItNow">Buy It Now:</label></td>
			<td><c:choose>
				<c:when test="${item.buyItNow || param.buyItNow}">
					<input name="buyItNow" type="checkbox" id="buyItNow" value="true"
						onchange="isBuyItNow(this)" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input name="buyItNow" type="checkbox" id="buyItNow"
						value="true" onchange="isBuyItNow(this)" />
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td><label for="bidIncrement">Bid increment:</label></td>
			<td>
				<c:choose>
					<c:when test="${empty item}">
						<input class="field" name="bidIncrement"
							value="${param.bidIncrement}" id="bidIncrement" type="text" />
					</c:when>
					<c:otherwise>
						<input class="field" name="bidIncrement"
							value="${item.bidIncrement}" id="bidIncrement" type="text" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="btn" colspan="2"><a
				href="FrontController?action=showItems" class="button"><input
				type="button" value="Back" /> </a> <input class="btn" type="reset"
				value="Reset" onclick="isBuyItNow(this);markErrorField(false);" />
			<input class="btn" type="submit" value="Publish / Save" /></td>
		</tr>
	</table>
	</form>
	</body>
	</html>
</jsp:root>