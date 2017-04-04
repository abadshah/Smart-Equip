package com.alkesh;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Numbers {
    /**
     * Minimum amount of values
     */
    private static final int MINIMUM_NUMBERS = 2;
    /**
     * Maximum amount of values
     */
    private static final int MAXIMUM_NUMBERS = 5;

    /**
     * Returns an array of integers with values between 0 and 9.
     * @return a random array of <code>Numbers.MINIMUM_NUMBERS</code> to <code>Numbers.MINIMUM_NUMBERS</code> integers
     */
    public static int[] generate() {
        Random random = new Random(System.currentTimeMillis());
        int numValsToGenerate = random.ints(Numbers.MINIMUM_NUMBERS, Numbers.MAXIMUM_NUMBERS+1)
                                      .findFirst().getAsInt();
        return random.ints(numValsToGenerate, 0, 10).toArray();
    }

    /**
     * Returns the sum of elements in the array.
     * @param numbers the array to sum
     * @return the sum of elements in this array
     */
    public static int getSum(int... numbers) {
        // if numbers is null, just return 0 instead of throwing a NullPointerException
        if (numbers == null)
            return 0;
        return IntStream.of(numbers).sum();
    }

    /**
     * Checks whether the sum of <code>numbers</code> is equal to <code>answer</code>
     * @param answer the proposed answer
     * @param numbers the array to sum
     * @return true if the sum of <code>numbers</code> is equal to <code>answer</code>.
     */
    public static boolean isCorrectResponse(int answer, int... numbers){
        return (answer == Numbers.getSum(numbers));
    }

    /**
     * Returns a csv as an array of integers
     * @param str string to convert into an array
     * @return an array of integer
     */
    public static int[] stringToArray(String str) {
        int[] intArray;
        try {
            intArray = Arrays.stream(str.split(","))
                                  .map(String::trim)
                                  .mapToInt(Integer::parseInt)
                                  .toArray();
        } catch (Exception e) {
            return null;
        }
        return intArray;
    }
}
