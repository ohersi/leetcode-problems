package LeetCode;

import java.util.*;

public class GroupAnagrams {

    public static String[] strs = {"eat","tea","tan","ate","nat","bat"};

    public static void map(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();
        ArrayList<List<String>> arrayList = new ArrayList<>();

        // For every string in array...
        for (String s : strs) {

            // Break the string into an array of characters --> "eat" ---> ['e','a','t']
           char[] chars = s.toCharArray();

           // Sort each character array ---> ['e','a','t'] ---> ['a','e','t']
            Arrays.sort(chars);

            // Covert the character array back into a string ---> ['a','e','t'] ---> "aet"
            String keyStr = String.valueOf(chars);

            // If hashmap does not contain the string, add it to a new array
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            // Add the actual unsorted string from original array as a value to the key in the hashmap
                // E.g. map.(aet <--- keyStr).add(eat <-- original string)
            map.get(keyStr).add(s);
        }
//        final map ---> {aet=[eat, tea, ate], abt=[bat], ant=[tan, nat]}
//        System.out.println(map);

        // Loop through values of hashmap (an array of strings), add to arraylist
        for (List<String> string : map.values()) {
            arrayList.add(string);
        }
       System.out.println(arrayList);
    }

    public static void main(String[] args) {
        map(strs);
    }
}
