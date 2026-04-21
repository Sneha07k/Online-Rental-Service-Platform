package findmyroomie;

import java.sql.*;
import java.util.*;

public class ApplicationDAO {

    public static boolean apply(int seekerId, int roomId, String message, String moveInDate, int durationMonths) {
        String sql = "INSERT INTO applications (seeker_id, room_id, message, status, move_in_date, duration_months) " +
                     "VALUES (?, ?, ?, 'PENDING', ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, seekerId);
            ps.setInt(2, roomId);
            ps.setString(3, message);
            ps.setString(4, moveInDate);
            ps.setInt(5, durationMonths);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error submitting application: " + e.getMessage());
            return false;
        }
    }

    public static List<Application> getBySeeker(int seekerId) {
        return getApplications("SELECT * FROM applications WHERE seeker_id = ?", seekerId);
    }

    public static List<Application> getByHost(int hostId) {
        String sql = "SELECT a.* FROM applications a JOIN rooms r ON a.room_id = r.id WHERE r.host_id = ?";
        return getApplications(sql, hostId);
    }

    public static boolean updateStatus(int appId, String status) {
        String sql = "UPDATE applications SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, appId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating application: " + e.getMessage());
            return false;
        }
    }

    private static List<Application> getApplications(String sql, int id) {
        List<Application> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error fetching applications: " + e.getMessage());
        }
        return list;
    }

    private static Application mapRow(ResultSet rs) throws SQLException {
        return new Application(
                rs.getInt("id"),
                rs.getInt("seeker_id"),
                rs.getInt("room_id"),
                rs.getString("message"),
                rs.getString("status"),
                rs.getString("applied_on"),
                rs.getString("move_in_date"),
                rs.getInt("duration_months")
        );
    }
}


