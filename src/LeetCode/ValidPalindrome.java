package LeetCode;

public class ValidPalindrome {

    public static String s =  "A man, a plan, a canal: Panama";   // "race a car"; --> false

    public static boolean bruteForce(String s) {
        // The time complexity of this algorithm is 0(N) - replaceAll() & 1 for-loop ---> 0(N) + 0(N) = 0(2N) ---> 0(N)
        String string = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (string.length() < 1) {
            return true;
        }
        int start = string.charAt(0);
        int end = string.length() - 1;

        for (int i = 0; i < string.length(); i++) {
//            System.out.println("loop starting");
//            System.out.println("start: " + start);
//            System.out.println("end: " + end);
                start = string.charAt(i);
                end = string.charAt((string.length() - 1) - i);
                if (start != end) {
                    return false;
                }
        }
        return true;
    }

    public static boolean alphaNum(char c) {
        int ascii = c;
        if ((65 <= ascii && ascii <= 90) ||
                (97 <= ascii && ascii <= 122) ||
                (48 <= ascii && ascii <= 57) ) {
            return true;
        }
        return false;
    }

    public static boolean withoutReplaceAll(String s) {
        if (s.length() < 1) {
            return true;
        }

        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            while (start < end && !alphaNum(s.charAt(start))) {
                start += 1;
            }
            while (end > start && !alphaNum(s.charAt(end))) {
                end -= 1;
            }
            if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
                return false;
            }
            start += 1;
            end -= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(bruteForce(s));
//        System.out.println(withoutReplaceAll(s));
    }
}
