package DB;

import DTO.WifiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiInfo {
    private static String dbName = "C:\\Users\\junho\\Desktop\\제로베이스 백엔드스쿨 13기\\mission1\\mission1\\db2.sqlite";

    public boolean insert(WifiDTO wifi) {
        Connection connection = null;
        int result = 0;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            String sql = "REPLACE INTO WIFI"
                        + "(management_number, district, name, address1, address2,"
                        + "installed_floor, install_type, install_agency,"
                        + "service_classification, mesh_type, install_year, in_out_door,"
                        + "connection_environment, latitude, longitude, workDateStr) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, wifi.getManagement_number());
            ps.setString(2, wifi.getDistrict());
            ps.setString(3, wifi.getName());
            ps.setString(4, wifi.getAddress1());
            ps.setString(5, wifi.getAddress2());
            ps.setString(6, wifi.getInstalled_floor());
            ps.setString(7, wifi.getInstall_type());
            ps.setString(8, wifi.getInstall_agency());
            ps.setString(9, wifi.getService_classification());
            ps.setString(10, wifi.getMesh_type());
            ps.setString(11, wifi.getInstall_year());
            ps.setString(12, wifi.getIn_out_door());
            ps.setString(13, wifi.getConnection_environment());
            ps.setDouble(14, wifi.getLatitude());
            ps.setDouble(15, wifi.getLongitude());
            ps.setString(16, wifi.getWorkDateStr());
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

    public boolean delete(WifiDTO wifi) {
        Connection connection = null;
        int result = 0;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            String sql = "DELETE FROM WIFI WHERE management_number = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, wifi.getManagement_number());

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

    public List<WifiDTO> readAll() {
        Connection connection = null;
        List<WifiDTO> result = new ArrayList<>();
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            String sql = "SELECT * FROM WIFI";
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                WifiDTO wifi = new WifiDTO();
                wifi.setManagement_number(rs.getString("management_number"));
                wifi.setDistrict(rs.getString("district"));
                wifi.setName(rs.getString("name"));
                wifi.setAddress1(rs.getString("address1"));
                wifi.setAddress2(rs.getString("address2"));
                wifi.setInstalled_floor(rs.getString("installed_floor"));
                wifi.setInstall_type(rs.getString("install_type"));
                wifi.setInstall_agency(rs.getString("install_agency"));
                wifi.setService_classification(rs.getString("service_classification"));
                wifi.setMesh_type(rs.getString("mesh_type"));
                wifi.setInstall_year(rs.getString("install_year"));
                wifi.setIn_out_door(rs.getString("in_out_door"));
                wifi.setConnection_environment(rs.getString("connection_environment"));
                wifi.setLatitude(rs.getDouble("latitude"));
                wifi.setLongitude(rs.getDouble("longitude"));
                wifi.setWorkDateStr(rs.getString("workDateStr"));

                result.add(wifi);
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


}
