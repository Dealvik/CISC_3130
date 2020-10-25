package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.company.Playlist.Song;

public class Main {

    public static void main(String[] args) {

        String[] files = {"week1.csv", "week2.csv", "week3.csv", "week4.csv", "week5.csv", "week6.csv",
                "week7.csv", "week8.csv", "week9.csv", "week10.csv", "week11.csv", "week12.csv"};

        int totalCount = 0;
        final int SONGS_TO_LISTEN_TO = 12;

        Playlist playlist = new Playlist();
        SongHistoryList songHistoryList = new SongHistoryList();

        // process the files
        for (int i = 0; i < files.length; i++) {
            MyQueue weekQueue = new MyQueue(files[i], playlist);
            totalCount += weekQueue.process();
        }

        System.out.println("----------");
        System.out.println(totalCount + " songs found in all CSV files");
        System.out.println("Going to remove/listen to " + SONGS_TO_LISTEN_TO + " different unique songs");
        System.out.println("----------");

        // remove
        for (int i = 0; i < SONGS_TO_LISTEN_TO; i++) {
            Song removedSong = playlist.removeHead();
            System.out.println("removed : " + removedSong.displayValues());
            songHistoryList.addSong(removedSong);
        }

        System.out.println("----------");
        System.out.println("These are the songs you listened to in order: ");
        System.out.println("----------");

        // song history
        for (int i = 0; i < SONGS_TO_LISTEN_TO; i++) {
            if (songHistoryList.lastListened() == null) {
                break;
            }
            Song removedSong = songHistoryList.pop();
            System.out.println(removedSong.displayValues());
        }
    }

    /* One way to think of storing one week of data in a queue */
    public static class MyQueue {

        String fileName;
        Playlist playlist;

        public MyQueue(String filename, Playlist playlist) {
            this.fileName = filename;
            this.playlist = playlist;
        }

        public int process() {
            // make a function that reads and extracts the artist, song name and url from the CSV file

            int count = 0;

            try {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);

                myReader.nextLine();
                myReader.nextLine();
                while (myReader.hasNextLine()) {
                    String currentLine = myReader.nextLine();
                    Playlist.Song newSong = Playlist.Song.parseSong(currentLine);
                    // add the values into the playlist linkedlist
                    playlist.addSong(newSong);
                    count++;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            return count;
        }
    }
}

