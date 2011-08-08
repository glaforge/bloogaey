import com.google.appengine.api.blobstore.BlobKey

new BlobKey(params.blobKey).delete()

def page = params.page.toInteger()

def singleOnPage = params.singleOnPage == 'true'
if (singleOnPage && page > 0) page -= 1

redirect "/admin/media${page > 0 ? "/p${params.page}" : ''}#media-browser"