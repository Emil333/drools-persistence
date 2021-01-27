package com.tech.drools.droolspersistence;

import org.apache.ignite.IgniteJdbcThinDataSource;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String igniteUrl = "jdbc:ignite:thin://localhost:10800/";
        IgniteJdbcThinDataSource ids = new IgniteJdbcThinDataSource();
        ids.setUrl(igniteUrl);
        ids.setDistributedJoins(true);
        Connection conn2 = ids.getConnection();
        ResultSet rs = conn2.createStatement().executeQuery("select * from city");
        while (rs.next()) {
            String name = rs.getString(1);
            System.out.println(name);
        }
        conn2.close();



    }
}
