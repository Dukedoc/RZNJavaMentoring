<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
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
	<link href="css/styles.css" type="text/css" rel="stylesheet"></link>
	<link href="css/showItems.css" type="text/css" rel="stylesheet"></link>
	<script type="text/javascript" src="js/scripts.js">
	__tag_18$53_
</script>
	<title>My Items</title>
	</head>
	<body>
	<jsp:directive.include file="/WEB-INF/jspf/logInfo.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/title.jsp" />
	<jsp:directive.include file="/WEB-INF/jspf/navigation.jsp" />
	<table class="items">
		<caption>Items</caption>
		<tr class="title">
			<th><c:choose>
				<c:when
					test="${param.sort eq 'item_id' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=item_id&amp;direction=desc">&amp;#9650;ID</a>
				</c:when>
				<c:when
					test="${param.sort eq 'item_id' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=item_id&amp;direction=asc">&amp;#9660;ID</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=item_id&amp;direction=asc">ID</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'title' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=title&amp;direction=desc">&amp;#9650;Title</a>
				</c:when>
				<c:when
					test="${param.sort eq 'title' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=title&amp;direction=asc">&amp;#9660;Title</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=title&amp;direction=asc">Title</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'description' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=description&amp;direction=desc">&amp;#9650;Description</a>
				</c:when>
				<c:when
					test="${param.sort eq 'description' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=description&amp;direction=asc">&amp;#9660;Description</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=description&amp;direction=asc">Description</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'category' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=category&amp;direction=desc">&amp;#9650;Сategory</a>
				</c:when>
				<c:when
					test="${param.sort eq 'category' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=category&amp;direction=asc">&amp;#9660;Сategory</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=category&amp;direction=asc">Сategory</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'seller' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=seller&amp;direction=desc">&amp;#9650;Seller</a>
				</c:when>
				<c:when
					test="${param.sort eq 'seller' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=seller&amp;direction=asc">&amp;#9660;Seller</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=seller&amp;direction=asc">Seller</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'start_price' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=start_price&amp;direction=desc">&amp;#9650;Start
					Price</a>
				</c:when>
				<c:when
					test="${param.sort eq 'start_price' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=start_price&amp;direction=asc">&amp;#9660;Start
					Price</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=start_price&amp;direction=asc">Start
					Price</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'bid_increment' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=bid_increment&amp;direction=desc">&amp;#9650;Bid
					Inc</a>
				</c:when>
				<c:when
					test="${param.sort eq 'bid_increment' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=bid_increment&amp;direction=asc">&amp;#9660;Bid
					Inc</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=bid_increment&amp;direction=asc">Bid
					Inc</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'best_offer' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=best_offer&amp;direction=desc">&amp;#9650;Best
					Offer</a>
				</c:when>
				<c:when
					test="${param.sort eq 'best_offer' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=best_offer&amp;direction=asc">&amp;#9660;Best
					Offer</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=best_offer&amp;direction=asc">Best
					Offer</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'bidder' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=bidder&amp;direction=desc">&amp;#9650;Bidder</a>
				</c:when>
				<c:when
					test="${param.sort eq 'bidder' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=bidder&amp;direction=asc">&amp;#9660;Bidder</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=bidder&amp;direction=asc">Bidder</a>
				</c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when
					test="${param.sort eq 'stop_date' &amp;&amp; param.direction eq 'asc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=stop_date&amp;direction=desc">&amp;#9650;Stop
					date</a>
				</c:when>
				<c:when
					test="${param.sort eq 'stop_date' &amp;&amp; param.direction eq 'desc'}">
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=stop_date&amp;direction=asc">&amp;#9660;Stop
					date</a>
				</c:when>
				<c:otherwise>
					<a
						href="FrontController?action=showMyItems&amp;searchType=${param.searchType}&amp;keywords=${param.keywords}&amp;sort=stop_date&amp;direction=asc">Stop
					date</a>
				</c:otherwise>
			</c:choose></th>
			<th>Action</th>
		</tr>
		<c:forEach items="${itemList}" var="item">
			<tr>
				<td><c:out value="${item.itemID}" /></td>
				<td><c:out value="${item.title}" /></td>
				<td><c:out value="${item.description}" /></td>
				<td><c:out value="${item.category}" /></td>
				<td><c:out value="${item.seller}" /></td>
				<td><c:out value="${item.startPrice}" /></td>
				<td><c:out value="${item.bidIncrement}" /></td>
				<td><c:out value="${item.bestOffer}" /></td>
				<td><c:out value="${item.bidder}" /></td>
				<td><fmt:formatDate value="${item.stopDate.time}"
					pattern="yyyy-MM-dd H-mm" /></td>
				<td><c:choose>
					<c:when test="${empty item.status}">
						<a
							href="FrontController?action=editItem&amp;itemID=${item.itemID}">
						Edit</a>
						<br />
						<a
							href="FrontController?action=deleteItem&amp;itemID=${item.itemID}">Delete</a>
					</c:when>
					<c:otherwise>
						<c:out value="${item.status}" />
					</c:otherwise>
				</c:choose>
				<c:if test="${!item.buyItNow}"><br />
					<a href="FrontController?action=showHistory&amp;itemID=${item.itemID}">Show history</a>
				</c:if>
				</td>				
			</tr>
		</c:forEach>
		<tr>
			<td colspan="11"><c:forEach var="i" begin="1" end="${pageCount}"
				step="1">
				<a
					href="FrontController?action=showMyItems&amp;page=${i}&amp;sort=${param.sort}&amp;direction=${param.direction}"><c:out
					value="${i}" /></a>
				<![CDATA[&nbsp;]]>
			</c:forEach></td>
		</tr>
	</table>

	</body>
	</html>
</jsp:root>