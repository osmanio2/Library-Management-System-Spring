<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login</title>


<link href="<c:url value="/resources/core/css/hello.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>" rel="stylesheet" />


</head>

<body onload='document.loginForm.username.focus();'
	background="<c:url value="/resources/core/img/W.jpg"/>">

	<div class="container">
		<c:if test="${not empty error }">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="false"></span> <span class="sr-only">Error:</span>
				${error}
			</div>
		</c:if>
		<c:if test="${not empty msg }">
			<div class="alert alert-success" role="alert">
				<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
				<span class="sr-only">Success:</span> ${msg}
			</div>
		</c:if>


		<form name="loginForm" class="form-signin" method="post"
			action="<c:url value='/auth/login_check?targetUrl=${targetUrl}' />">

			<div style="color:white;">
			<h2 class="form-signin-heading">Please sign in</h2>
			</div>
			

			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="email" name="username" class="form-control"
				placeholder="Email address" /> <label for="inputPassword"
				class="sr-only">Password</label> <input type="password"
				name="password" class="form-control" placeholder="Password" />

			<!-- if this is login for update, ignore remember me check -->
			<c:if test="${empty loginUpdate}">
				<div class="checkbox" >
					<label style="color:white;"> <input type="checkbox" name="remember-me">
						Remember me
					</label>
				</div>
			</c:if>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

	</div>
	<!-- /container -->



</body>
</html>
