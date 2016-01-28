<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Home</title>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the top of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<c:url value="/resources/core/js/bootstrap.min.js"/>"></script>
<!-- Bootstrap core CSS -->
<c:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />

<!-- Custom styles for this template -->
<c:url value="/resources/core/css/search.css" var="coreCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<!-- The jQuery library is a prerequisite for all jqSuite products -->
<!-- This is the Javascript file of jqGrid -->
<script type="text/ecmascript"
	src="<c:url value="/resources/core/js/jquery.jqGrid.min.js"/>"></script>
<!-- This is the localization file of the grid controlling messages, labels, etc.
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
<!-- The link to the CSS that the grid needs -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/core/css/ui.jqgrid-bootstrap.css"/>" />
</head>

<body>

	<script>
	$(document).ready(function() {
		$.jgrid.defaults.width = 780;
		$.jgrid.defaults.styleUI = 'Bootstrap';
		
		$("#search-button").click(function() {
			var url = "/Library_Management_System/search/book";
			$("#jqGrid").jqGrid({
				url : url,
				postData: {
			        name: function() {
			           return $(".search").val();
			        }
			    },
				datatype : "json",
				colModel : [ {
					label : 'Book Title',
					name : 'title',
					width : 90
				}, {
					label : 'Book Edition',
					name : 'edition',
					width : 75
				}, {
					label : 'Category',
					name : 'category',
					width : 75
				}, {
					label : 'Author',
					name : 'author',
					width : 90
				}, {
					label : 'ISBN',
					name : 'isbn',
					width : 60
				}, {
					label : 'Status',
					name : 'status',
					width : 60
				}, ],
				viewrecords : true, // show the current page, data rang and total records on the toolbar
				width : 780,
				height : 200,
				rowNum : 30,
				loadonce : false, 
				pager : "#jqGridPager"
			});
			$("#jqGrid").trigger("reloadGrid");
		});
	});
	</script>

	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">

				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">Search</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li><a href="<c:url value="/home"/>">My Library Account</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<form class="form-inline">
						<div class="form-group">

							<input type="text" class="search form-control"
								placeholder="What is book name?">


							<button id="search-button" type="button" class="btn btn-info">Search
								Book</button>

						</div>
					</form>
					<div class="row" style="margin-top: 10px; margin-left: 20px">
						<table id="jqGrid"></table>
						<div id="jqGridPager"></div>
					</div>
				</div>

				<div class="mastfoot">
					<div class="inner"></div>
				</div>

			</div>

		</div>

	</div>

</body>
</html>
