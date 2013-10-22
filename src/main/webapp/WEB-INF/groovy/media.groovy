import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.blobstore.BlobInfoFactory
import com.google.appengine.api.blobstore.BlobInfo

Entity blobEntity = datastore.execute { select single from BlobInfoFactory.KIND where filename == params.fileName }

if (blobEntity) {
    BlobInfo blobInfo = new BlobInfoFactory().createBlobInfo(blobEntity)

    response.contentType = blobInfo.contentType

    if (localMode) {
        // for some mysterious reason, bkey.serve response doesn't work on local SDK but works in production
        // so we use a workaround here instead
        files.getBlobFile(blobInfo.blobKey).withInputStream { BufferedInputStream stream ->
            sout << stream
        }
    } else {
        blobInfo.blobKey.serve response
    }
} else {
    response.contentType = 'image/png'
    sout << new File('images/image-not-found.png').bytes
}
