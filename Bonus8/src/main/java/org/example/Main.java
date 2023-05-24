package org.example;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = null;
        try {
            connection = Database.getConnection();
            PlaylistDAO playlistDAO = new PlaylistDAO(connection);
            Playlist playlist1 = new Playlist(1, "Summer Hits", new Date(73, 7, 19));
            // 19.08.1973 (lunile sunt indexate de la 0)
            Playlist playlist2 = new Playlist(2, "Road Trip", new Date(117, 5, 23));
            Playlist playlist3 = new Playlist(3, "Workout Mix", new Date(122, 8, 1));
            Playlist playlist4 = new Playlist(4, "Chill Vibes", new Date(119, 3, 12));

            playlistDAO.insert(playlist1);
            playlistDAO.insert(playlist2);
            playlistDAO.insert(playlist3);
            playlistDAO.insert(playlist4);

            List<Playlist> playlists = playlistDAO.select();

            System.out.println("All playlists :");
            for (Playlist playlist : playlists) {
                System.out.println("\t" + playlist);
            }

            PlaylistAlbumsDAO playlistAlbumsDAO = new PlaylistAlbumsDAO(connection);

            PlaylistAlbums playlistAlbum1 = new PlaylistAlbums(1, 4);
            PlaylistAlbums playlistAlbum2 = new PlaylistAlbums(1, 5);
            PlaylistAlbums playlistAlbum3 = new PlaylistAlbums(1, 6);
            PlaylistAlbums playlistAlbum4 = new PlaylistAlbums(2, 7);
            PlaylistAlbums playlistAlbum5 = new PlaylistAlbums(2, 8);
            PlaylistAlbums playlistAlbum6 = new PlaylistAlbums(3, 9);
            PlaylistAlbums playlistAlbum7 = new PlaylistAlbums(3, 10);
            PlaylistAlbums playlistAlbum8 = new PlaylistAlbums(4, 11);
            PlaylistAlbums playlistAlbum9 = new PlaylistAlbums(4, 12);

            playlistAlbumsDAO.insert(playlistAlbum1);
            playlistAlbumsDAO.insert(playlistAlbum2);
            playlistAlbumsDAO.insert(playlistAlbum3);
            playlistAlbumsDAO.insert(playlistAlbum4);
            playlistAlbumsDAO.insert(playlistAlbum5);
            playlistAlbumsDAO.insert(playlistAlbum6);
            playlistAlbumsDAO.insert(playlistAlbum7);
            playlistAlbumsDAO.insert(playlistAlbum8);
            playlistAlbumsDAO.insert(playlistAlbum9);


            List<PlaylistAlbums> playlistalbums = playlistAlbumsDAO.select();

            System.out.println("\nAll playlists albums are :");
            for (PlaylistAlbums playlistAlbum : playlistalbums) {
                System.out.println("\t" + playlistAlbum);
            }

            System.out.println("\n");

            GenerateMaxPlaylist generateMaxPlaylist = new GenerateMaxPlaylist(connection, 100);

            List<List<Album>> playlistGenerated = generateMaxPlaylist.generatePlaylist();

            System.out.println("\nListele generate: ");
            int counter_lista = 1;
            for(List<Album> albumList : playlistGenerated) {
                System.out.println("\tLista " + counter_lista + ":");
                counter_lista ++;
                for(Album album : albumList) {
                    System.out.println("\t\t- " + album.getTitle() + ", id: " + album.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
