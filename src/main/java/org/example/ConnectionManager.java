package org.example;

import java.sql.*;
import java.util.Map;

public class ConnectionManager {
    private String url;
    private String user;
    private String password;
    private Connection conn;

    public ConnectionManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(url, user, password);
        System.out.println("✅ Lidhja me DB u realizua");
    }

    public Connection getConnection() {
        return this.conn;
    }


    public void createTable(String tableName, Map<String, String> koloneTip) throws SQLException {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(tableName).append(" (");
        for (Map.Entry<String, String> entry : koloneTip.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
        }
        sb.setLength(sb.length() - 2); // heq presjen dhe hapësirën e fundit
        sb.append(")");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sb.toString());
            System.out.println("Tabela " + tableName + " u krijua.");
        }
    }


    public void dropTable(String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS " + tableName);
            System.out.println("Tabela " + tableName + " u fshi.");
        }
    }


    public void insertStudent(Student student) throws SQLException {
        if (student == null || student.getEmri() == null || student.getPiket() == null) {
            throw new IllegalArgumentException("Studenti ose të dhënat e tij janë null");
        }
        String sql = "INSERT INTO studenti (emri, piket) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getEmri());
            ps.setInt(2, student.getPiket());
            ps.executeUpdate();
            System.out.println("Studenti u shtua: " + student.getEmri());
        }
    }


    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM studenti WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("id"), rs.getString("emri"), rs.getInt("piket"));
                } else {
                    System.out.println("Studenti me ID " + id + " nuk ekziston.");
                    return null;
                }
            }
        }
    }


    public void updateStudent(int id, Student student) throws SQLException {
        if (student == null || student.getEmri() == null || student.getPiket() == null) {
            throw new IllegalArgumentException("Studenti ose të dhënat e tij janë null");
        }
        String sql = "UPDATE studenti SET emri = ?, piket = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getEmri());
            ps.setInt(2, student.getPiket());
            ps.setInt(3, id);
            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Studenti me ID " + id + " u modifikua.");
            } else {
                System.out.println("Studenti me ID " + id + " nuk ekziston.");
            }
        }
    }


    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM studenti WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted > 0) {
                System.out.println("Studenti me ID " + id + " u fshi.");
            } else {
                System.out.println("Studenti me ID " + id + " nuk ekziston.");
            }
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("Lidhja me DB u mbyll.");
        }
    }
}
