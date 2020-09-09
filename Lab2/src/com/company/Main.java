package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;


class Artist {
    public int songs;
    public String name;

    public Artist() {
        songs = 1;
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementSongs() {
        this.songs++;
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
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

            if (foundAt != -1) {
                artists[foundAt].incrementSongs();
                continue;
            }

            artists[x] = new Artist();
            artists[x].setName(currentArtist);

            System.out.println();
        }
        input.close();

        for (int z = 0; z < artists.length; z++) {
            if (artists[z] == null) {
                continue;
            }
            writer.println(artists[z].name + " songs: " + artists[z].songs);
        }
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
