
if (params.category) {

    // Max number of articles per page
    int pageSize = 10

    // Page number
    int page = params.page ? params.page.toInteger() : 0

    def posts = datastore.execute {
        from posts
        limit pageSize offset pageSize * page
        where created < new Date() + 1
        and draft == false
        and type == 'post'
        and categories == params.category
        sort desc by created
    }.groupBy { it.created.year + 1900 }

    request.page = page
    request.posts = posts
    request.category = params.category

    forward '/WEB-INF/pages/archive.gtpl'
} else {
    forward '/WEB-INF/pages/notfound.gtpl'
}