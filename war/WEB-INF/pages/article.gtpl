<!DOCTYPE html>
<html itemscope itemtype="http://schema.org/Blog">

<head>
    <% def post = request.post %>

    <title>${post.title} -- Guillaume Laforge's Blog</title>
    <% include '/WEB-INF/includes/syntaxHighlighting.gtpl' %>
    <% include '/WEB-INF/includes/meta.gtpl' %>

    <meta itemprop="name" content="${post.title}">
    <meta itemprop="description"
          content="${post.content.replaceAll('<[^>]*>', ' ').replaceAll('&[^;]*;', ' ').take(200)}...">

    <script type="text/javascript">var switchTo5x = true;</script>
    <script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
    <script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
    <script type="text/javascript">stLight.options({publisher:'4ebd393c-0ce9-4527-a07c-7c97bf04495f'});</script>
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

                        <div class="post-title"><h1>${post.title}</h1></div>

                        <div class="post-date">
                            <% if (user && users.isUserLoggedIn() && users.isUserAdmin()) { %>
                            <form action="/admin/posts/edit/${post.key.name}" method="post">
                                <input type="image" src="/images/pencil.png" alt="Edit" align="right">
                            </form>
                            <% } %>
                            Posted on ${post.created.format('dd MMMM, yyyy')} (${new com.ocpsoft.pretty.time.PrettyTime().format(post.created)})
                            <% if (post.modified != null && post.modified != post.created && (post.modified.time - post.created.time > 86400000)) { %>
                        &mdash;
                            Modified on ${post.modified?.format('dd MMMM, yyyy')} (${new com.ocpsoft.pretty.time.PrettyTime().format(post.modified)})
                            <% } %>

                        </div>

                        <% if (request.getAttribute('originalURI').contains('article')) { %>
                        <div class="text-center">
                            <br>
                            <script type="text/javascript"><!--
                            google_ad_client = "ca-pub-2993103784418650";
                            /* glaforge.appspot.com */
                            google_ad_slot = "4475788649";
                            google_ad_width = 468;
                            google_ad_height = 60;
                            //-->
                            </script>
                            <script type="text/javascript"
                                    src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                            </script>
                        </div>
                        <% } %>

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

                    <% if (request.getAttribute('originalURI').contains('article')) { %>
                    <div class="text-center">
                        <!-- Place this tag where you want the +1 button to render -->
                        <g:plusone count="false"></g:plusone>

                        <span class='st_twitter_large'></span>
                        <span class='st_dzone_large'></span>
                        <span class='st_delicious_large'></span>
                        <span class='st_reddit_large'></span>
                        <span class='st_linkedin_large'></span>
                        <span class='st_google_reader_large'></span>
                        <span class='st_facebook_large'></span>
                        <span class='st_email_large'></span>
                        <span class='st_sharethis_large'></span>

                        <p>&nbsp;</p>
                    </div>

                    <div class="archive-separator"></div>

                    <div id="comments">
                        <script>
                            var idcomments_acct = '6315ecb7a0c4effe19c78c6858ab628e';
                            var idcomments_post_id = '${post.key.name}';
                            var idcomments_post_url;
                        </script>
                        <span id="IDCommentsPostTitle" style="display:none"></span>
                        <script type='text/javascript'
                                src='http://www.intensedebate.com/js/genericCommentWrapperV2.js'></script>
                    </div>
                    <% } %>

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