package com.Algorithms;

/**
 * Reference taken from http://raelcunha.com/spell-correct.php
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by VIRAL on 3/22/2015.
 */
public class SpellChecker {
    private final HashMap<String, Integer> dictionaryWords = new HashMap<>();

    /**
     * Constructor takes input as a location of dictionary preferably .txt file
     * and creates a HashMap which contains every unique word as key and its occurrence
     * as value.
     *
     * @param fileLocation  A String stating file location.
     */
    public SpellChecker(String fileLocation) throws IOException{
        FileReader fileReader = new FileReader(fileLocation);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        /**
         * Create a Pattern to identify words
         */
        String wordRegex = "\\w+";
        Pattern wordPattern = Pattern.compile(wordRegex);

        String currentLine = bufferedReader.readLine();
        Matcher matcher;
        while(currentLine != null){
            matcher = wordPattern.matcher(currentLine.toLowerCase());

            /**
             * Find all the matching sub-sequences i.e words in line currentLine
             */
            while(matcher.find()){

                /**
                 * Capture the input sub-sequence matched by matcher
                 */
                String key = matcher.group();
                int value;

                /**
                 * Check if the HashMap contains key, if yes increment its value i.e. occurrence
                 * if not initialize value to 1
                 */
                if(dictionaryWords.containsKey(key)){
                    value = dictionaryWords.get(key)+1;
                }else{
                    value = 1;
                }
                dictionaryWords.put(key, value);
            }
            currentLine = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {

    }
}
