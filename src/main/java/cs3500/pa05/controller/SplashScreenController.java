package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a controller for the splash screen
 */
public class SplashScreenController {

  private Stage stage;
  @FXML
  private AnchorPane splash;

  /**
   * Sets the splash screen stage
   *
   * @param stage the stage to show the splash screen on
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  /**
   * Runs the controller
   */
  public void run() {
    splash.setOnMouseClicked(e -> stage.hide());
  }
}
