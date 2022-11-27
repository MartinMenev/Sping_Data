package com.jdbcdemo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.println();
        properties.setProperty("user", "root");
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                "    v.`name`, COUNT(DISTINCT m.`name`) AS number\n" +
                "FROM\n" +
                "    `villains` AS v\n" +
                "        JOIN\n" +
                "    `minions_villains` AS mv ON v.`id` = mv.`villain_id`\n" +
                "        JOIN\n" +
                "    `minions` AS m ON mv.`minion_id` = m.`id`\n" +
                "GROUP BY v.`name`\n" +
                "HAVING number > 15\n" +
                "ORDER BY number DESC;");

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("v.name") + " " +
                    result.getString("number"));
        }
    }
}
