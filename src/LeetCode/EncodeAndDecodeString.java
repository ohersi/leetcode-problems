package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeString {

    static List<String> str = Arrays.asList("lint","code","love","you");
        // Failed - if word characters are greater than 9 or have (n)# in front it will break
    public static String encode(List<String> str) {
        String concat = new String();

        for (String word : str) {
            int length = word.length();
            concat += length + ('#' + word);
        }
        System.out.println(concat);
        System.out.println(str);
        return concat;
    }

    public static void decode(String string) {

            List<String> output = new ArrayList<>();

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '#' && Character.isDigit(string.charAt(i - 1))) {
                    String word = new String();
                    int wordLength = Character.getNumericValue(string.charAt(i - 1));
                   for (int j = i + 1; j <= wordLength + i; j++) {
                       word += string.charAt(j);
                   }
//                   System.out.println(word);
                   output.add(word);
                }
            }
            System.out.println(output);
    }

    public static void main(String[] args) {
//        encode(str);
        decode(encode(str));
    }
}
