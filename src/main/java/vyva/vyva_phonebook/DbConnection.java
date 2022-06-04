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
    public static void clearAll(ArrayList arrayList){
        String isim;
        for(int i=0;i<arrayList.size();i++){
            String str = (String) arrayList.get(i);
            String[] splitStr = str.split("\\s+");
            isim=splitStr[0];
            delete(isim);
        }

    }
    public static void delete(String isim) {
        String sql = "DELETE FROM users WHERE isim = ?";

        try (Connection conn = Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, isim);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
public static void updateAll(ArrayList arrayList){
        String isim,soyisim,telNo;
        for(int i=0;i<arrayList.size();i++){
            String str = (String) arrayList.get(i);
            String[] splitStr = str.split("\\s+");
            isim=splitStr[0];
            soyisim=splitStr[1];
            telNo=splitStr[2];
            insert(telNo,isim,soyisim);
        }

    }
    public static void insert(String telNo, String isim, String soyisim) {
        String sql = "INSERT INTO users(isim, soyisim, telNo) VALUES(?,?,?)";
        try (Connection conn = Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isim);
            pstmt.setString(2, soyisim);
            pstmt.setString(3, telNo);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
