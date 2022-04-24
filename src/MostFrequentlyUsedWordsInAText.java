import java.io.FilterOutputStream;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Write a function that, given a string of text (possibly with punctuation and line-breaks), returns an array of the top-3 most occurring words, in descending order of the number of occurrences.
 * Assumptions:
 *
 *     A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
 *     Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
 *     Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
 *     Matches should be case-insensitive, and the words in the result should be lowercased.
 *     Ties may be broken arbitrarily.
 *     If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned, or an empty array if a text contains no words.
 *
 * Examples:
 *
 * top_3_words("In a village of La Mancha, the name of which I have no desire to call to
 * mind, there lived not long since one of those gentlemen that keep a lance
 * in the lance-rack, an old buckler, a lean hack, and a greyhound for
 * coursing. An olla of rather more beef than mutton, a salad on most
 * nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
 * on Sundays, made away with three-quarters of his income.")
 * # => ["a", "of", "on"]
 *
 * top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
 * # => ["e", "ddd", "aa"]
 *
 * top_3_words("  //wont won't won't")
 * # => ["won't", "wont"]
 *
 * For java users, the calls will actually be in the form: TopWords.top3(String s), expecting you to return a List<String>.
 * Bonus points (not really, but just for fun):
 *
 *     Avoid creating an array whose memory footprint is roughly as big as the input text.
 *     Avoid sorting the entire array of unique words.
 */
public class MostFrequentlyUsedWordsInAText {


    public static void main(String[] args) {
        String first = "In a village of La Mancha, the name of which I have no desire to call to" +
                "mind, there lived not long since one of those gentlemen that keep a lance" +
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for" +
                "coursing. An olla of rather more beef than mutton, a salad on most" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra" +
                "on Sundays, made away with three-quarters of his income.";
        System.out.println(top3(first));
        String second = "  //wont won't won't ";
        System.out.println(top3(second));
        System.out.println(top3("  , e   .. "));
        System.out.println(top3("  ...  "));
        System.out.println(top3("  '  "));
        System.out.println(top3("  '''  "));
    }


    public static List<String> top3(String s) {
        Map<String, Integer> mapWithCounter = new HashMap<>();
        String withoutPunctuation = s.replaceAll("[\\W&&[^']]", " ").replace("_", " ").toLowerCase(Locale.ROOT);
        for (String splitted : withoutPunctuation.split(" ")) {
            if (mapWithCounter.containsKey(splitted)) {
                Integer count = mapWithCounter.get(splitted);
                mapWithCounter.put(splitted, ++count);
            } else if (!splitted.isEmpty() && !splitted.replaceAll("'", "").isEmpty()) {
                mapWithCounter.put(splitted, 1);
            }
        }

        List<Integer> top3Counters = new ArrayList<>(mapWithCounter.values()).stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        List<String> top3Words = new ArrayList<>();
        for (Integer counter : top3Counters) {
            top3Words.add(getKey(mapWithCounter, counter));
        }

        return top3Words;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        K key = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
                break;
            }
        }
        map.remove(key);
        return key;
    }

}
