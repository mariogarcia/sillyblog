<html>
	<head>
		<meta name="layout" content="blog"/>
		<title><g:message code="blog.entry.list.create.title" default="Title"/></title>
		<g:set var="format" value="dd/MM/yyyy"/>
		<g:set var="jsFormat" value="${format.toLowerCase() - 'yy'}"/>
		<r:script>
			$(function(){
				createDatepicker("#entryDate","${jsFormat}");
				createAutocomplete("#tags","${g.createLink(controller:'blogEntryTags',action:'findAllTagsByNameLike')}");
			});
		</r:script>
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
				<g:textField class="text long" name="entryTitle" id="entryTitle" value="${entry?.entryTitle}"/>	
			<label><g:message code="blog.entry.create.form.text"/></label>
				<g:textArea cols="50" rows="10" name="entryText" id="entryText" value="${entry?.entryText}"/>
			<label><g:message code="blog.entry.create.form.tags"/></label>
				<%-- TODO move tags to a property editor --%>
				<g:textField class="text long" name="tags" id="tags" value="${entryTags}"/>	
			<label><g:message code="blog.entry.create.form.date"/></label>
				<tmpl:datePickerField 
					name="entryDate" 
					value="${entry?.entryDate}" 
					format="dd/MM/yyyy" 
					styleClass="text date"
				/>
			<g:submitButton class="submit" name="submit" value="Save"/>
		</g:form>	
	</body>
</html>
