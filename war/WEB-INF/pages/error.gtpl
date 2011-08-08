<!DOCTYPE html>
<html>

<head>
	<% include '/WEB-INF/includes/meta.gtpl' %>
	<title>An error occured -- Guillaume Laforge -- Groovy Blog</title>
</head>

<body id="top">

<% include '/WEB-INF/includes/header.gtpl' %>
<% include '/WEB-INF/includes/navigation.gtpl' %>

<div id="content-wrapper">
	<div class="center-wrapper">

		<div class="content" id="content-two-columns">

			<div id="main-wrapper">
				<div id="main">

					<div class="post">

						<div class="post-title"><h1>An error occured</h1></div>

						<div class="post-body">
                        <% if (request.getAttribute('code')) { %>
                            <dt><strong>Error code:</strong></dt>
                            <dd>${request.getAttribute('code')}</dd>
                        <% } else if (request.getAttribute('ex')) { %>
                            <dt><strong>Exception:</strong></dt>
                            <dd>${request.getAttribute('ex')}</dd>
                        <% } %>
                            <dt><strong>Message:</strong></dt>
                            <dd>${request.getAttribute('msg')}</dd>
                        </div>

                    </div>

				</div>
			</div>

            <% include '/WEB-INF/includes/left.gtpl' %>
		</div>

	</div>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>
<% include '/WEB-INF/includes/bottom.gtpl' %>

</body>
</html>