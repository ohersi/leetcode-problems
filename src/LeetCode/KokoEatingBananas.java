package LeetCode;

public class KokoEatingBananas {

    public static int[] piles = {30,11,23,4,20};
    public static int allottedHours = 5;

    // Banana-per-hour-eating speed = k.
    // She can only eat one pile at a time, so even if k (# of bananas she can eat in an hour)...
        // is greater than the size of the pile, she would have to stop for the hour
    // Since Koko can only eat one pile an hour, the number of piles has to be less than or equal to the hours given.
        // E.g. 5 piles, 4 hours to eat them --> cannot be done in time

    // If Koko was able to eat the max # of bananas an hour (k)...
    // The 1 pile an hour constraint means the shortest amount of time (in hours) to eat all piles is the # of piles
    // E.g. 4 piles (at max speed) = 4 hours - the shortest time; she cannot finish any faster

    // Ideally Koko wants to finish every pile in the given hour, so the # of k she needs to eat to achieve that...
    // is the size of the largest pile E.g. 11 largest pile, k = 11 ---> 0 bananas remaining for the hour
    // 11 is the most she can eat to achieve the fastest time, anything more is redundant...
        // since only 1 pile per hour constraint - eating 11 already achieves 0 remaining in the pile

    // GOAL
    // Since Koko wants to eat slowly, find the minimum # of bananas that can be eaten to still finish all by the allowed time.

    public static int search(int[] piles, int allottedHours) {

        // Find the largest pile in array; max k needed to achieve the shortest time
        int maxPile = 0;

        for (int i = 0; i < piles.length; i++) {
            maxPile = Math.max(maxPile, piles[i]);
        }

        // BINARY SEARCH
        int low = 0;
        int high = maxPile; // Checking every possible k, from 1 banana per hour (bph) to max - E.g. 1 bph to 11 bph

        int result = maxPile; // Start off with max bananas that can be eaten...
                                // Looking for min k that still results in all piles eaten in the allotted time

        while (low <= high) {

            int midpoint = low + (high - low) / 2;

            // Total hours spent eating all piles of bananas
            int hoursSpentEating = 0;
            // Loop through each pile, find time takes to eat each one - add all together
            for (int i = 0; i < piles.length; i++) {
                hoursSpentEating += Math.ceil((double) piles[i] / midpoint);
//                System.out.println("piles[i]: " + piles[i] + " / " + "midpoint: " + midpoint + " - total eaten so far: " + hoursSpentEating);
            }

//            System.out.println("_________________");

            // If hours spend eating is less than allotted time, minimum k still not found
                // Koko finished all piles with time remaining, she can eat slower...
                // min k is on the left side of midpoint, shift high to midpoint
            if (hoursSpentEating <= allottedHours) {
                result = Math.min(result, midpoint);
                // midpoint - 1 since the number at midpoint was already checked
                high = midpoint - 1;
            }
            // Hours spent eating is greater than allotted time, Koko eating too slow
            // min k is on the right side of midpoint, shift left to midpoint
            else {
                low = midpoint + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("min k is: " + search(piles, allottedHours));
    }
}
