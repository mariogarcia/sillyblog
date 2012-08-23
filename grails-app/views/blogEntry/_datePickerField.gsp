<%-- VISIBLE/VALUE FIELD --%>
	<g:textField 
		name="${name}" 
		id="${name}" 
		class="${styleClass}" 
		value="${g.formatDate(format:format,date:value)}"/>
<%-- VALUE CONVERSION --%>
	<g:set var="givenDate" value="${value}"/>
	<g:set var="givenDay" value="${givenDate?.getAt(Calendar.DAY_OF_MONTH)}"/>
	<g:set var="givenMonth" value="${givenDate?.getAt(Calendar.MONTH)?.plus(1)}"/>
	<g:set var="givenYear" value="${givenDate?.getAt(Calendar.YEAR)}"/>
<%-- DATEPICKER BACK/HIDDEN FIELDS --%>
	<g:hiddenField name="${name}_day"   id="${name}_day"   value="${givenDay}"/>
	<g:hiddenField name="${name}_month" id="${name}_month" value="${givenMonth}"/>
	<g:hiddenField name="${name}_year"  id="${name}_year"  value="${givenYear}"/>
