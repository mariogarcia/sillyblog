<div class="post">
	<h2 class="title">
		<g:if test="${!isFullEntry}">
			<g:link controller="blogEntry" action="show" 
					params="${[id:post.id,entryTitle:post.entryTitle]}">${post.entryTitle}</g:link>
		</g:if>
		<g:else>${post.entryTitle}</g:else>
	</h2>
	<p class="meta"><g:message code="blog.entry.post.postedOn"/> <g:formatDate date="${post.entryDate}" type="datetime" style="MEDIUM"/> 
		<g:if test="${!isFullEntry}">
			&nbsp;&bull;&nbsp; <g:link class="permalink" controller="blogEntry" action="show" params="${[id:post.id]}">
				<g:message code="blog.entry.post.fullEntry"/>
			</g:link>
		</g:if>
	</p>
	<div class="entry">
		<p>${post.entryText}</p>
	</div>
</div>
