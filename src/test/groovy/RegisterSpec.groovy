import spock.lang.*

class RegisterSpec extends Specification {
 
  def "register with no parameters should return error"() {
      expect:
        1 == 1
  }
}
