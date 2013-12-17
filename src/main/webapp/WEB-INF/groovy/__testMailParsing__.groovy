import javax.mail.internet.MimeMessage
import javax.mail.Session
import javax.mail.Authenticator
import javax.mail.internet.MimeMultipart

def msg = new MimeMessage(new Session(new Properties(), new Authenticator() {}), new FileInputStream('msg.txt'))

html.html {
    head {
        title "Parsed email from GMail"
    }
    body {
        handleMultipart(msg.content)
    }
}

def handleMultipart(MimeMultipart mmp) {
    html.div "Parts: ${mmp.count}"
    html.div "Content-type: ${mmp.contentType}"

    for (int i = 0; i < mmp.count; i++) {
        def part = mmp.getBodyPart(i)
        html.h1 "Part #$i: ${part}"
        html.h2 "Content ID: ${part.getHeader('Content-ID')}"
        html.h2 "X-Attachment ID: ${part.getHeader('X-Attachment-Id')}"
        html.h2 "Content-type: $part.contentType"
        html.h2 "Content-disposition: $part.disposition"
        html.h2 "File name: ${part.fileName}"
        if (part.content instanceof MimeMultipart) {
            html.blockquote {
                handleMultipart(part.content)
            }
        } else {
            if (part.contentType.contains('text/plain')) {
                html.mkp.yieldUnescaped part.content
            } else if (part.contentType.contains('text/html')) {
                html.mkp.yieldUnescaped part.content
            } else if (part.contentType.contains('image/')) {
                html.img src:"data:${part.contentType.find('image/(.*);')}base64,${part.content.bytes.encodeBase64()}"
            } else {
                html.mkp.yieldUnescaped part.content.bytes.encodeBase64()
            }
        }
    }
}