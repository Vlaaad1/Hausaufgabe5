package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class EnrollmentJDBC_Repository {

    private String DB_URL;
    private String USER;
    private String PASSWORD;

    public void openConnection() throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\Vlad\\IDeaProjects\\Hausaufgabe5\\src\\main\\resources\\config.properties");
        Properties properties = new Properties();
        properties.load(file);
        DB_URL = properties.getProperty("DB_URL");
        USER = properties.getProperty("USER");
        PASSWORD = properties.getProperty("PASSWORD");
    }

    public ArrayList<Integer> findCoursesByStudentID(int studentID) throws IOException {
        openConnection();
        ArrayList<Integer> coursesIDs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); 
             Statement statement = connection.createStatement()) {
            String QUERY = "SELECT studentID, courseID FROM enrollment";
            ResultSet resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                if (resultSet.getInt("studentID") == studentID) {
                    coursesIDs.add(resultSet.getInt("courseID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursesIDs;
    }

    public ArrayList<Integer> findStudentsByCourseID(int courseID) throws IOException {
        openConnection();
        ArrayList<Integer> studentsIDs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String QUERY = "SELECT studentID, courseID FROM enrollment";
            ResultSet resultSet = statement.executeQuery(QUERY);

            while (resultSet.next()) {
                if (resultSet.getInt("courseID") == courseID) {
                    studentsIDs.add(resultSet.getInt("studentID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsIDs;
    }

    public boolean save(int studentID, int courseID) throws IOException {
        openConnection();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO enrollment VALUES (?,?)")) {
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, courseID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int studentID, int courseID) throws IOException {
        openConnection();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM enrollment WHERE studentID = ? AND courseID = ?")) {
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, courseID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudentsByCourseID(int courseID) throws IOException {
        openConnection();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM enrollment WHERE courseID = ?")) {
            preparedStatement.setInt(1, courseID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCoursesByStudentID(int studentID) throws IOException {
        openConnection();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM enrollment WHERE studentID = ?")) {
            preparedStatement.setInt(1, studentID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}