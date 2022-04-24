import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any whitespace at the end of the line should also be stripped out.
 *
 * Example:
 *
 * Given an input string of:
 *
 * apples, pears # and bananas
 * grapes
 * bananas !apples
 *
 * The output expected would be:
 *
 * apples, pears
 * grapes
 * bananas
 *
 * The code would be called like so:
 *
 * var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
 * // result should == "apples, pears\ngrapes\nbananas"
 */
public class StripCommentsV2 {
    public static void main(String[] args) {
        //"apples, pears\ngrapes\nbananas"
        System.out.println(stripComments("apples, pears      # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));
//        "a\nc\nd"
        System.out.println(stripComments("a #b\nc\nd $e f g", new String[]{"#", "$"}));

        System.out.println(stripComments("apples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));

        System.out.println(stripComments("bf\n" +
                "[\n\n\n\n" +
                "bf\n" +
                "\n" +
                "d\n" +
                "\n" +
                "a\n" +
                "\n" +
                "c\n" +
                "\n" +
                "da\n", new String[]{"#", "$"}));
    }

    public static String stripComments(String text, String[] commentSymbols) {
        String regex = "(?=";
        regex += Arrays.stream(commentSymbols).map(el -> el = java.util.regex.Pattern.quote(el)).collect(Collectors.joining(")|(?=")) + ")|(?=\n)";

        String[] array = text.split(regex);

        boolean shouldDeleteElement;
        for (int i = 0; i < array.length; i++) {
            String element = array[i];

            shouldDeleteElement = false;
            for (String commentSymbol : commentSymbols) {
                if (element.contains(commentSymbol)) shouldDeleteElement = true;
            }

            if (!element.equals("\n")) {
                array[i] = element.stripTrailing();
            }
            if (shouldDeleteElement) {
                array[i] = "";
            }
        }

        return String.join("", array);
    }
}
