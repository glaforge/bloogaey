<!DOCTYPE html>
<html>

<head>
	<title>Guillaume Laforge -- Groovy Blog</title>
    <% include '/WEB-INF/includes/syntaxHighlighting.gtpl' %>
    <% include '/WEB-INF/includes/meta.gtpl' %>
    <meta name="robots" content="noindex,follow" />
</head>

<body id="top">

<% include '/WEB-INF/includes/header.gtpl' %>
<% include '/WEB-INF/includes/navigation.gtpl' %>

<div id="content-wrapper">
	<div class="center-wrapper">

		<div class="content" id="content-two-columns">

			<div id="main-wrapper">
				<div id="main">

                <%
                    // no articles to display
                    if (!request.posts) {
                %>
                    <h1>No more posts</h1>
                <%
                    }
                %>
                <%
                    request.posts.each { post ->
                %>

					<div class="post">

						<div class="post-title"><h1><a href="/article/${post.key.name}">${post.title}</a></h1></div>

						<div class="post-date">
                            <% if (user && users.isUserLoggedIn() && users.isUserAdmin()) { %>
                            <form action="/admin/posts/edit/${post.key.name}" method="post">
                                <input type="image" src="/images/pencil.png" alt="Edit" align="right">
                            </form>
                            <% } %>
                            Posted on ${post.created.format('dd MMMM, yyyy')} (${post.created.pretty()})
                        </div>

						<div class="post-body">
                            ${post.content}
						</div>

                        <% if (post.categories) { %>
                        <div class="post-meta">
							In categories:
                            <% post.categories.each { category -> %>
                                <a href="/category/${category}">${category}</a>
                            <% } %>
                        </div>
                        <% } %>

                    </div>

                <%
                    }
                %>

                    <div class="post-meta archive-pagination">

                    <%
                        int page = request.page
                        if (request.posts && request.posts.size() == 5) {
                    %>
                        <div class="left"><a href="/p${page+1}">&#171; Previous page</a></div>
                    <%
                        }
                        if (page) {
                            
                    %>
                        <div class="right"><a href="${(page-1) ? ('/p' + (page-1)) : '/'}">Next page &#187;</a></div>
                    <%
                        }
                    %>
                        <div class="clearer">&nbsp;</div>

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