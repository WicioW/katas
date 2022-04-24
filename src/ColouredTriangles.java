import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * If you finish this kata, you can try Insane Coloured Triangles by Bubbler, which is a much harder version of this one.
 *
 * A coloured triangle is created from a row of colours, each of which is red, green or blue. Successive rows, each containing one fewer colour than the last, are generated by considering the two touching colours in the previous row. If these colours are identical, the same colour is used in the new row. If they are different, the missing colour is used in the new row. This is continued until the final row, with only a single colour, is generated.
 *
 * The different possibilities are:
 *
 * Colour here:        G G        B G        R G        B R
 * Becomes colour:      G          R          B          G
 *
 * With a bigger example:
 *
 * R R G B R G B B
 *  R B R G B R B
 *   G G B R G G
 *    G R G B G
 *     B B R R
 *      B G R
 *       R B
 *        G
 *
 * You will be given the first row of the triangle as a string and its your job to return the final colour which would appear in the bottom row as a string. In the case of the example above, you would the given RRGBRGBB you should return G.
 *
 *     The input string will only contain the uppercase letters R, G, B and there will be at least one letter so you do not have to test for invalid input.
 *     If you are only given one colour as the input, return that colour.
 *
 * Adapted from the 2017 British Informatics Olympiad
 */

public class ColouredTriangles {

    public static void main(String[] args) {
//        System.out.println(triangle("GB"));   //R
//        System.out.println(triangle("RRR"));  //R
//        System.out.println(triangle("RGBG")); //B
//        System.out.println(triangle("RBRGBRB")); //G
//        System.out.println(triangle("RBRGBRBGGRRRBGBBBGG")); //G
        System.out.println(triangle("B")); //B
    }


    public static char triangle(final String row) {
        // Return the answer
        if (row.length() == 1) return row.charAt(0);
        String temp = row;
        while (temp.length() > 1) {
            List<String> splitted = split(temp, 2);
            temp = splitted.stream().map(s -> calculateCharFromStringLengthTwo(s)).map(String::valueOf).collect(Collectors.joining());
//            System.out.println(temp);
        }
        return temp.charAt(0);
    }

    private static List<String> split(String str, int interval) {
        if (str.length() <= interval) {
            return List.of(str);
        }
        var subStrings = new ArrayList<String>();
        int pointer = 0;
        while ((str.length() - 1) > pointer) {
//            String substring = str.substring(pointer, pointer + interval);
            String substring = str.substring(pointer, Math.min(pointer + interval, str.length()));
            subStrings.add(substring);
//            pointer += interval;
            pointer++;
        }
        return subStrings;
    }

    private static char calculateCharFromStringLengthTwo(String s) {
        if (s.length() > 2) System.out.println("error string too long");
        if (s.length() == 1) return s.charAt(0);

        if (s.replaceAll("R", "").isEmpty()) return 'R';
        if (s.replaceAll("G", "").isEmpty()) return 'G';
        if (s.replaceAll("B", "").isEmpty()) return 'B';

        if (s.contains("R")) {
            if (s.contains("G")) return 'B';
            if (s.contains("B")) return 'G';
        }
        if (s.contains("B")) {
            if (s.contains("G")) return 'R';
        }

        return '?';
    }


}
