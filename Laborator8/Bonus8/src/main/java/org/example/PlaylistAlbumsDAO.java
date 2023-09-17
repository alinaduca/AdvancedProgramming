package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistAlbumsDAO {
    private Connection connection;

    public PlaylistAlbumsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PlaylistAlbums> select() throws SQLException {
        String query = "SELECT * FROM playlist_albums";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<PlaylistAlbums> playlistAlbums = new ArrayList<>();
        while (resultSet.next()) {
            PlaylistAlbums playlistAlbum = new PlaylistAlbums();
            playlistAlbum.setPlaylist_id(resultSet.getInt("playlist_id"));
            playlistAlbum.setAlbum_id(resultSet.getInt("album_id"));
            playlistAlbums.add(playlistAlbum);
        }

        statement.close();
        resultSet.close();

        return playlistAlbums;
    }

    public void insert(PlaylistAlbums playlistAlbum) throws SQLException {
        String query = "INSERT INTO playlist_albums (playlist_id, album_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, playlistAlbum.getPlaylist_id());
        statement.setInt(2, playlistAlbum.getAlbum_id());
        statement.executeUpdate();

        statement.close();
    }

    public void delete(PlaylistAlbums playlistAlbum) throws SQLException {
        String query = "DELETE FROM albums WHERE playlist_id = ? and album_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlistAlbum.getPlaylist_id());
        statement.setInt(2, playlistAlbum.getAlbum_id());
        statement.executeUpdate();

        statement.close();
    }
}
