<html>
	<head>
		<meta name="layout" content="blog"/>
		<title>${entry.entryTitle}</title>
	</head>
	<body>
		<%-- REUSING THE POST TEMPLATE --%>
		<tmpl:postTemplate post="${entry}" fullEntry="${true}"/>
	</body>
</html>
