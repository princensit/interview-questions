package com.prince.design;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Prince Raj
 */
public class MusicalJukeBox {

    private static MusicalJukeBox instance;

    private final Volume volume = new Volume();

    private boolean repeatOneSong;

    private boolean repeatAllSongs;

    private Playlist playlist;

    public static MusicalJukeBox getInstance() {
        if (instance == null) {
            synchronized (MusicalJukeBox.class) {
                if (instance == null) {
                    instance = new MusicalJukeBox();
                }
            }
        }

        return instance;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void playSong(Song song) {
        // TODO Implement me!
    }

    public void pauseSong(Song song) {
        // TODO Implement me!
    }

    public void stopSong(Song song) {
        // TODO Implement me!
    }

    public void repeatSong(Song song) {
        // TODO Implement me!
    }

    public void shuffle() {
        // TODO Implement me!
    }
}

class Playlist {

    private Song song;

    private Queue<Song> queue = new LinkedBlockingQueue<>();

    public Playlist(Song song, Queue<Song> songs) {
        this.song = song;
        this.queue = songs;
    }

    public void addSongToQueue(Song song) {
        queue.add(song);
    }

    public Song getNextSongToPlay() {
        return queue.peek();
    }
}

class Song {

    private String songName;

    private String album;

    private String singer;

    private Date createdOn;

    private boolean running = false;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

class Display {

    private Song song;

    public Display(Song song) {
        this.song = song;
    }

    public String getCurrentSongPlaying() {
        return song.getSongName();
    }
}

class Volume {

    private static final int MIN_VOLUME = 0;

    private static final int MAX_VOLUME = 100;

    // this will be the current volume or default value
    private int currentVolume = 40;

    public void increaseVolume(int delta) {
        if (currentVolume + delta > MAX_VOLUME) {
            currentVolume = MAX_VOLUME;
        } else {
            currentVolume += delta;
        }
    }

    public void decreaseVolume(int delta) {
        if (currentVolume - delta < MIN_VOLUME) {
            currentVolume = MIN_VOLUME;
        } else {
            currentVolume -= delta;
        }
    }
}

enum SOURCE {
    CD, CASSETTE, USB, FM
}