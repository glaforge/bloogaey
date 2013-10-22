// Max number of articles per page
int pageSize = 10

// Page number
int page = params.page ? params.page.toInteger() : 0

// Retrieve the latest 10 posts
def posts = datastore.execute {
    from posts
    limit pageSize offset pageSize * page
    where created < new Date()
    and draft == false
    and type == 'post'
    sort desc by created
}.groupBy { it.created.year + 1900 }

request.page = page
request.posts = posts

forward '/WEB-INF/pages/archive.gtpl'

