package com.jdbcdemo;

import java.sql.*;


public class GetVillainsNames {
    private static final String GET_VILLAIN_NAMES = "SELECT \n" +
                    "    v.`name`, COUNT(DISTINCT m.`name`) AS number\n" +
                    "FROM\n" +
                    "    `villains` AS v\n" +
                    "        JOIN\n" +
                    "    `minions_villains` AS mv ON v.`id` = mv.`villain_id`\n" +
                    "        JOIN\n" +
                    "    `minions` AS m ON mv.`minion_id` = m.`id`\n" +
                    "GROUP BY v.`name`\n" +
                    "HAVING number > 15\n" +
                    "ORDER BY number DESC;";
    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAIN_NAMES);

        final ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("v.name") + " " +
                    result.getString("number"));
        }

        connection.close();
    }
}
