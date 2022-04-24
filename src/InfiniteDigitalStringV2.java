import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The position of a digital string in a infinite digital string
 *
 * Description:
 *
 * There is a infinite string. You can imagine it's a combination of numbers from 1 to n, like this:
 *
 * "123456789101112131415....n-2n-1n"
 *
 * Please note: the length of the string is infinite. It depends on how long you need it(I can't offer it as a argument, it only exists in your imagination) ;-)
 *
 * Your task is complete function findPosition that accept a digital string num. Returns the position(index) of the digital string(the first appearance).
 *
 * For example:
 *
 * findPosition("456") == 3
 * because "123456789101112131415".indexOf("456") = 3
 *             ^^^
 *
 * Is it simple? No, It is more difficult than you think ;-)
 *
 * findPosition("454") = ?
 * Oh, no! There is no "454" in "123456789101112131415",
 * so we should return -1?
 * No, I said, this is a string of infinite length.
 * We need to increase the length of the string to find "454"
 *
 * findPosition("454") == 79
 * because "123456789101112131415...44454647".indexOf("454")=79
 *                                    ^^^
 *
 * The length of argument num is 2 to 15. So now there are two ways: one is to create a huge own string to find the index position; Or thinking about an algorithm to calculate the index position.
 *
 * Which way would you choose? ;-)
 * Some examples:
 *
 *  findPosition("456") == 3
 *  ("...3456...")
 *        ^^^
 *  findPosition("454") == 79
 *  ("...444546...")
 *         ^^^
 *  findPosition("455") == 98
 *  ("...545556...")
 *        ^^^
 *  findPosition("910") == 8
 *  ("...7891011...")
 *         ^^^
 *  findPosition("9100") == 188
 *  ("...9899100101...")
 *          ^^^^
 *  findPosition("99100") == 187
 *  ("...9899100101...")
 *         ^^^^^
 *  findPosition("00101") == 190
 *  ("...99100101...")
 *          ^^^^^
 *  findPosition("001") == 190
 *  ("...9899100101...")
 *            ^^^
 *  findPosition("123456789") == 0
 *  findPosition("1234567891") == 0
 *  findPosition("123456798") == 1000000071
 *
 * A bit difficulty, A bit of fun, happy coding ;-)
 */
public class InfiniteDigitalStringV2 {


    public static void main(String[] args) {
//        System.out.println(findPosition("456")); //3
////        4 5 6
//        System.out.println(findPosition("454")); //79
////        //45 46
//        System.out.println(findPosition("455")); //98
////        54 55
//        System.out.println(findPosition("910")); //8
//        // 9 10
//        System.out.println(findPosition("9100")); //188
//        //99 100
//        System.out.println(findPosition("99100")); //187
//        //99 100
//        System.out.println(findPosition("00101")); //190
//        // 100 101
//        System.out.println(findPosition("001")); //190
//        //100 101
//        System.out.println(findPosition("00")); //190
//        //100 101
//        System.out.println(findPosition("123456789")); //0
//        //1 2 3 4
//        System.out.println(findPosition("1234567891")); //0
        //1 2 3 4
        System.out.println(findPosition("123456798")); //1000000071

    }

    //too slow
    public static long findPosition(final String s) {
        int incomingStringLength = s.length();
        LinkedList<Integer> fifo = new LinkedList<>();
        for (char c : s.toCharArray()) {
            fifo.add(Character.getNumericValue(c));
        }

        int indexOfDigit = 1;
        int iterator = 1;
        LinkedList<Integer> iteratorList = new LinkedList<>();
        while (true) {
            if (iterator < 10) {
                if (iteratorList.size() > 0 && iteratorList.size() >= incomingStringLength) {
                    iteratorList.remove(0);
                }
                iteratorList.add(iterator);
                indexOfDigit++;
                if (iteratorList.equals(fifo)) return indexOfDigit - incomingStringLength - 1;
            } else {
                Integer[] digits = getDigits(iterator);
                for (Integer digit : digits) {
                    if (iteratorList.size() > 0 && iteratorList.size() >= incomingStringLength) {
                        iteratorList.remove(0);
                    }
                    iteratorList.add(digit);
                    indexOfDigit++;

                    if (iteratorList.equals(fifo)) {
                        return indexOfDigit - incomingStringLength - 1;
                    }
                }
            }


            iterator++;
        }

    }

    public static Integer[] getDigits(int num) {
        List<Integer> digits = new ArrayList<Integer>();
        collectDigits(num, digits);
        return digits.toArray(new Integer[]{});
    }

    private static void collectDigits(int num, List<Integer> digits) {
        if (num / 10 > 0) {
            collectDigits(num / 10, digits);
        }
        digits.add(num % 10);
    }


    private static long numberLength(long number) {
        //divide and conquer
        if (number < 100000) {
            if (number < 100) {
                if (number < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (number < 1000) {
                    return 3;
                } else {
                    if (number < 10000) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        } else {
            if (number < 10000000) {
                if (number < 1000000) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                if (number < 100000000) {
                    return 8;
                } else {
                    if (number < 1000000000) {
                        return 9;
                    } else {
                        return 10;
                    }
                }
            }
        }
    }
}
