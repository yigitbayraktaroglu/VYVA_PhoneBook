module vyva.vyva_phonebook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens vyva.vyva_phonebook to javafx.fxml;
    exports vyva.vyva_phonebook;
}