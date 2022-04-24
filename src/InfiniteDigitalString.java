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
public class InfiniteDigitalString {


    public static void main(String[] args) {
//        System.out.println(findPosition("456")); //3
//        4 5 6
//        System.out.println(findPosition("454")); //79
////        //45 46
//        System.out.println(findPosition("455")); //98
////        54 55
//        System.out.println(findPosition("910")); //8
        // 9 10
        System.out.println(findPosition("9100")); //188
        //99 100
        System.out.println(findPosition("99100")); //187
        //99 100
        System.out.println(findPosition("00101")); //190
        // 100 101
        System.out.println(findPosition("001")); //190
        //100 101
        System.out.println(findPosition("00")); //190
        //100 101
        System.out.println(findPosition("123456789")); //0
        //1 2 3 4
        System.out.println(findPosition("1234567891")); //0
        //1 2 3 4
        System.out.println(findPosition("123456798")); //1000000071

    }

    //too slow
    public static long findPosition(final String s) {
        char firstLetterChar = s.charAt(0);
        boolean finished = false;
        int iterator = 1;
        while (!finished) { //wrong
            String iteratorString = String.valueOf(iterator);

            int indexOfFoundLetter = iteratorString.indexOf(firstLetterChar);
            if (indexOfFoundLetter >= 0) { //found first letter in number
//                outerloop:

                int i = 0;
                outer:
                while (i < s.length()) {
//                    String gener = generateDigitalStringFromIndexWithLength1(iterator, i, s.length() + i);

                    String gener = generateDigitalStringFromIndexWithLength1(iterator, indexOfFoundLetter, s.length() + indexOfFoundLetter, i);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                    int index = iterator;
//                    int start = indexOfFoundLetter;
//                    int end = s.length() + indexOfFoundLetter;
//                    int shift = i;
//                    StringBuilder sb = new StringBuilder();
//                    while (sb.length() < (end + shift)) {
//                        sb.append(index);
//                        index++;
//
//                        String tmp = sb.toString();
//                        if (!tmp.contains(s.substring(0, Math.min(tmp.length(), s.length())))) {
//                            break outer;
//                        }
//                    }
//                    String generatedString = sb.toString();
//                    String gener = generatedString.substring(start + shift, end + shift);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    log("gen: " + gener);
                    if (gener.equals(s)) {
//                        return calculateIndexOfNumberInInfiniteDigitalString(iterator) + i;
                        return calculateIndexOfNumberInInfiniteDigitalString(iterator) + indexOfFoundLetter + i;
                    } else {
//                        podbić iterator
//                        iterator = iterator + indexOfFoundLetter + i;
//                            iterator = iterator + indexOfFoundLetter + s.length() - i;
                    }


                    //tutaj
                    int foundNext = iteratorString.indexOf(firstLetterChar, indexOfFoundLetter + 1);
                    if (foundNext > 0) {
                        i = i + foundNext;
                    } else {
                        break;
                    }

                }

            }
            iterator++;
        }
        return 0L;
    }

    private static String generateDigitalStringFromIndexWithLength1(int index, int start, int end, int shift) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < (end + shift)) {
            sb.append(index);
            index++;
        }
        String generatedString = sb.toString();
        return generatedString.substring(start + shift, end + shift);
    }


    private static long calculateIndexOfNumberInInfiniteDigitalString(long number) {
        //brute force, certainly there is nicer way
        long index = 0;
        for (long i = 1; i < number; i++) {
            index = index + numberLength(i);
        }
        return index;
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


    private static void log(String logMessage) {
//        System.out.println(logMessage);
    }

}
