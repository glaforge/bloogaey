import com.google.appengine.api.datastore.Entity

import static bloogy.Utilities.*

def id = params.id?.toLong()

Entity postOrPage

if (id) {
    postOrPage = datastore.get('posts', id)
} else {
    postOrPage = new Entity('posts')
    postOrPage.urlTitle = streamline(params.title)
}

postOrPage.title   = params.title
postOrPage.content = params.content
postOrPage.created = Date.parse('yyyy/MM/dd HH:mm', params.created)
postOrPage.draft   = params.draft == 'draft' ?: false
postOrPage.type    = params.type

if (params.categories == null) {
    postOrPage.categories = null
} else if (params.categories instanceof String) {
    postOrPage.categories = [params.categories]
} else if (params.categories) {
    postOrPage.categories = params.categories.toList()
}

postOrPage.save()

memcache.clearCacheForUri "/${postOrPage.type == 'page' ? 'page' : 'article'}/${postOrPage.urlTitle}"
memcache.clearCacheForUri '/archives'
memcache.clearCacheForUri '/'

redirect "/live/${postOrPage.urlTitle}"


