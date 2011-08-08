
request.posts  = datastore.execute {
    from posts
    sort desc by created
    where draft == false
    and type == 'post'
}

request.pages  = datastore.execute {
    from posts
    sort desc by created
    where draft == false
    and type == 'page'
}

request.drafts  = datastore.execute {
    from posts
    sort desc by created
    where draft == true
}

forward '/WEB-INF/pages/admin/adminPosts.gtpl'