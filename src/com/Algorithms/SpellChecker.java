package com.Algorithms;

/**
 * Reference taken from http://raelcunha.com/spell-correct.php
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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


    /**
     * Enumerates all possible suggestion based on edit distance
     * This can be a big set. For a word of length n, there will be n deletions,
     * n-1 transpositions, 26n alterations, and 26(n+1) insertions, for a total of 54n+25
     */
    public final ArrayList<String> suggestions(String word){
        ArrayList<String> suggestionList = new ArrayList<>();
        String addedString = "";


        /**
         * Suggestion based on deletion of any one character
         * Input    = Viral
         * Output   = iral
         *          = Vral
         *          = Vial
         *          = Vira
         */
        for(int i = 0; i < word.length(); ++i){
            addedString = word.substring(0, i) + word.substring(i+1);
            suggestionList.add(addedString);
        }


        /**
         * Suggestion based on transposition
         * Input    = Viral
         * Output   = ()	(i)	(V)	(ral)
         *           (V)	(r)	(i)	(al)
         *           (Vi)	(a)	(r)	(l)
         *           (Vir)	(l)	(a)	()
         */
        for (int i = 0; i < word.length() - 1; ++i){
            addedString = word.substring(0,i) + word.substring(i+1,i+2) + word.substring(i, i+1) + word.substring(i+2);
//            System.out.println("("+ word.substring(0,i)+")\t("+word.substring(i+1,i+2)+")\t("+word.substring(i, i+1)+")\t("+word.substring(i+2)+")");
            suggestionList.add(addedString);
        }


        /**
         * Suggestion based on replacement of any one character
         * Input    = Viral
         * Output   = ()     (a->z) (iral)
         *          = (V)    (a->z) (ral)
         *          = (Vi)   (a->z) (al)
         *          = (Vir)  (a->z) (l)
         *          = (Vira) (a->z) ()
         */
        for(int i = 0; i < word.length(); ++i){
            for (char c = 'a'; c <= 'z'; ++c){
                addedString = word.substring(0, i) + String.valueOf(c) + word.substring(i+1);
//                System.out.println("("+ word.substring(0,i)+")\t("+String.valueOf(c)+")\t("+word.substring(i+1)+")");
                suggestionList.add(addedString);
            }
        }


        /**
         * Suggestion based on insertion of one character at any position
         * Input    = Viral
         * Output   = ()      (a->z)  (Viral)
         *          = (V)     (a->z)  (iral)
         *          = (Vi)    (a->z)  (ral)
         *          = (Vir)   (a->z)  (al)
         *          = (Vira)  (a->z)  (l)
         *          = (Viral) (a-> z) ()
         */
        for(int i = 0; i <= word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                addedString = word.substring(0,i)+String.valueOf(c)+word.substring(i);
//                System.out.println("("+ word.substring(0,i)+")\t("+String.valueOf(c)+")\t("+word.substring(i)+")");
                suggestionList.add(addedString);
            }
        }
        return suggestionList;
    }


    /**
     * The function correction chooses as the set of possible words the set with
     * the shortest edit distance to the original word, as long as the set has
     * some known words. Once it identifies the possible set to consider, it
     * chooses the element with the highest P(c) i.e probability of corrected
     * word "c" value.
     */
    public final String correction(String word){
        String returnString = "";

        /**
         * If the word is present in dictionary return immediately
         */
        if(dictionaryWords.containsKey(word)){
            returnString = word;
        }else{
            ArrayList<String> suggestionList = suggestions(word);
            HashMap<Integer, String> possibleWords = new HashMap<>();

            /**
             * For each suggestion check if it exists in dictionary.
             * If yes then store the string with its occurrence as key
             */
            for (String string : suggestionList){
                if (dictionaryWords.containsKey(string)){
                    possibleWords.put(dictionaryWords.get(string), string);
                }

                /**
                 * Return the maximum occurred word if there exists any from
                 * suggestionsList
                 */
                if(possibleWords.size() > 0){
                    returnString = possibleWords.get(Collections.max(possibleWords.keySet()));
                }else{

                    /**
                     * If any of the suggestion is not present in dictionary check for suggestions for each
                     * word in suggestionList.
                     */
                    for (String s : suggestionList){
                        for (String s1 : suggestions(s)){
                            if(dictionaryWords.containsKey(s1)){
                                possibleWords.put(dictionaryWords.get(s1), s1);
                            }
                        }
                    }


                    /**
                     * Return the maximum occurred word if there exists any from
                     * suggestionsList of "s" i.e. each word from previous suggestionList.
                     */
                    if(possibleWords.size() > 0){
                        returnString = possibleWords.get(Collections.max(possibleWords.keySet()));
                    }else{

                        /**
                         * Return the word itself if nothing found
                         */
                        returnString = word;
                    }
                }
            }
        }
        return returnString;
    }



    public static void main(String[] args) throws IOException {
        SpellChecker spellChecker = new SpellChecker("C:\\Users\\VIRAL\\Downloads\\big.txt");
        System.out.println(spellChecker.correction("plesent"));
    }
}
