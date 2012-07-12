<html>
	<head>
		<meta name="layout" content="blog"/>
		<title><g:message code="blog.entry.list.create.title" default="Title"/></title>
	</head>
	<body>
		<!-- Error messages -->
		<g:if test="${flash.message}">
			<p class='error'><g:message code="${flash.message}"/></p>
		</g:if>
		<g:eachError bean="${entry}">
			<p class='error'><g:message error="${it}"/></p>	
		</g:eachError>
		<!-- Blog Entry form -->
		<g:form action="saveEntry" method="post">
			<label><g:message code="blog.entry.create.form.title"/></label>
			<g:textField class="long" name="entryTitle" id="entryTitle" value="${entry?.entryTitle}"/>	
			<label><g:message code="blog.entry.create.form.text"/></label>
			<g:textArea cols="50" rows="10" name="entryText" id="entryText" value="${entry?.entryText}"/>
			<label><g:message code="blog.entry.create.form.date"/></label>
			<g:datePicker name="entryDate" id="entryDate" value="${entry?.entryDate}"/>	
			<g:submitButton class="submit" name="submit" value="Save"/>
		</g:form>	
	</body>
</html>
