package com.Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by VIRAL on 3/23/2015.
 */
public class Intersection {

    public static ArrayList<Integer> intersection(ArrayList<Integer> list1, ArrayList<Integer> list2){
        ArrayList<Integer> intersectionList = new ArrayList<>(Math.max(list1.size(), list2.size()));

        Collections.sort(list1);
        Collections.sort(list2);

        int list1Tracker = 0;
        int list2Tracker = 0;

        while (list1Tracker < list1.size() && list2Tracker < list2.size()){
            if(list1.get(list1Tracker).equals(list2.get(list2Tracker)) ){
                intersectionList.add(list1.get(list1Tracker));
                list1Tracker++;
                list2Tracker++;

            }else if(list1.get(list1Tracker) > list2.get(list2Tracker)){
                list2Tracker++;
            }else{
                list1Tracker++;
            }
        }
        return intersectionList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        int size = new Random().nextInt(40);
        for (int i = 0; i < size; i++){
            list1.add(new Random().nextInt(40));
        }

        size = new Random().nextInt(40);

        for (int i = 0; i < size; i++){
            list2.add(new Random().nextInt(30));
        }

        for (int i : list1){
            System.out.print(" " + i);
        }

        System.out.println();
        System.out.println();

        for (int i : list2){
            System.out.print(" "+i);
        }

        System.out.println();
        System.out.println();
        System.out.println("<<<<<<<<<<<<<<<<< INTERSECTION >>>>>>>>>>>>>>>>>");
        System.out.println();

        ArrayList<Integer> intersectionList = intersection(list1,list2);

        for (int i : intersectionList){
            System.out.print(i + " ");
        }
    }
}
