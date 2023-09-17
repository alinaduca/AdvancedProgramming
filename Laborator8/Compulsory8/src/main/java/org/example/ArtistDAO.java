package org.example;

import java.sql.*;
import java.util.*;

public class ArtistDAO {
    private Connection connection;
    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }
    public void addArtist(Artist artist) throws SQLException {
        String sql = "insert into artists (id, name) values (" + artist.getId() + ", '" + artist.getName() + "')";
        try {
            Statement stmt = connection.createStatement();
            int rows = stmt.executeUpdate(sql);
            System.out.println(rows + " rows inserted into table artists.");
        } catch (SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        }
    }
    public List<Artist> getAllArtists()  {
        try
        {
            List<Artist> artists = new ArrayList<>();
            Statement statement = connection.createStatement();
            String command = "select * from artists";
            ResultSet resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                int artistId = resultSet.getInt("id");
                String artistName = resultSet.getString("name");
                Artist artist = new Artist(artistId, artistName);
                artists.add(artist);
            }
            resultSet.close();
            statement.close();
            return artists;
        } catch(SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        }
        return null;
    }

    public Artist getArtistById(int id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String command = "select * from artists where id=" + id;
            ResultSet resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                int artistId = resultSet.getInt("id");
                String artistName = resultSet.getString("name");
                Artist artist = new Artist(artistId, artistName);
                return artist;
            }
            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        }
        return null;
    }
}