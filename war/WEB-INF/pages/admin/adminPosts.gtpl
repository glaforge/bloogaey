<!DOCTYPE html>
<html>

<head>
	<title>Administration -- Guillaume Laforge -- Groovy Blog</title>
    <% include '/WEB-INF/includes/meta.gtpl' %>
    <script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.sTabs.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            jQuery('#stabs').sTabs();
        });
    </script>
    <link rel="stylesheet" href="/css/stabs.css">
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

						<div class="post-title"><h1>Drafts, posts, and pages</h1></div>

						<div class="post-body">

                            <br>
                            <ul id="stabs">
                                <li>
                                    <a href="#Drafts">Drafts</a>
                                </li>
                                <li>
                                    <a href="#Posts">Posts</a>
                                </li>
                                <li>
                                    <a href="#Pages">Pages</a>
                                </li>
                            </ul>

                            <%
                                ['Drafts': request.drafts, 'Posts': request.posts, 'Pages': request.pages].each { type, posts ->
                            %>
                            <div id="${type}">
                            <ul class="nice-list">
                                <% posts.each { post -> %>
                                <li>
                                    <form action="/admin/posts/delete/${post.key.name}" method="post">
                                        <input type="image" src="/images/cross.png" alt="Delete it?" align="right">
                                    </form>
                                    <form action="/admin/posts/edit/${post.key.name}" method="post">
                                        <input type="image" src="/images/pencil.png" alt="Edit it" align="right">
                                    </form>
                                <% if (post.type == 'page') { %>
                                    <img src="/images/page_white_text.png" alt="Page" align="left">&nbsp;
                                <% } else if (post.type == 'post') { %>
                                    <img src="/images/newspaper.png" alt="Post" align="left">&nbsp;
                                <% } %>
                                    <strong><a href="/${post.type=='post'?'article':'page'}/${post.key.name}">${post.title}</a></strong>
                                    <br>
                                    <img src="/images/transparent.png" width="20" height="1">
                                    ${post.created.format('yyyy / MM / dd')}
                                    <% if (post.categories) { %> &mdash; ${post.categories?.join(', ') ?: 'none'} <% } %>
                                </li>
                                <% } %>
                            </ul>
                            </div>
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