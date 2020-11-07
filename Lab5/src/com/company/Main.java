package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static final String LAB_NUMBER = "5";

    public static void main(String[] args) {

        String[] filesNames = {"10-31-2020.csv", "11-01-2020.csv", "11-02-2020.csv", "11-03-2020.csv"};

        SongPlaylist myPlaylist = new SongPlaylist(filesNames);
        initializeGUI(filesNames, myPlaylist);
    }

    /* One way to think of storing one week of data in a queue */
    public static class MyQueue {

        String fileName;
        SongPlaylist playlist;

        public MyQueue(String filename, SongPlaylist playlist) {
            this.fileName = filename;
            this.playlist = playlist;
        }

        public void process() {
            // make a function that reads, extracts and returns artist array from the CSV file
            try {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);

                myReader.nextLine();
                myReader.nextLine();
                while (myReader.hasNextLine()) {
                    String currentLine = myReader.nextLine();
                    Song newSong = Song.parseSong(currentLine);
                    playlist.insert(newSong);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void initializeGUI(String[] fileNames, SongPlaylist playlist) {
        // initialize jframe values
        JFrame frame = new JFrame("Lab " + LAB_NUMBER);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocation(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<Song> list = new DefaultListModel<>();
        // process the songs from files into myPlaylist (song name, artist name, average streams)
        for (int i = 0; i < fileNames.length; i++) {
            MyQueue myQueue = new MyQueue(fileNames[i], playlist);
            myQueue.process();
        }
        Song[] songs = playlist.convertToArray();
        String[] songNames = new String[songs.length];
        // extract song names from songs
        for (int i = 0; i < songs.length; i++) {
            songNames[i] = songs[i].songTitle;
        }
        String[] artistNames = new String[songs.length];
        // extract artist names from songs
        for (int i = 0; i < songs.length; i++) {
            artistNames[i] = songs[i].artistName;
        }
        int[] streamCount = new int[songs.length];
        // extract artist names from songs
        for (int i = 0; i < songs.length; i++) {
            streamCount[i] = songs[i].streamsAverageCount;
        }

        // create the components
        JList songList = new JList<>(songNames);  // list that contains all the songs
        songList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());

                    JDialog d = new JDialog();
                    d.setSize(250,100);
                    d.setResizable(false);
                    d.setLocationRelativeTo(null);

                    JLabel songL = new JLabel("Song: " + songNames[index]);
                    songL.setBounds(0,0,150, 20);
                    d.getContentPane().add(songL);

                    JLabel artistL = new JLabel("Artist: " + artistNames[index]);
                    artistL.setBounds(0,60,150, 20);

                    d.getContentPane().add(artistL);

                    JLabel avgStreamCountL = new JLabel("Average Stream Count: " + streamCount[index]);
                    avgStreamCountL.setBounds(0,30,150, 120);
                    d.getContentPane().add(avgStreamCountL);

                    d.setVisible(true);
                }
            }
        });

        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPane = new JScrollPane(songList);

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText("found " + songs.length + " unique songs in " + fileNames.length + " files\n(double click to see song info)");
//        JButton previousSongButton = new JButton("Previous Song");
//        JButton nextSongButton = new JButton("Next Song");

        // group the components to the panel
        panel.add(label);
//        panel.add(previousSongButton);
//        panel.add(nextSongButton);

        // add the components to the frame
        frame.getContentPane().add(BorderLayout.CENTER, listScrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);


        frame.setVisible(true);
    }
}
