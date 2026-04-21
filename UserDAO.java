package findmyroomie;

import java.sql.*;
import java.util.*;

public class UserDAO {


    public static String lastError = "";

    public static boolean register(String name, String email, String password, String phone, String role,
                                   String aadhar, String smokingHabit, String drinkingHabit,
                                   String occupation, String gender, String preferredCity, int age, String bio) {
        lastError = "";


        String query = "INSERT INTO users " +
                       "(name, email, password, phone, role, aadhar, " +
                       "smoking_habit, drinking_habit, occupation, gender, preferred_city, age, bio) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.setString(5, role);

            ps.setString(6, aadhar.isEmpty()      ? null : aadhar);
            ps.setString(7, smokingHabit);
            ps.setString(8, drinkingHabit);
            ps.setString(9, occupation.isEmpty()   ? null : occupation);
            ps.setString(10, gender);
            ps.setString(11, preferredCity.isEmpty() ? null : preferredCity);
            ps.setInt(12, age);
            ps.setString(13, bio.isEmpty()         ? null : bio);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            lastError = e.getMessage();
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public static boolean emailExists(String email) {
        String query = "SELECT id FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            System.out.println("Error checking email: " + e.getMessage());
            return false;
        }
    }

    public static User login(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return null;
    }

    public static User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    
    public static List<User> getSeekersSortedByCompatibility(User currentUser) {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 'SEEKER' AND id != ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, currentUser.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching seekers: " + e.getMessage());
        }


        list.sort((a, b) -> b.compatibilityScore(currentUser) - a.compatibilityScore(currentUser));
        return list;
    }

    public static boolean updateProfile(int id, String name, String phone, String password,
                                        String smokingHabit, String drinkingHabit,
                                        String occupation, String preferredCity, int age, String bio) {
        String query = "UPDATE users SET name=?, phone=?, password=?, smoking_habit=?, " +
                       "drinking_habit=?, occupation=?, preferred_city=?, age=?, bio=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, password);
            ps.setString(4, smokingHabit);
            ps.setString(5, drinkingHabit);
            ps.setString(6, occupation);
            ps.setString(7, preferredCity);
            ps.setInt(8, age);
            ps.setString(9, bio);
            ps.setInt(10, id);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating profile: " + e.getMessage());
            return false;
        }
    }

    private static User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone"),
                rs.getString("role"),
                rs.getString("aadhar"),
                rs.getString("smoking_habit"),
                rs.getString("drinking_habit"),
                rs.getString("occupation"),
                rs.getString("gender"),
                rs.getString("preferred_city"),
                rs.getInt("age"),
                rs.getString("bio")
        );
    }
}

