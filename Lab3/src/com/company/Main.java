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

        Artist(String name) {
            songs = 1;
            this.name = name;
        }

        public void incrementSongs() {
            this.songs++;
        }
    }
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        TopStreamingArtists topStreamingArtists = new TopStreamingArtists();

        final String fileName = "viral-us-daily-latest.csv";
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        Scanner input = new Scanner(new File(fileName));

        Artist[] artists = new Artist[100];

        // Skip the top line
        input.nextLine();
        for(int x=0; x < 50; x++) {
            // this loop will set the artists without checking for duplicates
            String currentLine = input.nextLine();
            String[] currentLineArray = currentLine.split(",(?=\\S)");
            String currentArtist = currentLineArray[2];
            int foundAt = find(artists, currentArtist);

            // duplication check
            if (foundAt != -1) {
                artists[foundAt].incrementSongs();
//                topStreamingArtists.insertSorted(artists[foundAt].name, 1);
                continue;
            }

            artists[x] = new Artist(currentArtist);

            System.out.println();
        }
        input.close();

        for (int z = 0; z < artists.length; z++) {
            if (artists[z] == null) {
                continue;
            }

            String name = artists[z].name;
            name = name.replace("\"", "");

            int songs = artists[z].songs;
            topStreamingArtists.insertSorted(name, songs);
//            writer.println(name + " songs: " + songs);
        }

        topStreamingArtists.displayValues(writer);
        writer.close();
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
