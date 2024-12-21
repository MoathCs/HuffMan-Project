module com.example.algo_project1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algo_project1 to javafx.fxml;
    exports com.example.algo_project1;
}