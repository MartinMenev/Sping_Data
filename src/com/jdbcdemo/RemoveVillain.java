package com.jdbcdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        properties.setProperty("user", "root");
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);

        String villainId = scanner.nextLine();

        PreparedStatement checkVillainPresence = connection.prepareStatement(
                "SELECT * FROM `villains` WHERE `id` = ?;");

        checkVillainPresence.setString(1, villainId);
        ResultSet resultSet = checkVillainPresence.executeQuery();
        if (!resultSet.next()) {
            System.out.println("No such villain was found");
        } else {
            String villainName = resultSet.getString("name");
            PreparedStatement removeVillain = connection.prepareStatement(
                    "DELETE FROM `villains` WHERE `id` = ?;");
            removeVillain.setString(1, villainId);
            int numberRemoved = removeVillain.executeUpdate();

            System.out.println(villainName + " was deleted");
            System.out.println(numberRemoved + " minions released");
        }
    }
}
