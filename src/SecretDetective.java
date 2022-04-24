import java.security.KeyPair;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * There is a secret string which is unknown to you. Given a collection of random triplets from the string, recover the original string.
 *
 * A triplet here is defined as a sequence of three letters such that each letter occurs somewhere before the next in the given string. "whi" is a triplet for the string "whatisup".
 *
 * As a simplification, you may assume that no letter occurs more than once in the secret string.
 *
 * You can assume nothing about the triplets given to you other than that they are valid triplets and that they contain sufficient information to deduce the original string. In particular, this means that the secret string will never contain letters that do not occur in one of the triplets given to you.
 */
public class SecretDetective {

//    /Recover a secret string from random triplets

    public static void main(String[] args) {

        char[][] triplets = {
                {'t', 'u', 'p'},
                {'w', 'h', 'i'},
                {'t', 's', 'u'},
                {'a', 't', 's'},
                {'h', 'a', 'p'},
                {'t', 'i', 's'},
                {'w', 'h', 's'}
        };

        System.out.println(recoverSecret(triplets));
    }

    public static class CustomPair {
        public char value1;
        public char value2;

        public CustomPair(char value1, char value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    //brute force, there should be easier way
    public static String recoverSecret(char[][] triplets) {
        Set<CustomPair> pairSet = new HashSet<>();
        Set<Character> distinctCharsSet = new HashSet<>();
        for (char[] triplet : triplets) {
            pairSet.add(new CustomPair(triplet[0], triplet[1]));
            pairSet.add(new CustomPair(triplet[1], triplet[2]));
            distinctCharsSet.add(triplet[0]);
            distinctCharsSet.add(triplet[1]);
            distinctCharsSet.add(triplet[2]);
        }

        Character[] characters = distinctCharsSet.toArray(Character[]::new);

        boolean finished = false;
        while (!finished) {
            int foundDiff = 0;
            for (CustomPair customPair : pairSet) {

                int i = 0;
                int lowerIndex = 0;
                int higherIndex = 0;
                for (Character character : characters) {
                    if (character.equals(customPair.value1)) {
                        lowerIndex = i;
                    }
                    if (character.equals(customPair.value2)) {
                        higherIndex = i;
                    }
                    i++;
                }

                //switch places
                if (lowerIndex > higherIndex) {
                    char tmp = characters[lowerIndex];
                    characters[lowerIndex] = characters[higherIndex];
                    characters[higherIndex] = tmp;
                    foundDiff++;
                }

            }

            if (foundDiff == 0) {
                finished = true;
            }

        }

        Stream<Character> charStream = Arrays.stream(characters);
        return charStream.map(String::valueOf).collect(Collectors.joining());
    }


}
