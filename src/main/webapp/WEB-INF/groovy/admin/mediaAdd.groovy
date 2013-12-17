
def blobs = blobstore.getUploadedBlobs(request)
def blob = blobs["fileName"]

redirect '/admin/media'