package LeetCode;

public class BestTimeToBuyAndSellStock {
    public static int[] price = {7,1,5,3,6,4};
    public static void bruteForce(int[] price) {
        // Time complexity of this algorithm is 0(N) - length of the price array
        // Space complexity is 0(1) - no extra space other than variables; number of variables remains constant
        int left = 0;
        int right = left + 1;
        int profit = 0;

        if (price.length <= 1) {
            System.out.println("profit is:" + profit);
        }

        if (right == price.length - 1) {
            if (price[right] - price[left] > profit) {
                profit = price[right] - price[left];
            }
        }

        while (right < price.length - 1) {
            // Check initial right - left is greater than profit
            if (price[right] - price[left] > profit) {
                profit = price[right] - price[left];
            }
            // If next day price is lower than current, move left pointer to lower number/price
            // Trying to maximize profit, so lower buying price is beneficial
            if (price[right] < price[left]) {
                left = right;
            }
            // Move right pointer (selling price) to maximize profit, higher the number compared to buying price the better
            right += 1;
            // Check new right - left is greater than profit
            if (price[right] - price[left] > profit) {
                profit = price[right] - price[left];
            }
            System.out.println("left: " + price[left]);
            System.out.println("right: " + price[right]);
        }
        System.out.println("profit: " + profit);
    }

    public static void main(String[] args) {
        bruteForce(price);
    }
}
