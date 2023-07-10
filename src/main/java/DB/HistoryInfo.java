package DB;

import DTO.HistoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryInfo {
    private static String dbName = "/Users/junho/Desktop/제로베이스 백엔드스쿨 13기/mission1/mission1/db2.sqlite";

    public boolean insert(HistoryDTO historyDTO) {
        Connection connection = null;
        int result = 0;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            String sql = "INSERT INTO HISTORY " + "(latitude, longitude, lookupDate) " + "VALUES (?, ?, datetime('now'));";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, historyDTO.getLatitude());
            ps.setDouble(2, historyDTO.getLongitude());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
        }
        return result > 0;
    }

    public static List<HistoryDTO> getAll() {
        Connection connection = null;
        List<HistoryDTO> result = new ArrayList<>();
        ResultSet rs = null;

        try {
                Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            String sql = "SELECT * FROM HISTORY;";
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HistoryDTO historyDTO = new HistoryDTO();
                historyDTO.setId(rs.getInt("id"));
                historyDTO.setLatitude(rs.getDouble("latitude"));
                historyDTO.setLongitude(rs.getDouble("longitude"));
                historyDTO.setLookupDate(rs.getDate("lookupDate"));

                result.add(historyDTO);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return result;
    }

    public boolean delete(HistoryDTO historyDTO) {
        Connection connection = null;
        int result = 0;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            String sql = "DELETE FROM HISTORY WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, historyDTO.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return result > 0;
    }

}
