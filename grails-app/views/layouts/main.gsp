<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Workout Tracker"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
	<asset:stylesheet src="application.css"/>
	<g:layoutHead/>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<sec:ifLoggedIn>
				<g:link controller="dashboard" class="navbar-brand">Workout Tracker</g:link>
			</sec:ifLoggedIn>
			<sec:ifNotLoggedIn>
				<a href="${createLink(uri: '/')}" class="navbar-brand">Workout Tracker</a>
			</sec:ifNotLoggedIn>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<sec:ifLoggedIn>
					<li><g:link controller="activity">Activities</g:link></li>
					<li><g:link controller="goal">Goals</g:link></li>
				</sec:ifLoggedIn>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:ifLoggedIn>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Hello, <sec:username/> <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><g:link controller='logout' data-method="post">Logout</g:link></li>
						</ul>
					</li>
				</sec:ifLoggedIn>
				<sec:ifNotLoggedIn>
					<li><g:link controller="login" action="auth">Login/Sign Up</g:link></li>
				</sec:ifNotLoggedIn>
			</ul>
		</div>
	</div>
</nav>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<g:if test="${flash.message}">
				<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.warning}">
				<div class="alert alert-danger" role="status">${flash.message}</div>
			</g:if>
		</div>
	</div>
</div>
<g:layoutBody/>
<asset:javascript src="application.js"/>
</body>
</html>