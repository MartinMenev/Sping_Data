package com.jdbcdemo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
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

        String villainId = scanner.nextLine();

        PreparedStatement getVillainName = connection.prepareStatement(
                "SELECT \n" +
                "    `name`\n" +
                "FROM\n" +
                "    `villains`\n" +
                "WHERE\n" +
                "    `id` = ?;");

        getVillainName.setInt(1, Integer.parseInt(villainId));
        ResultSet resultSet = getVillainName.executeQuery();
        if (resultSet.next()) {
            System.out.printf("Villain: %s%n", resultSet.getString("name"));

            PreparedStatement getVillains = connection.prepareStatement(
                    "SELECT \n" +
                    "    m.`name`, m.`age`\n" +
                    "FROM\n" +
                    "    `villains` AS v\n" +
                    "        JOIN\n" +
                    "    `minions_villains` AS mv ON v.`id` = mv.`villain_id`\n" +
                    "        JOIN\n" +
                    "    `minions` AS m ON mv.`minion_id` = m.`id`\n" +
                    "WHERE v.`id` = ?;");

            getVillains.setInt(1, Integer.parseInt(villainId));
            ResultSet result = getVillains.executeQuery();

            int count = 1;
            while(result.next()) {
                count++;
                System.out.println(count + ". " + result.getString("m.name") + " " +
                        result.getString("m.age"));
            }
        } else {
            System.out.printf("No villain with ID %s exists in the database.", villainId);
        }
    }
}
