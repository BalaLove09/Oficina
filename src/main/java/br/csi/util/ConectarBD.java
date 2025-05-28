package br.csi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBD {

    public static Connection ConectarBancoPostgres()
            throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");

        String url = "jdbc:postgresql://localhost:5432/oficina";
        String user = "postgres";
        String senha = "1234";

        Connection conn = DriverManager.getConnection(url, user, senha);
        return conn;

    }

}
