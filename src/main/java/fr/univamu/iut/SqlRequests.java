package fr.univamu.iut;

import java.sql.*;

public class SqlRequests {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver"; // Driver JDBC pour MySQL
    static final String DB_URL = "jdbc:mariadb://mysql-structuration-dune-application-web-en-php.alwaysdata.net/structuration-dune-application-web-en-php_restaurant_bd";
    static final String USER = "344788_annonces";
    static final String PASS = "Fatigueµ56";

    public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;

        // Etape 1: Enregistrement du driver JDBC
        Class.forName(JDBC_DRIVER);

        // Etape 2: Connexion à la base de données
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Etape 3: Création de la requête SQL
        stmt = conn.createStatement();
        String sql;
        sql = query;
        ResultSet rs = stmt.executeQuery(sql);
        conn.close();
        stmt.close();
        return rs;
    }
}
