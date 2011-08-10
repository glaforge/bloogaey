
memcache.clearCacheForUri '/'
memcache.clearCacheForUri '/archives'

100.times {
    memcache.clearCacheForUri "/p${it}"
    memcache.clearCacheForUri "/archives/p${it}"
}

memcache.clearCacheForUri '/search'
memcache.clearCacheForUri '/social'

memcache.clearCacheForUri '/feed/atom'

datastore.execute {
    from posts
    sort desc by created
    where draft == false
}.each {
    memcache.clearCacheForUri "/${it.type == 'post' ? 'article' : 'page'}/${it.key.name}"
}

datastore.execute {
    from categories
    sort asc by name
}.each {
    memcache.clearCacheForUri "/category/${it.name}"
    memcache.clearCacheForUri "/feed/atom/${it.name}"
}

redirect '/admin/posts'