package vyva.vyva_phonebook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static vyva.vyva_phonebook.DbConnection.Connect;
import static vyva.vyva_phonebook.DbConnection.UsersSelectAll;
import static vyva.vyva_phonebook.TrieMain.printSorted;

public class HelloController implements Initializable {
    public static Stack stack = new Stack();

    @FXML
    private ListView myListView;
    @FXML
    private ListView aranacakLV;
    @FXML
    private ListView donusLV;
    @FXML
    private TextField isimInput;
    @FXML
    private TextField soyisimInput;
    @FXML
    private TextField telNoInput;
    @FXML
    private TextField araInput;
    @FXML
    private Button ekle;
    @FXML
    private Button sil;
    @FXML
    private Button isimSırala;
    @FXML
    private Button soyisimSırala;
    @FXML
    private Button ara;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myListView.getItems().setAll(DbConnection.UserslinkedList.getList());
    }

    @FXML
    public void ekle() {
        insert(telNoInput.getText(), isimInput.getText(), soyisimInput.getText());
        isimInput.clear();
        soyisimInput.clear();
        telNoInput.clear();
        UsersSelectAll();
        myListView.getItems().setAll(DbConnection.UserslinkedList.getList());

    }

    public void insert(String telNo, String isim, String soyisim) {
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

    @FXML
    public void sil() {
        delete();
        UsersSelectAll();
        myListView.getItems().setAll(DbConnection.UserslinkedList.getList());
    }

    public void delete() {
        String sql = "DELETE FROM users WHERE isim = ?";
        ArrayList array = new ArrayList();
        array = DbConnection.UserslinkedList.getList();
        Integer selected = myListView.getSelectionModel().getSelectedIndex();

        String str = (String) array.get(selected);
        String[] splitStr = str.split("\\s+");

        try (Connection conn = Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, splitStr[0]);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void isimSırala() {
        ArrayList<String> arrayList = DbConnection.UserslinkedList.getList();
        BubbleSort.sort(arrayList);
        myListView.getItems().setAll(arrayList);


    }


    @FXML
    public void bul() {
        String araInp = araInput.getText();
        araInput.clear();
        System.out.println(araInp);
    }

    @FXML
    public void aranacakEkle() {
        ArrayList<String> arrayList = DbConnection.UserslinkedList.getList();
        Integer selected = myListView.getSelectionModel().getSelectedIndex();
        String str = (String) arrayList.get(selected);
        String[] splitStr = str.split("\\s+");
        stack.push(splitStr[0] + " " + splitStr[1] + "\n");
        aranacakLV.getItems().setAll(stack.getAll());


    }
    @FXML
    public void ara(){
        try{
            stack.pop();
            aranacakLV.getItems().setAll(stack.getAll());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void donusEkle() {

    }

    @FXML
    public void donus() {
    }
}