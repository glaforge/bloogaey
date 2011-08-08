import com.ocpsoft.pretty.time.PrettyTime

categories PrettyTimeCategory

class PrettyTimeCategory {
    static String pretty(Date date) {
        new PrettyTime().format(date)
    }
}