<!DOCTYPE html>
<html>

<head>
	<title>Social (twitter, delicious, google reader) -- Guillaume Laforge's Blog</title>
    <script src="http://www.google.com/jsapi" type="text/javascript"></script>
    <% include '/WEB-INF/includes/meta.gtpl' %>
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

						<div class="post-title"><h1>Social</h1></div>

						<div class="post-body">

                            <p>
                                This page gathers:
                            </p>
                            <ul>
                                <li>my <a href="http://twitter.com/glaforge">tweets on Twitter</a>,</li>
                                <li>my <a href="https://plus.google.com/114130972232398734985/posts">public posts on Google+</a>,</li>
                                <li>my <a href="http://www.google.com/reader/shared/glaforge">shared items in Google Reader</a>,</li>
                                <li>and my <a href="http://www.delicious.com/glaforge">saved bookmakrs on Delicious</a>.</li>
                            </ul>

                            <% request.items.each { date, entries -> %>
                            <h2 class="archive-year">${date.format('yyyy / MM / dd')}</h2>
                            <ul class="nice-list">
                            <% entries.each { entry -> %>
                                <li>
                                    <img src="/images/icon-${entry.origin}.png">
                                    <a href="${entry.link}">${entry.title}</a>
                                </li>
                            <%} %>
                            </ul>
                            <% } %>
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