package LeetCode;

public class BestTimeToBuyAndSellStock {
    public static int[] price = {7,1,5,3,6,4};
    public static int slidingWindow(int[] price) {
        // Time complexity of this algorithm is 0(N) - length of the price array
        // Space complexity is 0(1) - no extra space other than variables; number of variables remains constant
        int left = 0;
        int right = left + 1;
        int profit = 0;

        while (right < price.length) {
            // Check right - left is greater than profit
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
        }
        return profit;
    }


    public static void main(String[] args) {
        System.out.println("Maximum profit: " + slidingWindow(price));
    }
}
