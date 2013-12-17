try {
    datastore.get('posts', params.title.streamline())
    out << 'true'
} catch (any) {
    out << 'false'
}
