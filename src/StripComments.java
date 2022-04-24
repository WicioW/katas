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
public class StripComments {

    public static void main(String[] args) {
        //"apples, pears\ngrapes\nbananas"
//        System.out.println(stripComments("apples, pears      # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));


//        "a\nc\nd"
//        System.out.println(stripComments("a #b\nc\nd $e f g", new String[]{"#", "$"}));

        System.out.println(stripComments("apples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !applesapples, pears      # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));

    }

    //too slow
    public static String stripComments(String text, String[] commentSymbols) {
        String returnText = text;
        String tmp;

        boolean shouldGo = true;
        while (shouldGo) {
            int firstIndex = returnText.length();
            for (String symbol : commentSymbols) {
                int lowerFirstIndex = returnText.indexOf(symbol);
                if (lowerFirstIndex < firstIndex && lowerFirstIndex != -1) firstIndex = lowerFirstIndex;
            }
            while (Character.isWhitespace(returnText.charAt(firstIndex - 1)))
                firstIndex = firstIndex - 1;
            int secondIndex = -1;
            while (secondIndex < firstIndex) {
                secondIndex = returnText.indexOf("\n", secondIndex + 1);
                if (secondIndex == -1) secondIndex = returnText.length();
            }

            if (secondIndex < firstIndex) secondIndex = returnText.length();
            tmp = returnText.substring(firstIndex, secondIndex);
            returnText = returnText.replace(tmp, "");

            shouldGo = false;
            for (String symbol : commentSymbols) {
                if (returnText.contains(symbol)) shouldGo = true;
            }
        }

        return returnText;
    }


}
