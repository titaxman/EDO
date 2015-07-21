<jsp:include page="include/header.jsp" />
<%@ include file="include/taglib.jsp"%>
<div id="content">
	<h2>
		Erreur
		<c:out value="${code}" />
	</h2>
	<p>
		<c:if test="${not empty message}">
			<c:out value="${message}" />
			<br />
		</c:if>
		Retour � la <a href="<spring:url value="/"/>">page d'accueil</a>.
	</p>
</div>
<jsp:include page="include/footer.jsp" />
