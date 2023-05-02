package org.example;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        String filename = "album.csv";
        AlbumDAO albumDAO = new AlbumDAO();
        Connection connection = null;
        try {
            connection = Database.getConnection();
            ArtistDAO artistDAO = new ArtistDAO(connection);
            GenreDAO genreDAO = new GenreDAO(connection);

            // Create the album importer and import the data
            RollingStoneAlbumImporter importer = new RollingStoneAlbumImporter(artistDAO, genreDAO, albumDAO);
            importer.importAlbums(filename);
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
