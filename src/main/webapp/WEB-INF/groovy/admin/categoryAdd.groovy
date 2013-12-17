import com.google.appengine.api.datastore.Entity

def categoryName = params.categoryName
def categoryDescription = params.categoryDescription

new Entity('categories').with {
    name = categoryName
    description = categoryDescription
    save()
}

redirect '/admin/categories'