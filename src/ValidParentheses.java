/**
 * Write a function that takes a string of parentheses, and determines if the order of the parentheses is valid. The function should return true if the string is valid, and false if it's invalid.
 * Examples
 *
 * "()"              =>  true
 * ")(()))"          =>  false
 * "("               =>  false
 * "(())((()())())"  =>  true
 *
 * Constraints
 *
 * 0 <= input.length <= 100
 *
 * Along with opening (() and closing ()) parenthesis, input may contain any valid ASCII characters. Furthermore, the input string may be empty and/or not contain any parentheses at all. Do not treat other forms of brackets as parentheses (e.g. [], {}, <>).
 */
public class ValidParentheses {

    public static void main(String[] args) {
//        assertEquals(true,Solution.validParentheses( "()" ));
//        assertEquals(false,Solution.validParentheses( "())" ));
//        assertEquals(true,Solution.validParentheses( "32423(sgsdg)" ));
//        assertEquals(false,Solution.validParentheses( "(dsgdsg))2432" ));
//        assertEquals(true,Solution.validParentheses( "adasdasfa" ));

        System.out.println(validParentheses("()")); //true
        System.out.println(validParentheses("())")); //false
        System.out.println(validParentheses("32423(sgsdg)")); //true
        System.out.println(validParentheses("(dsgdsg))2432")); //false
        System.out.println(validParentheses("adasdasfa")); //true
    }

    public static boolean validParentheses(String parens) {
        //Put code below
        char[] chars = parens.toCharArray();
        int sum = 0;

        for (char c : chars) {
            if ('(' == c) {
                sum++;
            }
            if (')' == c) {
                sum--;
            }

            if (sum < 0) {
                return false;
            }
        }

        return sum == 0;
    }

}
