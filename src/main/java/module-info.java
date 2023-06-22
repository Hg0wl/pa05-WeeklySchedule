/**
 * module info file
 */
module cs3500.pa05 {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires org.controlsfx.controls;
  requires java.desktop;

  opens cs3500.pa05 to javafx.fxml;
  exports cs3500.pa05;
  exports cs3500.pa05.model;
  exports cs3500.pa05.view;
  exports cs3500.pa05.controller;
  exports cs3500.pa05.model.json;
  opens cs3500.pa05.model.json to javafx.fxml;
  exports cs3500.pa05.controller.popups;
  opens cs3500.pa05.controller.popups to javafx.fxml;
  opens cs3500.pa05.model to com.fasterxml.jackson.databind, javafx.fxml;
  opens cs3500.pa05.controller to com.fasterxml.jackson.databind, javafx.fxml;
  exports cs3500.pa05.controller.fxadapters;
  opens cs3500.pa05.controller.fxadapters to com.fasterxml.jackson.databind, javafx.fxml;
}