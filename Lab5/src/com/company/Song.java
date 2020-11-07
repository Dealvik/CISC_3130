package com.company;

// node for the tree
public class Song {
    String songTitle; // use songTitle for sorting in the bst
    String artistName;
    int streamsAverageCount;
    int artistAverage;

    int timesAppeared = 0;

    Song leftChild;
    Song rightChild;

    public Song(String songTitle, String artistName, int streamsAverageCount, int artistAverage) {
        timesAppeared = 1;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.streamsAverageCount = streamsAverageCount;
        this.artistAverage = artistAverage;
    }

    public void displayValues() {
        System.out.println("Song: " + songTitle + ", Artist: " +
                artistName + ", Streams Average Count: " +
                streamsAverageCount + ", Artist Average: " +  artistAverage);
    }


    public int getStreamsAverageCount() {
        return streamsAverageCount / timesAppeared;
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
        Song song = new Song(
            trackName.toString(),
            artistName.toString(),
            Integer.parseInt(streamNumber.toString()),
            Integer.parseInt(streamNumber.toString())
        );
        return song;
    }
}
