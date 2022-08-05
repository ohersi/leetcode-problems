package LeetCode;

import java.util.*;

public class CarFleet {

    public static int target = 12; // Miles until destination

    // Indices of both arrays match the same car - E.g. position[1] & speed[1] = same car
    public static int[] position = {10,8,0,5,3}; // Miles driven so far
    public static int[] speed = {2,4,1,1,3}; // Miles per hour
    //  int n (number of cars) = length of position or speed array

    // CONSTRAINTS
        // Car cannot past car ahead of it, they can go same speed
        // Faster car will slow down to match slower cars speed
        // Distance between two cars is ignored, assumed to be in same position
        // Car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.
        // If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.

    // GOAL
        // Return the number of car fleets that will arrive at the destination.

    // NOTES
        // Position is how many miles the car has driven - E.g. position[0] = 10 - 10 miles driven - 12 - 10 = 2 miles until destination
        // Speed is greater than the distance between the two cars, it will catch up to the car ahead
            // E.g. position[1] = 8 going speed[1] = 4 ---> catch up to car position[0] = 10 going speed[1] = 2

    public static int stack(int target, int[] position, int[] speed) {

        Stack<Double> stack = new Stack<>();

        int[][] car = new int[position.length][2];

        // Combine position and speed into array
        for (int i = 0; i < position.length; i++) {
            car[i][0] = position[i];
            car[i][1] = speed[i];
        }

        Arrays.sort(car, (a, b) -> a[0] - b[0]); // Sorting positions; miles driven from smallest to largest [0, 3, 5, 8, 10] --->
                                                    // Since cars are traveling forwards, # at the end of the array is the car in front

        int carFleets;

        // Loop from car with most distance traveled to least
        for (int i = car.length - 1; i >= 0; i--) {
            // Calculate time until destination reached...
            // Time remaining till car reaches destination: destination - miles traveled / speed
                // Lower the timeRemaining the faster the car will reach the destination
            double timeRemaining = ((target - car[i][0]) * 1.0) / car[i][1]; // keep decimals
        System.out.println("Car driven: " + car[i][0] + " going: " + car[i][1] + " will reach in: " + timeRemaining);

        // If current car time remaining is less than/equal to car in front(aka the top of stack) - do not add it to the stack
            if (!stack.isEmpty() && timeRemaining <= stack.peek()) {
                // Skip since its will catch up and join the car ahead as a single fleet - cars will now go at the same speed as well
                continue;
            }
            else {
                stack.push(timeRemaining);
            }

            System.out.println(stack);
        }

        carFleets = stack.size();

        return carFleets;
    }

    public static void main(String[] args) {
        System.out.println("Number of car fleets: " + stack(target, position, speed));
    }
}
