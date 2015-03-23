package com.Algorithms;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by VIRAL on 3/22/2015.
 *
 * Posting List is a list of all the occurrence of word in a specific document. It is a HashMap with key being
 * the word itself and value is a LinkedList<Integer> which stores the position at which the word occurred
 * and forms a list if more than one occurrence of specific word exists.
 *
 * This class PostingList creates such list with various other methods.
 *
 * Posting Lists are mainly used in Boolean Ranked Model in Information Retrieval to find the intersection between
 * two boolean queries.
 */
public class Posting {

    private File inputFile;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    private HashMap<String, LinkedList<Integer>> postingHahMap;


    /**
     * Constructor with only file address
     */
    public Posting(String fileAddress) throws FileNotFoundException {
        inputFile = new File(fileAddress);
        fileReader = new FileReader(inputFile);
        bufferedReader = new BufferedReader(fileReader);
        postingHahMap = new HashMap<>();
    }

    /**
     * Constructor with only file address and approximate word count.
     *
     * Word count is used as size for HashMap.
     *
     * It is generally preferable to provide a HashMap a size
     * so the time taken to expand the map (indirectly re-hashing
     * all the existing keys) it minimal. In best case this time
     * can be reduced to zero.
     */
    public Posting(String fileAddress, int wordCount) throws FileNotFoundException {
        inputFile = new File(fileAddress);
        fileReader = new FileReader(inputFile);
        bufferedReader = new BufferedReader(fileReader);
        postingHahMap = new HashMap<>(wordCount);
    }

    public HashMap<String, LinkedList<Integer>> createPostingListMap() throws IOException {
        String wordRegex = "\\w+";
        Pattern wordPattern = Pattern.compile(wordRegex);

        String currentLine = bufferedReader.readLine();
        Matcher matcher;

        int wordCounter = 0;

        while(currentLine != null){
            matcher = wordPattern.matcher(currentLine.toLowerCase());
            while(matcher.find()){
                String key = matcher.group();
                LinkedList<Integer> value = new LinkedList<>();

                if(postingHahMap.containsKey(key)){
                    value = postingHahMap.get(key);
                    value.add(wordCounter);
                }else{
                    value.add(wordCounter);
                }
                postingHahMap.put(key, value);
                wordCounter++;
            }
            currentLine = bufferedReader.readLine();
        }
        return postingHahMap;
    }

    public LinkedList<Integer> getPostingList(String word){
        LinkedList<Integer> positionList = postingHahMap.get(word);
        return positionList;
    }



    public static void main(String[] args) throws IOException {

        Posting posting = new Posting("C:\\Users\\VIRAL\\Downloads\\big.txt",40000);
        HashMap<String,LinkedList<Integer>>  postingHashMap = posting.createPostingListMap();
        System.out.println(postingHashMap.size());

        LinkedList<Integer> the_postingList = posting.getPostingList("the");
    }
}
