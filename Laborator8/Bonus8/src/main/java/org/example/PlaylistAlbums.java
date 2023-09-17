package org.example;

public class PlaylistAlbums {
    private int playlist_id;
    private int album_id;

    public PlaylistAlbums(){
    }
    public PlaylistAlbums(int playlist_id, int album_id){
        this.playlist_id = playlist_id;
        this.album_id = album_id;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return "PlaylistAlbum{" +
                "playlist_id=" + playlist_id +
                ", album_id='" + album_id + '\'' +
                '}';
    }
}
