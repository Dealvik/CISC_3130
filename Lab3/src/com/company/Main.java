package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    static class Artist {
        private int songs;
        private String name;
        TopStreamingArtists.SoftedArtist next;

        Artist(String name) {
            songs = 1;
            name = "";
        }

        public void incrementSongs() {
            this.songs++;
        }

    }
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        TopStreamingArtists topStreamingArtists = new TopStreamingArtists();
        topStreamingArtists.insertLast("a",1);
        topStreamingArtists.insertSorted("a",4);
        topStreamingArtists.insertSorted("a",5);
        topStreamingArtists.insertSorted("a",2);
        topStreamingArtists.insertSorted("a",3);
        topStreamingArtists.insertSorted("a",3);
//        topStreamingArtists.insertSorted("a",2);
//        topStreamingArtists.insertSorted("a",1);

        topStreamingArtists.displayValues();

        final String fileName = "viral-us-daily-latest.csv";
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        Scanner input = new Scanner(new File(fileName));

       // TopStreamingArtists artists = new TopStreamingArtists();
       Artist[] artists = new Artist[100];


        // Skip the top line
        input.nextLine();
        for(int x=0; x < 50; x++) {
            // this loop will set the artists without checking for duplicates
            String currentLine = input.nextLine();
            String[] currentLineArray = currentLine.split(",(?=\\S)");
            String currentArtist = currentLineArray[2];
            int foundAt = find(artists, currentArtist);

            if (foundAt != -1) {
                artists[foundAt].incrementSongs();
                continue;
            }

//            artists[x] = new Artist();
//            artists[x].setName(currentArtist);

            System.out.println();
        }
        input.close();

        // declare topartistist = TopStreamingArtists and instanciate it

        for (int z = 0; z < artists.length; z++) {
            if (artists[z] == null) {
                continue;
            }
            writer.println(artists[z].name + " songs: " + artists[z].songs);
            // topartistist.insertSorted (name, songs);
        }
        writer.close();

//        SortedArtist ta = topartistist.next();
//        while(ta != null) {
//            //print ta
//            //it will print it sorted
//            ta = ta.next();
//        }
    }

    public static int find(Artist[] artists, String currentArtistName) {
        for (int j = 0; j < artists.length; j++) {
            if (artists[j] == null) {
                continue;
            }
            if (artists[j].name.equalsIgnoreCase(currentArtistName)) {
                return j;
            }
        }
        return -1;
    }
}
