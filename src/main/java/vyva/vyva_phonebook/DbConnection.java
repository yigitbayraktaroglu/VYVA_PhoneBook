package vyva.vyva_phonebook;

import java.sql.*;
import java.util.ArrayList;


public class DbConnection {
    public static LinkedList UserslinkedList = new LinkedList();

    public static Connection Connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:database.sqlite";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void UsersSelectAll() {
        UserslinkedList.deleteList();
        String sql = "SELECT * FROM users";
        try (Connection conn = Connect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Users user = new Users(rs.getString("isim"), rs.getString("soyisim"), rs.getString("TelNo"));
                        UserslinkedList.push(user);
                    }
                    stmt.close();
                    conn.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void update(ArrayList<String> arrayList){

    }

    public static void main(String[] args) {
        UsersSelectAll();
        UserslinkedList.printList();
    }
}
