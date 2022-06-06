package vyva.vyva_phonebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
    * @author: Yiğit Bayraktaroglu @github: yigitbayraktaroglu
    * @author: Yusuf Kocaturk @github: yusufkctrk
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 725, 400);
        stage.setTitle("PhoneBook");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DbConnection.UsersSelectAll();
        launch();

    }
}