package com.jdbcdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        properties.setProperty("user", "root");
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);

        String countryName = scanner.nextLine();

        PreparedStatement ps = connection.prepareStatement(
                "UPDATE `towns` SET `name` = UPPER(`name`) WHERE country = ?");

        ps.setString(1, countryName);
        int changedTowns = ps.executeUpdate();

        List<String> townList = new ArrayList<>();

        PreparedStatement showUpdatedTowns = connection.prepareStatement(
                "SELECT `name` FROM `towns` WHERE country = ?");
        showUpdatedTowns.setString(1, countryName);
        ResultSet rs = showUpdatedTowns.executeQuery();

        while (rs.next()) {
            townList.add(rs.getString("name"));
        }

        if (changedTowns == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.println(changedTowns + " town names were affected.");
            System.out.println("[" + String.join(", ", townList) + "]");
        }

    }
}
