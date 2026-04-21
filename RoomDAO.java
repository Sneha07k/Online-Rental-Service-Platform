package findmyroomie;

import java.sql.*;
import java.util.*;

public class RoomDAO {

    public static boolean addRoom(String title, String city, double rent, boolean furnished, int hostId,
                                   String description, String imagePath, int bedrooms, int bathrooms,
                                   String roomType, boolean petsAllowed, boolean smokingAllowed,
                                   String amenities, String availableFrom) {
        String query = "INSERT INTO rooms (title, city, rent, furnished, host_id, description, " +
                       "image_path, bedrooms, bathrooms, room_type, pets_allowed, smoking_allowed, " +
                       "amenities, available_from) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setString(2, city);
            ps.setDouble(3, rent);
            ps.setBoolean(4, furnished);
            ps.setInt(5, hostId);
            ps.setString(6, description);
            ps.setString(7, imagePath);
            ps.setInt(8, bedrooms);
            ps.setInt(9, bathrooms);
            ps.setString(10, roomType);
            ps.setBoolean(11, petsAllowed);
            ps.setBoolean(12, smokingAllowed);
            ps.setString(13, amenities);
            ps.setString(14, availableFrom);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding room: " + e.getMessage());
            return false;
        }
    }

    public static List<Room> getRoomsByHost(int hostId) {
        return queryRooms("SELECT * FROM rooms WHERE host_id = ?", hostId, "", 0);
    }

    public static List<Room> searchRooms(String city, double maxRent) {
        String query = "SELECT * FROM rooms WHERE (? = '' OR LOWER(city) LIKE LOWER(?)) AND (? = 0 OR rent <= ?)";
        List<Room> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, city);
            ps.setString(2, "%" + city + "%");
            ps.setDouble(3, maxRent);
            ps.setDouble(4, maxRent);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error searching rooms: " + e.getMessage());
        }

        return list;
    }

    public static Room getRoomById(int id) {
        String query = "SELECT * FROM rooms WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error fetching room: " + e.getMessage());
        }
        return null;
    }

    public static boolean deleteRoom(int id) {
        String query = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting room: " + e.getMessage());
            return false;
        }
    }

    private static List<Room> queryRooms(String sql, int intParam, String strParam, double dblParam) {
        List<Room> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, intParam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }
        return list;
    }

    private static Room mapRow(ResultSet rs) throws SQLException {
        return new Room(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("city"),
                rs.getDouble("rent"),
                rs.getBoolean("furnished"),
                rs.getInt("host_id"),
                rs.getString("description"),
                rs.getString("image_path"),
                rs.getInt("bedrooms"),
                rs.getInt("bathrooms"),
                rs.getString("room_type"),
                rs.getBoolean("pets_allowed"),
                rs.getBoolean("smoking_allowed"),
                rs.getString("amenities"),
                rs.getString("available_from")
        );
    }
}


