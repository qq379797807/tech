package com.companyname.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
   // 数据库连接信息
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mysecretpassword";

    public static void main(String[] args) {
        try {
            // 1. 注册驱动 (JDBC 4.0+ 可自动加载，此步可选)
            Class.forName("org.postgresql.Driver");
            
            // 2. 建立连接
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connected to PostgreSQL database!");
                
                // 3. 创建表
                createTable(conn);
                
                // 4. 插入数据
                insertData(conn, 1, "John Doe", "john@example.com");
                
                // 5. 查询数据
                queryData(conn);
                
                // 6. 更新数据
                updateData(conn, 1, "john.updated@example.com");
                
                // 7. 删除数据
                // deleteData(conn, 1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 创建表
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id SERIAL PRIMARY KEY, " +
                     "name VARCHAR(100) NOT NULL, " +
                     "email VARCHAR(100) UNIQUE NOT NULL)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Table created successfully");
        }
    }

    // 插入数据
    private static void insertData(Connection conn, int id, String name, String email) throws SQLException {
        String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) inserted");
        }
    }

    // 查询数据
    private static void queryData(Connection conn) throws SQLException {
        String sql = "SELECT * FROM users";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s%n",
                                  rs.getInt("id"),
                                  rs.getString("name"),
                                  rs.getString("email"));
            }
        }
    }

    // 更新数据
    private static void updateData(Connection conn, int id, String newEmail) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newEmail);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) updated");
        }
    }

    // 删除数据
    private static void deleteData(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) deleted");
        }
    }
}
