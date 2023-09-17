package org.example;

import java.sql.*;
import java.util.*;

public class ArtistDAO {
    private Connection connection;
    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }
    public void addArtist(Artist artist) throws SQLException {
        Statement stmt = null;
        String sql = "insert into artists (id, name) values (" + artist.getId() + ", '" + artist.getName() + "')";
        try {
            stmt = connection.createStatement();
            int rows = stmt.executeUpdate(sql);
            System.out.println(rows + " rows inserted into table artists.");
        } catch (SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        } finally {
            if(stmt != null) stmt.close();
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
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String command = "select * from artists where id=" + id;
            resultSet = statement.executeQuery(command);
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
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
        return null;
    }

    public Artist findByName(String artistName) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String command = "select * from artists where name='" + artistName + "'";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                int artistId = resultSet.getInt("id");
                Artist artist = new Artist(artistId, artistName);
                return artist;
            }
            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
        return null;
    }

    public int getNewId() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String command = "select max(id)+1 from artists";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                int artistId = resultSet.getInt("max(id)+1");
                return artistId;
            }
            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
        return 0;
    }
}