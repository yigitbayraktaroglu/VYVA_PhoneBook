package vyva.vyva_phonebook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static vyva.vyva_phonebook.DbConnection.Connect;
import static vyva.vyva_phonebook.DbConnection.UsersSelectAll;


public class HelloController implements Initializable {
    public static Stack stack = new Stack();
    Trie trie = new Trie();

    @FXML
    private ListView myListView;
    @FXML
    private ListView aranacakLV;
    @FXML
    private TextArea bulunan;

    @FXML
    private TextField isimInput;
    @FXML
    private TextField soyisimInput;
    @FXML
    private TextField telNoInput;
    @FXML
    private TextField araInput;

    //Uygulama başlatıgında ListView'a databaseden verilein yazılması için kullanılır.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myListView.getItems().setAll(DbConnection.UserslinkedList.getList());
    }

    //Yeni kisi eklemek icin kullanılır.
    @FXML
    public void ekle() {
        insert(telNoInput.getText(), isimInput.getText(), soyisimInput.getText());//textFieldlardan veriler çekilerek
        // insert fonksiyonuna gönderilir
        //Textfieldlar temizlenir
        isimInput.clear();
        soyisimInput.clear();
        telNoInput.clear();
        UsersSelectAll();
        //listView kişi eklendiği için güncellenir.
        myListView.getItems().setAll(DbConnection.UserslinkedList.getList());

    }

    //Database yeni kişi ekler
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

    //ListViewda seçilen kişiyi siler.
    @FXML
    public void sil() {
        delete();
        UsersSelectAll();
        myListView.getItems().setAll(DbConnection.UserslinkedList.getList());
    }

    //Databaseden seçilen kişiyi  silmek için kullanılır.
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

    //ALfabetik olarak isimlerin sıralanması için kullanılır.
    @FXML
    public void isimSırala() {
        ArrayList<String> arrayList = DbConnection.UserslinkedList.getList();

        BubbleSort.sort(arrayList);
        //Database yeni sıralanmış haliyle güncellenir.
        DbConnection.clearAll(arrayList);
        DbConnection.updateAll(arrayList);

        myListView.getItems().setAll(arrayList);

    }

//Girilen değere gören sonuç veren bulma algoritması
    @FXML
    public void bul() {
        bulunan.clear();
        ArrayList<String> arrayList = DbConnection.UserslinkedList.getList();
        String araInp = araInput.getText();
        araInput.clear();
        String str;
        //linkedlistten arraylisten çverilen değer bölünerek isim olarak ayrılı ve bu isim değerleri
        //trie'ye eklenir.
        String splited[] = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            str = (String) arrayList.get(i);
            String splitStr[] = str.split("\\s+");
            splited[i] = splitStr[0];

        }
        trie.insertIntoTrie(splited);
        trie.displayContacts(araInp, bulunan);
    }
//Aranacak kişiler için oluşturulana stacka kişi ekler.
    @FXML
    public void aranacakEkle() {
        ArrayList<String> arrayList = DbConnection.UserslinkedList.getList();
        Integer selected = myListView.getSelectionModel().getSelectedIndex();
        String str = (String) arrayList.get(selected);
        String[] splitStr = str.split("\\s+");
        stack.push(splitStr[0] + " " + splitStr[1] + "\n");
        aranacakLV.getItems().setAll(stack.getAll());


    }
//aranan kişileri stackten poplar.
    @FXML
    public void ara() {
        try {
            stack.pop();
            aranacakLV.getItems().setAll(stack.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}