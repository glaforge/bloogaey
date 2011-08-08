import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.Text

['posts', 'categories', 'authors'].each { entityKind ->
    datastore.execute { select keys from entityKind }*.delete()
}

new Entity('categories').with {
    name = 'Groovy'
    description = 'Groovy dynamic language for the JVM'
    save()
}

new Entity('categories').with {
    name = 'Java'
    description = 'Articles about the Java ecosystem'
    save()
}

new Entity('categories').with {
    name = 'Blogosphere'
    description = 'About OSS, geek stuff, interesting reads, and more'
    save()
}

def posts = [
        [
                title: 'Groovy 1.8-final is out the door!',
                urlTitle: 'groovy-18-final-is-out-the-door',
                created: new Date() - 100,
                categories: ['Groovy'],
                content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent porttitor vulputate augue, ac vulputate purus lobortis non. Suspendisse suscipit elementum consectetur. Mauris adipiscing molestie sem sit amet ornare. Aenean non metus nec nulla sagittis blandit. In sollicitudin, massa quis ultrices ultrices, enim neque tristique purus, eu luctus mi mi eget lectus. Etiam at commodo sem. Maecenas quis rhoncus lacus. In id tempor arcu. Nulla rutrum, odio non ultrices cursus, sapien tortor interdum orci, eget rhoncus quam orci nec diam. Praesent ultrices, dui eu pellentesque tristique, massa sem consequat purus, nec posuere elit quam at purus. Donec volutpat leo quis purus.',
                draft: false,
                type: 'post'
        ],
        [
                title: 'GR8Conf Europe 2011 - a conference dedicated to the Groovy Ecosystem!',
                urlTitle: 'gr8conf-europe-2011-a-conference-dedicated-to-the-groovy-ecosystem',
                created: new Date() - 150,
                categories: ['Groovy', 'Java'],
                content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent porttitor vulputate augue, ac vulputate purus lobortis non. Suspendisse suscipit elementum consectetur. Mauris adipiscing molestie sem sit amet ornare. Aenean non metus nec nulla sagittis blandit. In sollicitudin, massa quis ultrices ultrices, enim neque tristique purus, eu luctus mi mi eget lectus. Etiam at commodo sem. Maecenas quis rhoncus lacus. In id tempor arcu. Nulla rutrum, odio non ultrices cursus, sapien tortor interdum orci, eget rhoncus quam orci nec diam. Praesent ultrices, dui eu pellentesque tristique, massa sem consequat purus, nec posuere elit quam at purus. Donec volutpat leo quis purus.',
                draft: false,
                type: 'post'
        ],
        [
                title: 'Gaelyk 0.7 is out!',
                urlTitle: 'gaelyk-07-is-out',
                created: new Date() - 1,
                categories: ['Groovy'],
                content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent porttitor vulputate augue, ac vulputate purus lobortis non. Suspendisse suscipit elementum consectetur. Mauris adipiscing molestie sem sit amet ornare. Aenean non metus nec nulla sagittis blandit. In sollicitudin, massa quis ultrices ultrices, enim neque tristique purus, eu luctus mi mi eget lectus. Etiam at commodo sem. Maecenas quis rhoncus lacus. In id tempor arcu. Nulla rutrum, odio non ultrices cursus, sapien tortor interdum orci, eget rhoncus quam orci nec diam. Praesent ultrices, dui eu pellentesque tristique, massa sem consequat purus, nec posuere elit quam at purus. Donec volutpat leo quis purus.',
                draft: false,
                type: 'post'
        ],
        [
                title: 'Gaelyk 1.0 released!',
                urlTitle: 'gaelyk-10-released',
                created: new Date() + 10,
                categories: ['Groovy'],
                content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent porttitor vulputate augue, ac vulputate purus lobortis non. Suspendisse suscipit elementum consectetur. Mauris adipiscing molestie sem sit amet ornare. Aenean non metus nec nulla sagittis blandit. In sollicitudin, massa quis ultrices ultrices, enim neque tristique purus, eu luctus mi mi eget lectus. Etiam at commodo sem. Maecenas quis rhoncus lacus. In id tempor arcu. Nulla rutrum, odio non ultrices cursus, sapien tortor interdum orci, eget rhoncus quam orci nec diam. Praesent ultrices, dui eu pellentesque tristique, massa sem consequat purus, nec posuere elit quam at purus. Donec volutpat leo quis purus.',
                draft: true,
                type: 'post'
        ],
        [
                title: 'About this site',
                urlTitle: 'about-this-site',
                created: new Date(),
                categories: [],
                content: new Text('About this site'),
                draft: false,
                type: 'page'
        ],
        [
                title: 'Contact me',
                urlTitle: 'contact-me',
                created: new Date(),
                categories: [],
                content: new Text('Contact me'),
                draft: false,
                type: 'page'
        ]
]

posts.each { item ->
    def e = new Entity('posts')
    item.each { prop, val ->
        e."$prop" = val
    }
    e.save()
}

redirect '/'