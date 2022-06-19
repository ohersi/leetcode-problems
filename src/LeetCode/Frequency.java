package LeetCode;

import java.util.*;

class Employees {
    String name;
    int salary;

    public Employees(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class Frequency {

    static int[] nums = new int[]{1, 4, 1, 2, 2, 3, 4, 1, 1, 1, 6, 6, 6, 4, 1, 6, 6, 8};
    static int highestNumberOfFrequencies = 2;

    public static void quadraticTime(int[] nums) {

        // This algorithm is in O (N ^2) time

        int[] visitedArr = new int[nums.length];
        int visited = -1;

        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                    visitedArr[j] = visited;
                }
            }
            if (visitedArr[i] != visited) {
                visitedArr[i] = count;
            }
        }
        for (int i = 0; i < visitedArr.length; i++) {
            if (visitedArr[i] != visited) {
                System.out.println("Frequency of " + nums[i] + " : " + visitedArr[i]);
            }
        }
//        System.out.println(Arrays.toString(visitedArr));
    }

    public static void hashMapVer(int[] nums) {

        // This algorithm is time complexity is O(n log n) - O(n log n + 2n)
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                  map.put(nums[i], map.get(nums[i]) + 1);
            }
            else {
                map.put(nums[i], 1);
            }
        }

        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        List<Integer> list = new ArrayList<>();
        for (int entry : reverseSortedMap.keySet()) {
            if (list.size() >= highestNumberOfFrequencies) break;
            list.add(entry);
        }
        int[] finalArr = new int[list.size()];
        for (int i = 0; i < finalArr.length; i++) {
            finalArr[i] = list.get(i);
        }

//        List<Integer> mapValues = new ArrayList<>(map.values());
//        List<Integer> mapKeys = new ArrayList<>(map.keySet());
////        Collections.sort(mapValues, Collections.reverseOrder());
////        Collections.sort(mapKeys, Collections.reverseOrder());
////        System.out.println(mapKeys);
//        System.out.println(mapValues);
        int[] arrValues = new int [highestNumberOfFrequencies];
//        int[] arrKeys = new int [highestNumberOfFrequencies];
//        for (int i = 0; i < highestNumberOfFrequencies; i++) {
//            arrValues[i] = mapValues.get(i);
//            arrKeys[i] = mapKeys.get(i);
//        }
////        System.out.println("this is arrValues: " + Arrays.toString(arrValues));
////        System.out.println("this is arrKeys: " + Arrays.toString(arrKeys));
//        for (Map.Entry test : map.entrySet()) {
//            if (map.values().containsAll(mapValues)) {
//                System.out.println(test.getKey());
//            }
//        }
//        System.out.println(map.values().containsAll(mapKeys));
        System.out.println(Arrays.toString(finalArr));
        System.out.println(list);
        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
        System.out.println(map);
    }

    public static void priorityQueueVer(int[] nums) {

        // This logarithm time complexity is 0 (n log n), if keep the size of queue within (k aka highestNumberOfFrequencies) elements then its O(k log n)

        Map<Integer, Integer> map = new HashMap<>();
        for (int element : nums) {
            // Add key - value pair, since its initially empty (null), use .getOrDefault() to set its value to 0. So its starts as 1=0, 2=0, 3=0 etc..
            // then since hashmap have unique keys, if an identified key shows up, the value is increased by 1;
            map.put(element, map.getOrDefault(element, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        // Add hashmap entries to the queue
        for (Map.Entry entry : map.entrySet()) {
            queue.add(entry);
            // queue size will equal top k elements
            if (queue.size() > highestNumberOfFrequencies) {
                queue.poll();
            }
        }

        int[] output = new int[highestNumberOfFrequencies];
        for (int i = 0; i < highestNumberOfFrequencies; i++) {
            // PriorityQueue .poll() method retrieves the head (first) of the queue;
            output[i] = queue.poll().getKey();
        }

        System.out.println(map);
        System.out.println(queue);
        System.out.println(Arrays.toString(output));
    }

    public static void bucketSortVer(int[] nums) {

        // This algorithm (Bucket Sort) is O(N) -> O(3N)

        Map<Integer, Integer> bucket = new HashMap<>();
        for (int element : nums) {
            // Add key - value pair, since its initially empty (null), use .getOrDefault() to set its value to 0. So its starts as 1=0, 2=0, 3=0 etc..
            // then since hashmap have unique keys, if an identified key shows up, the value is increased by 1;
            bucket.put(element, bucket.getOrDefault(element, 0) + 1);
        }

        Map<Integer, List<Integer>> listOfBuckets = new HashMap<>();
        for (Integer num : bucket.keySet()) {
            // Frequency of every number, aka the values of the keys in the original map
            Integer frequency = bucket.get(num);
            // If map does not contain the frequency (value of the original map) then add frequency as key, and value as a new list
            if (!listOfBuckets.containsKey(frequency)) {
                listOfBuckets.put(frequency, new ArrayList<>());
            }
            // If frequency is in the second map, add the key of the original map into the list
            listOfBuckets.get(frequency).add(num);
        }

        int[] sortedList = new int[highestNumberOfFrequencies];

        // Loop through the nums array, starting at the end and going down to the last element in the array
        for (int i = nums.length; i > 0; i--) {
            if (listOfBuckets.containsKey(i)) {
                // If new map contains (i) element as a key, create new list of the (i) element from the original map (the values)
               List<Integer> list = listOfBuckets.get(i);
               // Iterate through the elements of each list
                for (Integer integer : list) {
                    if (highestNumberOfFrequencies != 0) {
                        // Add the element to the sortedList array, until k elements (highestNumberOfFrequencies) are present
                        // sortedList starting at last index is equal to integer then down
                        // [--highestNumberOfFrequencies] is same as [highestNumberOfFrequencies = highestNumberOfFrequencies - 1] aka decrementing
                        sortedList[--highestNumberOfFrequencies] = integer;
                    }
                }
            }
        }
        System.out.println(listOfBuckets);
        System.out.println(Arrays.toString(sortedList));
//
//
//        return sortedList;
    }

    public static void pQueue() {

       PriorityQueue<Employees> employeesQeueue = new PriorityQueue<>((a, b) -> b.salary - a.salary);

       employeesQeueue.add(new Employees("Tim", 10));
        employeesQeueue.add(new Employees("Bob", 40));
        employeesQeueue.add(new Employees("Carla", 80));
        employeesQeueue.add(new Employees("Mikal", 30));

        while (!employeesQeueue.isEmpty()) {
            System.out.println(employeesQeueue.poll());
        }
    }


    public static void main(String[] args) {
//       quadraticTime(nums);
//        hashMapVer(nums);
//        priorityQueueVer(nums);
//        bucketSortVer(nums);
//        pQueue();
    }
}
