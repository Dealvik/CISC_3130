package com.company;
import com.company.Playlist.Song;

public class SongHistoryList {
    private Song top;

    public void addSong(Song song) {
        song.next = top;
        top = song;
    }

    public Song pop() // remove at the beginning
    {
        if (top == null) {
            return null;
        }
        Song deletedHead = top;
        top = top.next;
        return deletedHead;
    }
    public void display()
    {
        // check for stack underflow
        if (top == null) {
            System.out.printf("\nStack Underflow");
        }
        else {
            Song temp = top;
            while (temp != null) {

                temp.displayValues();
                // assign temp link to temp
                temp = temp.next;
            }
        }
    }
    public Song lastListened() { // this is like the peek
        return top;
    }
}
