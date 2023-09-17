package org.example;

import java.sql.*;
import java.util.*;

public class GenreDAO {
    private Connection connection;

    public GenreDAO(Connection connection) {
        this.connection = connection;
    }

    public void addGenre(Genre genre) {
        String sql = "INSERT INTO genres (id, name) VALUES (" + genre.getId() +", '" + genre.getName() +"')";
        try {
            Statement stmt = connection.createStatement();
            int rows = stmt.executeUpdate(sql);
            System.out.println(rows + " rows inserted into table genres.");
        } catch (SQLException e) {
            System.out.println("You have this error:");
            e.printStackTrace();
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
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM genres WHERE id=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
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
        }
        return null;
    }
}