import java.util.*;
import java.util.stream.Stream;

/**
 * Create a RomanNumerals class that can convert a roman numeral to and from an integer value. It should follow the API demonstrated in the examples below. Multiple roman numeral values will be tested for each helper method.
 *
 * Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero.
 * In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
 * 2008 is written as 2000=MM, 8=VIII; or MMVIII.
 * 1666 uses each Roman symbol in descending order: MDCLXVI.
 *
 * Input range : 1 <= n < 4000
 *
 * In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").
 * Examples
 *
 * RomanNumerals.toRoman(1000) // should return 'M'
 * RomanNumerals.fromRoman("M") // should return 1000
 *
 * Help
 * Symbol 	Value
 * I 	1
 * IV 	4
 * V 	5
 * X 	10
 * L 	50
 * C 	100
 * D 	500
 * M 	1000
 */
public class RomanNumerals {

//    public static void main(String[] args) {
//        System.out.println(fromRoman("MCMXC")); //1990
//        System.out.println(fromRoman("MMVIII")); //2008
//        System.out.println(fromRoman("MDCLXVI")); //1666
//    }

    public static void main(String[] args) {
        System.out.println(toRoman(1990));
        System.out.println(toRoman(2008));
        System.out.println(toRoman(1666));
    }


    public static String toRoman(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            for (Map.Entry<Integer, String> entry : toRomanMap().entrySet()) {
                if (n >= entry.getKey()) {
                    sb.append(entry.getValue());
                    n -= entry.getKey();
                    break;
                }
            }
        }

        return sb.toString();
    }

    public static Map<Integer, String> toRomanMap() {
        Map<Integer, String> map = new TreeMap<Integer, String>(Collections.reverseOrder());
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        return map;
    }

    public static int fromRoman(String romanNumeral) {
        char[] letters = romanNumeral.toCharArray();
        List<Integer> numbers = new LinkedList<>();
        for (char letter : letters) {
            numbers.add(fromRomanMap.get(String.valueOf(letter)));
        }
        Collections.reverse(numbers);
        int finalNumber = 0;
        int tmp = 0;
        for (Integer number : numbers) {
            if (number >= tmp) {
                finalNumber += number;
            } else {
                finalNumber -= number;
            }
            tmp = number;
        }
        return finalNumber;
    }

    public static Map<String, Integer> fromRomanMap = Map.of(
            "I", 1,
            "V", 5,
            "X", 10,
            "L", 50,
            "C", 100,
            "D", 500,
            "M", 1000);


}
