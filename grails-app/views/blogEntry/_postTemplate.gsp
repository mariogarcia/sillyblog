<div class="post">
	<h2 class="title">
		<g:if test="${!isFullEntry}">
			<g:link controller="blogEntry" action="show" 
					params="${[id:post.id,entryTitle:post.entryTitle]}">${post.entryTitle}</g:link>
		</g:if>
		<g:else>${post.entryTitle}</g:else>
	</h2>
	<p class="meta"><g:message code="blog.entry.post.postedOn"/> <g:formatDate date="${post.entryDate}" type="datetime" style="MEDIUM"/> 
		<sec:ifLoggedIn>
			&nbsp;&bull;&nbsp; <g:link class="permalink" 
				controller="blogEntry" action="editEntry" params="${[id:post.id]}">
					<g:message code="blog.index.menu.edit"/>
				</g:link>
		</sec:ifLoggedIn>
	</p>
	<div class="entry">
		<g:if test="${fullEntry}">
			<p>${post.entryText}</p>
		</g:if>
		<g:else>
			<p>${post.entryText?.intro(100)}</p>
		</g:else>
	</div>
</div>
