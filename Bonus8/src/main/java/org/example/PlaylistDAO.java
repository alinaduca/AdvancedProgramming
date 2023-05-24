package org.example;

import java.sql.*;
import java.util.*;

public class PlaylistDAO {
    private Connection connection;
    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }
    public List<Playlist> select() throws SQLException {
        String query = "SELECT * FROM playlists";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            Playlist playlist = new Playlist();
            playlist.setId(resultSet.getInt("id"));
            playlist.setTitle(resultSet.getString("title"));
            playlist.setCreationDate(resultSet.getDate("creationDate"));
            playlists.add(playlist);
        }

        statement.close();
        resultSet.close();
        return playlists;
    }


    public void insert(Playlist playlist) throws SQLException {
        String query = "INSERT INTO playlists (id, title, creationDate) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlist.getId());
        statement.setString(2, playlist.getTitle());
        statement.setDate(3, playlist.getCreationDate());
        statement.executeUpdate();

        statement.close();
    }

    public void update(Playlist playlist) throws SQLException {
        String query = "UPDATE playlists SET title = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, playlist.getTitle());
        statement.setInt(2, playlist.getId());
        statement.executeUpdate();

        statement.close();
    }

    public void delete(Playlist playlist) throws SQLException {
        String query = "DELETE FROM playlists WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlist.getId());
        statement.executeUpdate();

        statement.close();
    }


    public int getPlaylistID(String playlistTitle) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM playlists WHERE title = ?");
        statement.setString(1, playlistTitle);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            statement.close();
            resultSet.close();
            return resultSet.getInt("id");
        } else {
            statement.close();
            resultSet.close();
            return -1; // artist not found
        }
    }

    public int getMaxPlaylistID() throws SQLException {
        String query = "SELECT MAX(id) AS max_id FROM playlists";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            statement.close();
            resultSet.close();
            return resultSet.getInt("max_id");
        }

        statement.close();
        resultSet.close();
        return 0;
    }
}