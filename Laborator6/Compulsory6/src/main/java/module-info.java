module labs.lab6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens labs.lab6 to javafx.fxml;
    exports labs.lab6;
}