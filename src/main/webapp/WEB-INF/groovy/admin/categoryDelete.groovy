
datastore.execute { select single from categories where name == params.categoryName }.delete()

redirect '/admin/categories'