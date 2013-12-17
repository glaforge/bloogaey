import groovyx.gaelyk.spock.*

class RoutesSpec extends GaelykRoutingSpec {
 
  def setup() {
      routing 'routes.groovy'
  }
   
  def "a get method may be routed"() {
    expect:
      get('/')
  }
   
  def "an incorrectly routed method will not match"() {
    expect:
      !get('/wrongurl')
  }
   
  def "a forward of a mapping may be configured"() {
    expect:
      with get('/gcm/register'), {
        matches
        destination == "/gcm/register.groovy"
        //redirectionType == 'FORWARD'
      }
  }
}
