module com.example.totpalistvan_lotto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.totpalistvan_lotto to javafx.fxml;
    exports com.example.totpalistvan_lotto;
}