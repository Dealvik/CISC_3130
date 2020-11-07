package com.company;

import java.util.*;

// binary search tree implementation
public class SongPlaylist {
    public Song root;
    int filesAmount = 0;

    SongPlaylist(String[] files) {
        filesAmount = files.length;
    }

    public Song find(String key) {
        Song current = root;

        while (key != current.songTitle) {
            if(key.hashCode() < current.songTitle.hashCode())
                current = current.leftChild;
            else
                current = current.rightChild;

            if(current == null)
                return null;
        }
        return current;
    }

    public void insert(Song newSong) {
        if(root == null)
            root = newSong;
        else {
            Song current = root;
            Song parent;
            while (true) {
                parent = current;

                // SORTING WHEN ADDING - comparing in ascending order based of the song title
                int compared = newSong.songTitle.compareToIgnoreCase(current.songTitle);

                // the previous Song
                if (compared == 0) {
                    current.streamsAverageCount += newSong.streamsAverageCount;
                    current.timesAppeared ++;
                    if (current.timesAppeared >= filesAmount) {
                        // compute average
                        current.streamsAverageCount = current.streamsAverageCount / current.timesAppeared;
                    }

                    return;
                }

                if (compared < 0) {
                    // go left?
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newSong;
                        return;
                    }
                }
                else {
                    current = current.rightChild;
                    if(current == null) {
                        parent.rightChild = newSong;
                        return;
                    }
                }
            }
        }
    }
    public void inOrder(Song node, int pos, Song[] songs) {
        if (node != null) {
            inOrder(node.leftChild, pos + 1, songs);
            node.displayValues();
            if (pos < songs.length) {
                songs[pos] = node;
            }
            inOrder(node.rightChild, pos + 1, songs);
        }
    }

//    public Song[] getAllSongsOrdered(Song node) {
//        ArrayList<Song> songLinkedList = new ArrayList<>();
//
//        if (node != null) {
//            inOrder(node.leftChild);
//            System.out.println(node.displayValues());
//
//            inOrder(node.rightChild);
//        }
//    }

//    public void getAllSongsOrdered(List<Song> list, Song node) {
//        if (node != null) {
//            getAllSongsOrdered(list, node.leftChild);
//            list.add(node);
//            getAllSongsOrdered(list, node.rightChild);
//        }
//    }

    private void getAllSongsOrdered(Song node, List<Song> arrayListSongs) {
        if (node != null) {
            getAllSongsOrdered(node.leftChild, arrayListSongs);
            arrayListSongs.add(node);
            getAllSongsOrdered(node.rightChild, arrayListSongs);
        }
    }

    public Song[] convertToArray() {
        ArrayList<Song> songArrayList = new ArrayList<>();
        this.getAllSongsOrdered(this.root, songArrayList);
        Song[] songs = new Song[songArrayList.size()];
        // songArrayList is now sorted correctly (:D)
        for (int i = 0; i < songs.length; i++) {
            songs[i] = songArrayList.get(i);
        }
        return songs;
    }
}
