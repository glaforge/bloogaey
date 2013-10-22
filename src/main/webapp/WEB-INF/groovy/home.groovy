
// Max number of articles per page
int pageSize = 5

// Page number
int page = params.page ? params.page.toInteger() : 0

// Retrieve 10 posts
def posts = datastore.execute {
    from posts
    limit pageSize offset pageSize * page
    where created < upperDate
    and draft == false
    and type == 'post'
    sort desc by created
}

request.posts = posts
request.page = page

forward '/WEB-INF/pages/home.gtpl'
