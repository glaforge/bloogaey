import java.text.SimpleDateFormat

def delicious = "http://feeds.delicious.com/v2/rss/glaforge?count=15".toURL().get(async: true)
def greader   = "http://www.google.com/reader/public/atom/user%2F16866715143536937448%2Fstate%2Fcom.google%2Fbroadcast".toURL().get(async: true)
def twitter   = "http://search.twitter.com/search.atom?q=from%3Aglaforge&rpp=200".toURL().get(async: true)

def items = []

def slurper = new XmlSlurper()

// date format: 2011-07-29T09:00:10Z
def sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

def twitterDoc   = slurper.parseText(twitter.get().text)
twitterDoc.entry.each { entry ->
    items << [
            origin: 'twitter',
            title: entry.title.text(),
            published: sdf.parse(entry.published.text()),
            link: entry.link.find { it.@type == 'text/html' }.@href.text()
    ]
}

def greaderDoc   = slurper.parseText(greader.get().text)
greaderDoc.entry.each { entry ->
    items << [
            origin: 'greader',
            title: entry.title.text(),
            published: sdf.parse(entry.published.text()),
            link: entry.link.find { it.@type == 'text/html' }.@href.text()
    ]
}

// date format: Thu, 28 Jul 2011 07:32:22 +0000
sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US)

def deliciousDoc = slurper.parseText(delicious.get().text)
deliciousDoc.channel.item.each { entry ->
    items << [
            origin: 'delicious',
            title: entry.title.text(),
            published: sdf.parse(entry.pubDate.text()),
            link: entry.link.text()
    ]
}


request.items = items.sort { it.published }.reverse().each { it.published.clearTime() }.groupBy { it.published }

forward '/WEB-INF/pages/social.gtpl'