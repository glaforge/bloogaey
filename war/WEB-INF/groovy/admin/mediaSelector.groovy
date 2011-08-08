import com.google.appengine.api.blobstore.BlobInfoFactory

def blobstoreKind = com.google.appengine.api.blobstore.BlobInfoFactory.KIND

def numberPerPage = 5

def page = params.page ? params.page.toInteger() : 0
def offsetParam = page * numberPerPage

datastore.execute {
    from blobstoreKind
    offset offsetParam limit numberPerPage
    sort desc by creation
}.each { media ->
    def info = new BlobInfoFactory().createBlobInfo(media)
    def mediaUrl = "/media/${java.net.URLEncoder.encode(info.filename)}"
    def croppedUrl = images.getServingUrl(info.blobKey) + '=s100-c'

    html.img src: croppedUrl, fullsrc: mediaUrl
}

