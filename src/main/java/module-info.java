module com.github.jakobwilms.portalgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.jakobwilms.portalgame to javafx.fxml;
    exports com.github.jakobwilms.portalgame;
}