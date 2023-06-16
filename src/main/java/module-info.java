/**
 * module info file
 */
module cs3500.pa05 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens cs3500.pa05 to javafx.fxml;
    exports cs3500.pa05;
    exports cs3500.pa05.model;
    exports cs3500.pa05.view;
    exports cs3500.pa05.controller;
    opens cs3500.pa05.controller to javafx.fxml;

   // opens cs3500.pa05.model to javafx.fxml;
   // exports cs3500.pa05.model.json;
   // opens cs3500.pa05.model.json to javafx.fxml;
}