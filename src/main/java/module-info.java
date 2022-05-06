module com.nicepeopleproject.simplegameproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nicepeopleproject.simplegameproject to javafx.fxml;
    exports com.nicepeopleproject.simplegameproject;
}