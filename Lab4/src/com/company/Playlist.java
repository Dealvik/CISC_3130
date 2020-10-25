package com.company;

import java.util.LinkedList;

public class Playlist {

    // this is the node for the linked list "Playlist"
    public static class Song {
        public String track;
        public String artist;
        public String url;

        Song next;

        public Song(String track, String artist, String url) {
            this.track = track;
            this.artist = artist;
            this.url = url;
        }
        public String displayValues() {
            return track + " " + artist + " " + url;
        }
        public static Song parseSong(String string) {
            StringBuilder chartNumber = new StringBuilder();
            StringBuilder trackName = new StringBuilder();
            StringBuilder artistName = new StringBuilder();
            StringBuilder streamNumber = new StringBuilder();
            StringBuilder urlNumber = new StringBuilder();
            boolean parsingChartNumber = true;
            boolean parsingTrackName = true;
            boolean parsingArtistName = true;
            boolean parsingStreamNumber = true;
            boolean parsingUrlNumber = true;
            boolean firstQuoteInTrackName = false;
            boolean lastQuoteInTrackName = false;
            boolean firstQuoteInArtistName = false;
            boolean lastQuoteInArtistName = false;

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                // parsing the number first
                if (parsingChartNumber) {
                    if (c == ',') {
                        parsingChartNumber = false;
                        continue;
                    } else {
                        chartNumber.append(c);
                        continue;
                    }
                }

                // parsing the track name
                if (parsingTrackName) {
                    if (c == '"') {
                        if (firstQuoteInTrackName == false) {
                            firstQuoteInTrackName = true;
                            continue;
                        }
                        if (lastQuoteInTrackName == false) {
                            lastQuoteInTrackName = true;
                            continue;
                        }
                    }
                    boolean haveBothQuotes = !firstQuoteInTrackName && !lastQuoteInTrackName;
                    boolean haveNoQuotes = firstQuoteInTrackName && lastQuoteInTrackName;
                    if (c == ',' && (haveNoQuotes || haveBothQuotes)) {
                        parsingTrackName = false;
                        continue;
                    } else {
                        trackName.append(c);
                        continue;
                    }
                }

                // parsing the artist name
                if (parsingArtistName) {
                    if (c == '"') {
                        if (firstQuoteInArtistName == false) {
                            firstQuoteInArtistName = true;
                            continue;
                        }
                        if (lastQuoteInArtistName == false) {
                            lastQuoteInArtistName = true;
                            continue;
                        }
                    }
                    if (c == ',') {
                        parsingArtistName = false;
                        continue;
                    } else {
                        artistName.append(c);
                        continue;
                    }
                }

                // parsing the streams number
                if (parsingStreamNumber) {
                    if (c == ',') {
                        parsingStreamNumber = false;
                        continue;
                    } else {
                        streamNumber.append(c);
                        continue;
                    }
                }

                // parsing the url number
                if (parsingUrlNumber) {
                    if (c == ',') {
                        parsingUrlNumber = false;
                        continue;
                    } else {
                        urlNumber.append(c);
                        continue;
                    }
                }
            }
            Song song = new Song(trackName.toString(), artistName.toString(), urlNumber.toString());
            return song;
        }
    }

    private Song head;     // the first Song of the playlist

    public boolean isEmpty() {
        return head == null;
    }

    public void addSong(Song newSong) {
        newSong.next = null;

        if (head == null) {
            head = newSong;
        } else {
            // head is not null so cycle through the linked list for a song.next with room
            Song n = head;

            while (n.next != null) {
                // SORTING WHEN ADDING - comparing in ascending order based of the track name
                int compared = n.next.track.compareToIgnoreCase(newSong.track);
                if (compared == 0) {
                    return;
                }
                if (compared > 0) {
                    newSong.next = n.next;
                    n.next = newSong;
                    return;
                }
                else {
                    n = n.next;
                }
            }
            n.next = newSong;
        }
    }

    public Song removeHead() { // remove at the beginning
        if (head == null) {
            return null;
        }
        Song deletedHead = head;
        head = head.next;
        return deletedHead;
    }
}
