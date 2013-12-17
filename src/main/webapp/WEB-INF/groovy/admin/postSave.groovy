import com.google.appengine.api.datastore.Entity

import static bloogy.Utilities.*

def keyName = params.id
def created = Date.parse('yyyy/MM/dd HH:mm', params.created)

Entity postOrPage

if (keyName) {
    postOrPage = datastore.get('posts', keyName)
    postOrPage.modified = new Date()
} else {
    postOrPage = new Entity('posts', streamline(params.title))
    postOrPage.modified = created
}

postOrPage.title   = params.title
postOrPage.content = params.content
postOrPage.created = created
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

memcache.clearCacheForUri "/${postOrPage.type == 'page' ? 'page' : 'article'}/${postOrPage.key.name}"
memcache.clearCacheForUri '/archives'
memcache.clearCacheForUri '/'

redirect "/live/${postOrPage.key.name}"


