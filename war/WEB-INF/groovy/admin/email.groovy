import com.google.appengine.api.datastore.Entity
import javax.mail.internet.MimeMultipart
import javax.mail.BodyPart
import javax.mail.internet.MimeMessage

import static bloogy.Utilities.*

def content = request.inputStream.text
new Entity('email').with {
    rawContent = content
    save()
}

MimeMessage msg = mail.parseMessage(request)

log.info "Subject ${msg.subject}, to ${msg.allRecipients.join(', ')}, from ${msg.from[0]}"

String fullContent = ""

msg.contentStream.withReader { Reader reader ->
    fullContent = reader.text
}

def sb = new StringBuilder()

if (msg.content instanceof String) {
    log.info "Received a string"
    sb << msg.content
} else if (msg.content instanceof MimeMultipart) {
    log.info "Received a mime multipart"
    for (int i = 0; i < msg.content.count; i++) {
        BodyPart part = msg.content.getBodyPart(i)
        log.info("Part $i: $part")
        log.info("Content: " + part.content)
        sb << part.content
    }
}

log.info "ALL: ${sb.toString()}"

new Entity('posts').with {
    title = msg.subject
    urlTitle = streamline(msg.subject)
    created = new Date()
    categories = []
    draft = false
    type = 'post'

    content = "<pre>$fullContent</pre>"

    save()
}


