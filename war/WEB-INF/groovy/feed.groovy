import java.text.SimpleDateFormat

response.contentType = "text/xml;charset=utf-8"

def isoTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

def posts = datastore.execute {
    from posts limit 10
    where created < new Date()
    and draft == false
    and type == 'post'
    if (params.category) {
        and categories == params.category
    }
    sort desc by created
}

def serverRoot = "http://${request.serverName}${request.serverPort != 80 ? ":$request.serverPort" : ''}"

html.feed(xmlns: "http://www.w3.org/2005/Atom") {
    title "Guillaume Laforge's blog feed"
    subtitle "On all things Groovy!"
    link href: serverRoot, rel: "self"
    updated isoTime.format(new Date())
    generator(uri: "http://gaelyk.appspot.com", version: app.gaelyk.version, "Gaelyk lightweight Groovy toolkit for Google App Engine")

    posts.each { post ->
        entry {
            // create the summary to show in the Atom feed
            int min = Math.min(post.content.size(), 1000)
            def content = post.content[0..<min] + (min == 1000 ? '...' : '')

            id post.urlTitle
            title post.title
            link href: "${serverRoot}/article/${post.key.name}"
            published isoTime.format(post.created)
            updated isoTime.format(post.created)
            post.categories.each { cat ->
                category term: cat, scheme: "$serverRoot/category/${cat}"
            }
            summary(type: 'html') {
                mkp.yield content
            }
            author {
                name "Guillaume Laforge"
            }
        }
    }
}
