<!DOCTYPE html>
<html>

<head>
	<% include '/WEB-INF/includes/meta.gtpl' %>
	<title>Administration: Categories</title>
    <script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            jQuery('#catInput').focus().blur(function(evt) {
                if (jQuery(this).val().length == 0) {
                    jQuery('#catError').css('display', 'inline-block');
                    jQuery('#submitButton').attr('disabled', 'true');
                } else {
                    jQuery('#catError').css('display', 'none');
                    jQuery('#submitButton').attr('disabled', false);
                }
            });
        });
    </script>
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

						<div class="post-title"><h1>Categories</h1></div>

						<div class="post-body">

                            <ul class="nice-list">
                            <% request.categories.each { category -> %>
                                <li>
                                    <% if (request.categoriesCount[category] == 0) { %>
                                    <form action="/admin/categories/delete/${category.name}" method="post">
                                        <input type="image" src="/images/cross.png" alt="Delete this category?" align="right">
                                    </form>
                                    <%}%>
                                    <strong>${category.name}</strong> &mdash;
                                    <em>${category.description}</em> &mdash;
                                    <em>(<a href="/category/${category.name}">${request.categoriesCount[category]} posts</a>)</em>
                            </li>
                            <% } %>
                            </ul>
                            <fieldset>
                                <h3>Create a new category</h3>
                                <br>
                                <form action="/admin/categories/add" method="post">
                                    <div class="form-row">
                                        <div class="form-property form-required">Category name</div>
                                        <div class="form-value">
                                            <input type="text" name="categoryName" id="catInput" class="text" size="32">
                                            <div class="error hidden" id="catError">Category name must not be empty</div>
                                        </div>
                                        <div class="clearer">&nbsp;</div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-property form-required">Description</div>
                                        <div class="form-value"><input type="text" name="categoryDescription" class="text" size="70"></div>
                                        <div class="clearer">&nbsp;</div>
                                    </div>
                                    <br>
                                    <div class="form-row form-row-submit">
                                        <div class="form-value"><input type="submit" class="button" id="submitButton" value="Add category &#187;" /></div>
                                        <div class="clearer">&nbsp;</div>
                                    </div>
                                </form>
                            </fieldset>

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