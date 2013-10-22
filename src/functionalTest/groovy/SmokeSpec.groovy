import geb.spock.GebSpec
import geb.Browser
import groovyx.net.http.RESTClient
import spock.lang.Shared
import groovyx.gaelyk.logging.GroovyLogger

class SmokeSpec extends GebSpec {

    @Shared
    def log = new GroovyLogger("myLogger")

    @Shared
    private def restUrl = "${browser.baseUrl}gcm/register/"
    
    @Shared
    private def client = new RESTClient()
    
    void "main page title should be 'SimpleSMS'"() {
        when:
        go 'http://localhost:8080'

        then:
        title == "SimpleSMS"
    }
    
     void "verify grm register with rest"() {
        when:
        log.info "******** URL $restUrl"
        //def resp = client.post(uri: restUrl, path: "person/${personId}.json")
        def resp = client.post(uri: restUrl)
        assert resp.status == 200
        assert resp.contentType == "application/json"
        def json = resp.data

        then:
        assert json.status == "ERROR"
        assert json.code == "99"
        assert json.message == "INVALID PARAMETES"        
    }
}
