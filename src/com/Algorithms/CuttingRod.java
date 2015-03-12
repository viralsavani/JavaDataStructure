package com.Algorithms;

/**
 * Created by VIRAL on 3/12/2015.
 */
public class CuttingRod {

    public static long startTime = 0;
    public static long endTime = 0;

    public static void main(String[] args) {

        int[] priceArray = {0,3,5,10,12,14};
        int rodLength = 5;

        System.out.print("Using Recursion :: ");
        startTime = System.nanoTime();
        System.out.print(cuttingRodRecursive(priceArray, rodLength));
        endTime = System.nanoTime();
        System.out.println("\t Time taken(nanosecond) :: " + (endTime-startTime) );


        System.out.print("Using Bottom Up :: ");
        startTime = System.nanoTime();
        System.out.print(cuttingRodBottomUp(priceArray, rodLength));
        endTime = System.nanoTime();
        System.out.println("\t Time taken(nanosecond) :: " + (endTime-startTime) );

    }

    public static int cuttingRodRecursive(int[] price, int piece) {
        int maxFound = Integer.MIN_VALUE;
        if(piece == 0){
            return 0;
        }else{
            for (int i = 1; i <= piece; i++){
                maxFound = Math.max(maxFound, price[i]+cuttingRodRecursive(price,piece-i));
            }
        }
        return maxFound;
    }

    public static int cuttingRodBottomUp(int[] price, int piece){
        int[] revenueArray = new int[piece+1];
        revenueArray[0] = 0;

        for (int i = 1; i <= piece; i++){
            int maxFound = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++){
                maxFound = Math.max(maxFound, price[j]+revenueArray[i-j]);
            }
            revenueArray[i] = maxFound;
        }
        return revenueArray[piece];
    }
}
