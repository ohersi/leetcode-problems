package LeetCode;

public class ValidPalindrome {

    public static String s = "A man, a plan, a canal: Panama";  //  "race a car"; --> false

    public static boolean bruteForce(String s) {
        // The time complexity of this algorithm is 0(N) - replaceAll() & 1 for-loop ---> 0(N) + 0(N) = 0(2N) ---> 0(N)
        String string = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (string.length() < 1) {
           return true;
        }

        char start;
        char end;

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

    public static void main(String[] args) {
        System.out.println(bruteForce(s));
    }
}
