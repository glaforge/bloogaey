
def title = bloogy.Utilities.streamline(params.title)

try {
    datastore.get('posts', title)
    out << 'true'
} catch (any) {
    out << 'false'
}
