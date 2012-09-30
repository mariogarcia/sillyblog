<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><g:layoutTitle default="Silly Blog"/></title>
<r:require modules="ui"/>
<g:layoutHead/>
<r:layoutResources />
<r:script>
	$(function(){
		$("input:submit").button();
	});
</r:script>
</head>
<body>
<div id="wrapper">
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="page-content">
					<div id="header-wrapper">
						<div id="header">
							<div id="logo">
								<h1><a href="#">ExtremeSurfing</a></h1>
								<p>template design by <a href="http://www.freecsstemplates.org/">FCT</a></p>
							</div>
						</div>
					</div>
					<!-- end #header -->
					<div id="menu-wrapper">
						<div id="menu">
							<ul>
								<li class="${pageProperty(name:'page.menuItem') == 'index' ? 'current_page_item' :''}">
									<g:link controller="blogEntry" action="index">
										<g:message code="blog.index.menu.home"/>
									</g:link>
								</li>
								<sec:ifLoggedIn>
										<li class="${pageProperty(name:'page.menuItem') == 'create' ? 'current_page_item' :''}">
											<g:link controller="blogEntry" action="createEntry">
												<g:message code="blog.entry.create.title"/>
											</g:link>
										</li>
										<li>
											<g:link controller="logout" action="index">
												<g:message code="blog.index.menu.logout"/>
											</g:link>
										</li>
								</sec:ifLoggedIn>
								<sec:ifNotLoggedIn>
										<li>
											<g:link controller="login" action="index">
												<g:message code="blog.index.menu.login"/>
											</g:link>
										</li>
								</sec:ifNotLoggedIn>
							</ul>
						</div>
					</div>
					<!-- end #menu -->
					<%--
					<div id="banner">
						<g:img dir="images" file="img05.jpg" width="940" height="300"/>
					</div>
					--%>
					<div id="content">
						<g:layoutBody/>
					</div>
					<!-- end #content -->
					<g:set var="lastSix" value="${com.github.sillyblog.BlogEntry.list(max:6,sort:'entryDate',order:'desc')}"/>
					<div id="sidebar">
						<ul>
							<li>
								<h2><g:message code="blog.entry.list.last"/></h2>
								<p>
									<g:set var="lastEntry" value="${lastSix.find{it}}"/>
									<g:link controller="blogEntry" action="show" 
										params="[id:lastEntry.id]">${lastEntry.entryTitle}</g:link>
								</p>
							</li>
							<li>
								<h2><g:message code="blog.entry.list.lasts"/></h2>
								<ul>
									<g:each var="entry" in="${lastSix}">
										<li>
											<g:link controller="blogEntry" action="show" 
												params="[id:entry.id]">${entry.entryTitle}</g:link>
										</li>
									</g:each>
								</ul>
							</li>
							<li>
								<h2>Blogroll</h2>
								<p id="tagCloud">
									<g:render template="/layouts/blogEntryTagCloud"/>
								</p>
							</li>
						</ul>
					</div>
					<!-- end #sidebar -->
				</div>
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
	<p>Copyright (c) 2012 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/">FCT</a>.</p>
</div>
<!-- end #footer -->
<r:layoutResources />
</body>
</html>
