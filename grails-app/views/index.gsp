<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Workout Tracker</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Workout Tracker</h1>
			<p>
				Track your workouts, set goals, keep fit!
			</p>
			<p>
				<sec:ifLoggedIn>
					<g:link controller="dashboard" class="btn btn-success btn-lg">View Dashboard</g:link>
				</sec:ifLoggedIn>
				<sec:ifNotLoggedIn>
					Already have an account? <g:link controller="login" action="auth" class="btn btn-primary btn-lg">Login here!</g:link>
					or <g:link controller="register" class="btn btn-success btn-lg">Create an account!</g:link>
				</sec:ifNotLoggedIn>
			</p>
		</div>
	</div>
</body>
</html>
