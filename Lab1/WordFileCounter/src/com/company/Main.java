/* Class: CISC 3130
 * Section: MY9 MW905
 * EmplId: 23755735
 * Name:  David Kofman
 */


package com.company;

import javax.print.attribute.standard.JobKOctets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        final String fileName = "sampleWords.txt";

        Scanner input = new Scanner(new File(fileName));
        Map<String, Integer> words = new TreeMap<>(); //<key> word <value> number of times occurred

        while (input.hasNext()) {
            String currentWord = input.next();
            currentWord = currentWord.replaceAll("\\W", "");
            currentWord = currentWord.replaceAll("\\d", "");
            currentWord = currentWord.toLowerCase();

            if(words.containsKey(currentWord)) {
                // word does exist
                words.put(currentWord, Integer.parseInt(words.get(currentWord).toString()) + 1);
            } else {
                // word doesn't exist
                words.put(currentWord, 1);
            }
        }
        input.close();

        for (Object key : words.keySet()) {
            Object currentVal = words.get(key);
            System.out.println(key + " : " + currentVal);

        }
    }
}
