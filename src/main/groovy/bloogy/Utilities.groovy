package bloogy

import java.text.Normalizer

class Utilities {

    /**
     * Transform a post article into a simplified url-friendly string.
     * "Cool! A groovy, very groovy, title :-)" is transformed into "cool-a-groovy-very-groovy-title
     */
    static String streamline(String title) {
        Normalizer.normalize(title, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
            .replaceAll(/\W+/, '-')
            .replaceAll(/(-+$|^-+)/, '')
            .toLowerCase()
    }
}

