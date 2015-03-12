package com.Algorithms;

/**
 * Created by VIRAL on 3/12/2015.
 */
public class CuttingRod {
    public static void main(String[] args) {

        int[] priceArray = {0,1,5,8,9,10,17,17,20};
        int rodLength = 4;

        System.out.println(cuttingRodRecursive(priceArray,rodLength));

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
}
