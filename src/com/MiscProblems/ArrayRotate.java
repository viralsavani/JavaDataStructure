package com.MiscProblems;

/**
 * Created by VIRAL on 4/13/2015.
 */

public class ArrayRotate {

    /**
     * In a straightforward way, we can create a new array and then copy elements to the
     * new array. Space is O(n) and time is O(n).
     */
    public static int[] rotateMethod1(int[] array, int rotate){

        if(array == null || rotate < 0){
            throw new IllegalArgumentException("Illegal Argument !");
        }

        if(rotate > array.length){
            rotate = rotate % array.length;
        }

        int[] result = new int[array.length];

        for (int i = 0; i < rotate; i++){
            result[i] = array[array.length - rotate + i];
        }

        int j = 0;
        for (int i = rotate; i < array.length; i++){
            result[i] = array[j];
            j++;
        }

        return result;
    }

    /**
     * This solution is like bubble sort. However, the time is O(n*k).
     * where n = array length
     *       k = rotating value
     */
    public static int[] rotateBubble(int[] array, int rotate){
        if(array == null || rotate < 0){
            throw new IllegalArgumentException("Illegal Argument !");
        }

        for (int i = 0; i < rotate ; i++){
            for (int j = array.length-1; j > 0; j--){
                int temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
            }
        }
        return array;
    }

    /**
     * Divide the array in two parts, rotate the first part, then rotate second
     * part and finally rotate the entire array.
     * O(1) space and in O(n) time
     */
    public static int[] rotateReversal(int[] array, int rotate){
        if(array == null || rotate < 0){
            throw new IllegalArgumentException("Illegal Argument !");
        }

        rotate = rotate % array.length;

        // Length of first part.
        int k = array.length - rotate;

        //First part rotate
        array = arrayReversal(array, 0, k-1);

        // Second part rotate
        array = arrayReversal(array, k, array.length-1);

        // Entire array rotate
        return arrayReversal(array, 0, array.length-1);
    }

    /**
     * Rotate helper method.
     */
    private static int[] arrayReversal(int[] array, int left, int right){
        if(array == null || array.length == 1){
            return null;
        }
        while (left < right){
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }

        return array;
    }
}
