<div id="sidebar-wrapper">
    <div id="sidebar">
<%
    def originalURI = request.getAttribute('originalURI')
    if (user && users.isUserLoggedIn() && users.isUserAdmin() && (originalURI.contains('admin') || originalURI.contains('live'))) {
%>
        <div class="box">

            <div class="box-title">Administration</div>

            <div class="box-content">
                <ul class="nice-list">
                    <li><a href="/admin/posts/create">Create new content <img src="/images/add.png" alt="New content" align="right"></a></li>
                    <li><a href="/admin/posts">Drafts, Posts, and pages</a></li>
                    <li><a href="/admin/categories">Categories</a></li>
                    <li><a href="/admin/media">Media</a></li>
                    <li><a href="/admin/clearCache">Clear cached content</a></li>
                    <li><a href="${users.createLogoutURL("/")}">Logout</a></li>
                </ul>
            </div>

        </div>
<%
    }
%>
        <div class="box">

            <div class="box-title">Categories</div>

            <div class="box-content">
                <ul class="nice-list">
                <% datastore.execute{ from categories sort asc by name }.each { category -> %>
                    <li>
                        <a href="/category/${category.name}" alt="${category.description}">${category.name}</a>
                    </li>
                <% } %>
                </ul>
            </div>

        </div>

        <div class="box">

            <div class="box-title">Resources</div>

            <div class="box-content">
                <ul class="nice-list">
                    <li><a href="http://groovy.codehaus.org">Groovy</a> dynamic language</li>
                    <li><a href="http://grails.org">Grails</a> web application framework</li>
                    <li><a href="http://gaelyk.appspot.com">Gaelyk</a> lightweight toolkit for GAE</li>
                    <li><a href="http://www.gradle.org">Gradle</a> project automation</li>
                    <li><a href="http://spockframework.org/">Spock</a> testing framework</li>
                    <li><a href="http://codenarc.sourceforge.net/">CodeNarc</a> static code analysis</li>
                </ul>
            </div>

        </div>

        <div class="box nobborder">

            <div class="box-title">About</div>

            <div class="box-content">
                <p>
                    <a href="/page/contact-me">Guillaume Laforge</a> is blogging here about whatever comes to his mind,
                    but mostly about technology, and especially topics related to
                    the <a href="http://groovy.codehaus.org">Groovy</a> dynamic language,
                    the <a href="http://gaelyk.appspot.com">Gaelyk</a> lightweight toolkit
                    for developing applications for Google App Engine,
                    and all the other cool things in the Java and Groovy ecosystem.
                </p>
            </div>

        </div>

    </div>
</div>

<div class="clearer">&nbsp;</div>
