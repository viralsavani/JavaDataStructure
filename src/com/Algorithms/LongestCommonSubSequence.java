package com.Algorithms;

/**
 * Created by VIRAL on 3/23/2015.
 */
public class LongestCommonSubSequence {

    public static String lcs(String string1, String string2){
        if(string1.equals("") || string2.equals("")){
            return "";
        }

        int[][] lcsMatrix = new int[string1.length()+1][string2.length()+1];

        for (int i = 0; i < string1.length(); i++){
            for (int j = 0; j <string2.length(); j++){
                if(string1.charAt(i) == string2.charAt(j)){
                    lcsMatrix[i+1][j+1] = lcsMatrix[i][j]+1;
                }else{
                    lcsMatrix[i+1][j+1] = Math.max(lcsMatrix[i+1][j], lcsMatrix[i][j+1]);
                }
            }
        }

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = string1.length(), j = string2.length(); i != 0 && j != 0; ){
            if(lcsMatrix[i][j] == lcsMatrix[i-1][j]){
                i--;
            }else if(lcsMatrix[i][j] == lcsMatrix[i][j-1]){
                j--;
            }else{
                assert string1.charAt(i-1) == string2.charAt(j-1);
                stringBuffer.append(string1.charAt(i-1));
                i--;
                j--;
            }
        }
        return stringBuffer.reverse().toString();
    }

    public static void main(String[] args) {

        String string1 = "JAVA is a programming language";
        String string2 = "JAVA is an object oriented language";
        System.out.println("Longest Common Sub Sequence ::"+ lcs(string1,string2));
    }
}
