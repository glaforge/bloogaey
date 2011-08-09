def title = params.title

def post = datastore.get('posts', title)

if (post) {
    request.post = post
    forward '/WEB-INF/pages/article.gtpl'
} else {
    request.title = params.title
    forward '/WEB-INF/pages/notFound.gtpl'
}