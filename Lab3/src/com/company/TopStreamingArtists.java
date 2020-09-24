package com.company;

public class TopStreamingArtists {
    private SoftedArtist head;

    void insertLast(String name, int songs) {
        SoftedArtist newNode = new SoftedArtist(name, songs);
        newNode.next = null;

        if (head == null)
            head = newNode;
        else {
            SoftedArtist n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    void insertSorted(String name, int songs) {
        SoftedArtist newNode = new SoftedArtist(name, songs);
        newNode.next = null;

        if (head == null)
            head = newNode;
        else {
            SoftedArtist n = head;
            while (n.next != null) {
                if (n.next.songs > newNode.songs) {
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
    void displayValues() {
        SoftedArtist current = head;
        while (current.next != null) {
            System.out.println(current.name + " : " + current.songs);
            current = current.next;
        }
        System.out.println(current.name + " : " + current.songs);
    }


    void setName(String name) {
        name = name;
    }
    boolean isEmpty() { return head == null; }
//    Object getItem(int index) {
//        for (int i = 0; i < index; i++) {
//
//        }
//    }
//    public void incrementSongs() {
//        this.songs++;
//    }

    class SoftedArtist {
        private int songs;
        private String name;
        SoftedArtist next;

        SoftedArtist(String name, int songs) {
            this.songs = songs;
            this.name = name;
        }

        public void incrementSongs() {
            this.songs++;
        }

    }
}
