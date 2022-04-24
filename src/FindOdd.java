import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers, find the one that appears an odd number of times.
 *
 * There will always be only one integer that appears an odd number of times.
 * Examples
 *
 * [7] should return 7, because it occurs 1 time (which is odd).
 * [0] should return 0, because it occurs 1 time (which is odd).
 * [1,1,2] should return 2, because it occurs 1 time (which is odd).
 * [0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
 * [1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).
 */
public class FindOdd {

    public static void main(String[] args) {
        System.out.println(findIt(new int[]{20, 1, -1, 2, -2, 3, 3, 5, 5, 1, 2, 4, 20, 4, -1, -2, 5}));
        System.out.println(findIt(new int[]{1, 1, 2, -2, 5, 2, 4, 4, -1, -2, 5}));
        System.out.println(findIt(new int[]{20, 1, 1, 2, 2, 3, 3, 5, 5, 4, 20, 4, 5}));
        System.out.println(findIt(new int[]{10}));
        System.out.println(findIt(new int[]{1, 1, 1, 1, 1, 1, 10, 1, 1, 1, 1}));
        System.out.println(findIt(new int[]{5, 4, 3, 2, 1, 5, 4, 3, 2, 10, 10}));
//        assertEquals(5, FindOdd.findIt(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
//        assertEquals(-1, FindOdd.findIt(new int[]{1,1,2,-2,5,2,4,4,-1,-2,5}));
//        assertEquals(5, FindOdd.findIt(new int[]{20,1,1,2,2,3,3,5,5,4,20,4,5}));
//        assertEquals(10, FindOdd.findIt(new int[]{10}));
//        assertEquals(10, FindOdd.findIt(new int[]{1,1,1,1,1,1,10,1,1,1,1}));
//        assertEquals(1, FindOdd.findIt(new int[]{5,4,3,2,1,5,4,3,2,10,10}));
    }

    public static int findIt(int[] a) {
        List<Integer> list = new ArrayList<>();

        for (int single : a) {
            if (!list.contains(single)) {
                list.add(new Integer(single));
            } else {
                list.remove(new Integer(single));
            }
        }

        return list.get(0);
    }


}
