module labs.lab6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens labs.lab6 to javafx.fxml;
    exports labs.lab6;
}