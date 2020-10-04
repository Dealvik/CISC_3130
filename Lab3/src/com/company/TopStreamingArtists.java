package com.company;

import java.io.PrintWriter;

public class TopStreamingArtists {
    private SortedArtist head;

    TopStreamingArtists() {
        insertLast(".", 0);
    }
    void insertLast(String name, int songs) {
        SortedArtist newNode = new SortedArtist(name, songs);
        newNode.next = null;

        if (head == null)
            head = newNode;
        else {
            SortedArtist n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    void insertSorted(String name, int songs) {
        SortedArtist newNode = new SortedArtist(name, songs);
        newNode.next = null;

        if (head == null)
            head = newNode;
        else {
            SortedArtist n = head;
            while (n.next != null) {
                if (n.next.name.compareToIgnoreCase(newNode.name) > 0) {
                    newNode.next = n.next;
                    n.next = newNode;
                    return;
                }
                else {
                    n = n.next;
                }
            }
            n.next = newNode;
        }
    }

    // TODO make delete work and ge
    void delete(SortedArtist artist) {
        SortedArtist n = head;
        while (n.next != null) {
            if (n.name == artist.name) {
                n.name = null;
                n.songs = -1;

                n.next = n.next.next;
            }
            n = n.next;
        }
    }

    void displayValues(PrintWriter writer) {
        boolean displayedFalse = false;
        SortedArtist current = head;
        while (current.next != null) {
            if (displayedFalse == true) {
                System.out.println(current.name + " : " + current.songs);
                writer.println(current.name + " songs: " + current.songs);
            }
            current = current.next;
            displayedFalse = true;
        }
        System.out.println(current.name + " : " + current.songs);
        delete(head);
    }

    void setName(String name) {
        name = name;
    }
    boolean isEmpty() { return head == null; }



    class SortedArtist {
        private int songs;
        private String name;
        SortedArtist next;

        SortedArtist(String name, int songs) {
            this.songs = songs;
            this.name = name;
        }

        public void incrementSongs() {
            this.songs++;
        }

    }
}
