module com.example.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.desktopapp to javafx.fxml;
    exports com.example.desktopapp;

    exports com.example.desktopapp.controllers.adminControllers.addContollers;
    opens com.example.desktopapp.controllers.adminControllers.addContollers to javafx.fxml;

    exports com.example.desktopapp.controllers.authControllers;
    opens com.example.desktopapp.controllers.authControllers to javafx.fxml;

    exports com.example.desktopapp.controllers.adminControllers;
    opens com.example.desktopapp.controllers.adminControllers to javafx.fxml;

    exports com.example.desktopapp.DAO;
    opens com.example.desktopapp.DAO to javafx.fxml;

    exports com.example.desktopapp.controllers.adminControllers.showControllers;
    opens com.example.desktopapp.controllers.adminControllers.showControllers to javafx.fxml;

    exports com.example.desktopapp.models;
    opens com.example.desktopapp.models to javafx.base;

    exports com.example.desktopapp.controllers.adminControllers.changeControllers;
    opens com.example.desktopapp.controllers.adminControllers.changeControllers to javafx.fxml;
}