import com.google.appengine.api.datastore.Key

def key = ['posts', params.id.toLong()] as Key
key.delete()

redirect '/admin/posts'