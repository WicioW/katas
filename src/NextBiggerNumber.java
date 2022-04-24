import java.util.Arrays;

/**
 * Create a function that takes a positive integer and returns the next bigger number that can be formed by rearranging its digits. For example:
 *
 * 12 ==> 21
 * 513 ==> 531
 * 2017 ==> 2071
 *
 * nextBigger(num: 12)   // returns 21
 * nextBigger(num: 513)  // returns 531
 * nextBigger(num: 2017) // returns 2071
 *
 * If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift):
 *
 * 9 ==> -1
 * 111 ==> -1
 * 531 ==> -1
 *
 * nextBigger(num: 9)   // returns nil
 * nextBigger(num: 111) // returns nil
 * nextBigger(num: 531) // returns nil
 */
public class NextBiggerNumber {
    public static void main(String[] args) {
        System.out.println(nextBiggerNumber(516731)); //517136
        System.out.println(nextBiggerNumber(10991)); //11099
        System.out.println(nextBiggerNumber(10691)); //10916
        System.out.println(nextBiggerNumber(3857)); //3875
        System.out.println(nextBiggerNumber(4518)); //4581
        System.out.println(nextBiggerNumber(414)); //441
        System.out.println(nextBiggerNumber(144)); //414
        System.out.println(nextBiggerNumber(123)); //132
        System.out.println(nextBiggerNumber(9)); //-1
        System.out.println(nextBiggerNumber(111)); //-1
    }

    public static long nextBiggerNumber(long n) {
        if (n < 10) { //nothing to change
            return -1;
        }
        //long to array
        String tmp = String.valueOf(n);
        int[] array = new int[tmp.length()];
        for (int i = 0; i < tmp.length(); i++) {
            array[i] = Character.getNumericValue(tmp.charAt(i));
        }

        for (int i = array.length - 1; i >= 0; i--) {
            //      System.out.println(array[i]);
            if (i > 0) {
                if (array[i - 1] < array[i]) { //if digit on the left is smaller than digit on the right -> we can rearrange all digits on the right side to bigger number

                    int indexOfTheSmallestDigitOnTheRightSideBiggerThanIMinusOne = i;
                    int smallestRightSideValueBiggerThanIMinusOne = array[i];
                    for (int a = i; a < array.length; a++) {
                        if (array[a] > array[i - 1] && array[a] < smallestRightSideValueBiggerThanIMinusOne) {
                            indexOfTheSmallestDigitOnTheRightSideBiggerThanIMinusOne = a;
                        }
                    }

                    // switch
                    int temp = array[indexOfTheSmallestDigitOnTheRightSideBiggerThanIMinusOne];
                    array[indexOfTheSmallestDigitOnTheRightSideBiggerThanIMinusOne] = array[i - 1];
                    array[i - 1] = temp;

                    //create subarray from digits on the right side
                    int[] subarray = Arrays.copyOfRange(array, i, array.length);
                    Arrays.sort(subarray); //sort ascending

                    //override sorted numbers
                    int subarrayIndex = 0;
                    for (int j = i; j < array.length; j++) {
                        array[j] = subarray[subarrayIndex];
                        subarrayIndex++;
                    }

                    // end
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder(); //back to long
        for (int i : array) {
            sb.append(i);
        }
        long finishedLong = Long.parseLong(sb.toString());
        if (n == finishedLong) { //finished long the same as incoming long
            return -1;
        }
        return finishedLong;
    }


    //MARKED AS BETTER SOLUTION
    public static long betternextBiggerNumber(long n) {
        char[] s = String.valueOf(n).toCharArray();
        for (int i = s.length - 2; i >= 0; i--) {
            for (int j = s.length - 1; j > i; j--) {
                if (s[i] < s[j]) {
                    char tmp = s[i];
                    s[i] = s[j];
                    s[j] = tmp;
                    Arrays.sort(s, i + 1, s.length);
                    return Long.valueOf(String.valueOf(s));
                }
            }
        }
        return -1;
    }

}
