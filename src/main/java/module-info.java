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
    exports com.example.desktopapp.controllers;
    opens com.example.desktopapp.controllers to javafx.fxml;
}