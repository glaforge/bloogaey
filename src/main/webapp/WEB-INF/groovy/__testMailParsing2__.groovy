import javax.mail.internet.MimeMessage
import javax.mail.Session
import javax.mail.Authenticator
import javax.mail.internet.MimeMultipart

def msg = new MimeMessage(new Session(new Properties(), new Authenticator() {}), new FileInputStream('msg.txt'))

def sb = new StringBuilder()

def handleMultipart
handleMultipart = { MimeMultipart mmp ->
    for (int i = 0; i < mmp.count; i++) {
        def part = mmp.getBodyPart(i)
        if (part.content instanceof MimeMultipart) {
            handleMultipart(part.content)
        } else {
            if (part.contentType.contains('text/plain')) {

            } else if (part.contentType.contains('text/html')) {

            } else if (part.contentType.contains('image/')) {

            } else {
                
            }
        }
    }
}

handleMultipart(msg.content)

out << sb.toString()


/*

mixed
    related
        alternative
            text/plain
            text/html
        image-png (inline)
    image-png (attachment)


*/
