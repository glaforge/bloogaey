def cache = localMode ? 0 : 1.hour

get "/",                                forward: '/home.groovy',                                                            cache: cache
get "/p@p",                             forward: '/home.groovy?page=@p',                      validate: { p ==~ /\d+/ },    cache: cache

get "/article/@title",                  forward: '/article.groovy?title=@title',                                            cache: cache
get "/page/@title",                     forward: '/article.groovy?title=@title',                                            cache: cache
get "/live/@title",                     forward: '/article.groovy?title=@title'

get "/archives/p@p",                    forward: '/archive.groovy?page=@p',                   validate: { p ==~ /\d+/ },    cache: cache
get "/archives",                        forward: '/archive.groovy',                                                         cache: cache

get "/category/@cat/p@p",               forward: '/category.groovy?category=@cat&page=@p',    validate: { p ==~ /\d+/ },    cache: cache
get "/category/@cat",                   forward: '/category.groovy?category=@cat',                                          cache: cache

get "/media/@file.@ext",                forward: '/media.groovy?fileName=@file.@ext',                                       cache: cache

get "/feed/atom/@cat",                  forward: '/feed.groovy?category=@cat',                                              cache: cache
get "/feed/atom",                       forward: '/feed.groovy',                                                            cache: cache

get "/social",                          forward: '/social.groovy',                                                          cache: cache

get "/search",                          forward: '/search.groovy',                                                          cache: 24.hours

get "/admin/categories",                forward: '/admin/adminCategories.groovy'
get "/admin/posts",                     forward: '/admin/adminPosts.groovy'
get "/admin/posts/create",              forward: '/WEB-INF/pages/admin/postEdit.gtpl'
get "/admin/media",                     forward: '/admin/adminMedia.groovy'
get "/admin/media/p@p",                 forward: '/admin/adminMedia.groovy?page=@p'
get "/admin/mediaSelector",             forward: '/admin/mediaSelector.groovy'
get "/admin/clearCache",                forward: '/admin/clearCache.groovy'
get "/admin/export",                    forward: '/admin/export.groovy'
get "/admin/import",                    forward: '/admin/import.groovy'

post "/admin/media/add",                forward: '/admin/mediaAdd.groovy'
post "/admin/media/delete/@bk",         forward: '/admin/mediaDelete.groovy?blobKey=@bk'
post "/admin/posts/delete/@id",         forward: '/admin/postDelete.groovy?id=@id'
post "/admin/posts/edit/@id",           forward: '/WEB-INF/pages/admin/postEdit.gtpl?id=@id'
post "/admin/posts/save",               forward: '/admin/postSave.groovy'
post "/admin/categories/add",           forward: '/admin/categoryAdd.groovy'
post "/admin/categories/delete/@cat",   forward: '/admin/categoryDelete.groovy?categoryName=@cat'

// route for 404 errors defined in web.xml
get "/not-found",                       forward: '/WEB-INF/pages/notFound.gtpl',                                            cache: 24.hours
// route for 403 errors defined in web.xml
get "/not-allowed",                     forward: '/WEB-INF/pages/notAllowed.gtpl',                                          cache: 24.hours
// when some error happens
get "/error",                           forward: '/WEB-INF/pages/error.gtpl'

get "/favicon.ico",                     redirect: "/images/gaelyk-favicon.png"

email to: "/admin/email.groovy"
