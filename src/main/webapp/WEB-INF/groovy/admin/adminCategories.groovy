
request.categories  = datastore.execute {
    from categories
    sort asc by name
}

request.categoriesCount = request.categories.collectEntries { category ->
    [(category): datastore.execute {
        select count
        from posts
        where categories == category.name
    }]
}

forward '/WEB-INF/pages/admin/adminCategories.gtpl'