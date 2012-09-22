<html>
	<head>
		<meta name="layout" content="blog"/>
		<title><g:message code="blog.entry.list.title" default="Title"/></title>
		<content tag="menuItem">index</content>
	</head>
	<body>
		<g:each in="${entries}" var="post">	
			<%-- REUSING THE POST TEMPLATE --%>
			<tmpl:postTemplate post="${post}"/>
		</g:each>
	</body>
</html>
