package org.example;

import java.sql.*;
import java.util.*;

public class GenreDAO {
    private Connection connection;

    public GenreDAO(Connection connection) {
        this.connection = connection;
    }

    public void addGenre(Genre genre) throws SQLException {
        Statement stmt = null;
        String sql = "INSERT INTO genres (id, name) VALUES (" + genre.getId() +", '" + genre.getName() +"')";
        try {
            stmt = connection.createStatement();
            int rows = stmt.executeUpdate(sql);
            System.out.println(rows + " rows inserted into table genres.");
        } catch (SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        } finally {
            if (stmt != null) stmt.close();
        }
    }

    public List<Genre> getAllGenres() throws SQLException {
        try {
            List<Genre> genres = new ArrayList<>();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM genres";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int genreId = resultSet.getInt("id");
                String genreName = resultSet.getString("name");
                Genre genre = new Genre(genreId, genreName);
                genres.add(genre);
            }
            resultSet.close();
            statement.close();
            return genres;
        } catch(SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
        }
        return null;
    }

    public Genre getGenreById(int id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM genres WHERE id=" + id;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int genreId = resultSet.getInt("id");
                String genreName = resultSet.getString("name");
                Genre genre = new Genre(genreId, genreName);
                return genre;
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

    public Genre findByName(String genreName) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String command = "select * from genres where name='" + genreName + "'";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                int genreId = resultSet.getInt("id");
                Genre genre = new Genre(genreId, genreName);
                return genre;
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
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String command = "select max(id)+1 from genres";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                int genreId = resultSet.getInt("max(id)+1");
                return genreId;
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