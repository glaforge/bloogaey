<!DOCTYPE html>
<html>

<head>
    <title>Search Guillaume Laforge's Blog</title>
    <script src="http://www.google.com/jsapi" type="text/javascript"></script>
    <script type="text/javascript">
        google.load('search', '1', {language:'en'});
        google.setOnLoadCallback(function () {
            var customSearchControl = new google.search.CustomSearchControl('013939896723962546743:_f8aom6tzae');
            customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);
            customSearchControl.draw('cse');
        }, true);
    </script>
    <link rel="stylesheet" href="http://www.google.com/cse/style/look/default.css" type="text/css"/>
    <link rel="stylesheet" href="/css/google-search.css" type="text/css"/>
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

                        <div class="post-title"><h1>Search this site</h1></div>

                        <div class="post-body">
                            <div id="cse" style="width: 100%;">Loading</div>
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