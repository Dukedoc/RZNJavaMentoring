<div class="logInfo">
	<label id="userInfo">
		You are logged in as 
		<c:choose>
			<c:when test="${empty user}">
				guest 
				<a href="FrontController?action=showLogin">Login</a>
			</c:when>
			<c:otherwise>
				<c:out value="${user.fullName}" />
				<![CDATA[&nbsp;]]>
				<a href="FrontController?action=logout">Logout</a>

			</c:otherwise>
		</c:choose> 
	</label>
</div>