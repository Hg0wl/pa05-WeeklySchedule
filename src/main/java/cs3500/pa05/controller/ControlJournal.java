package cs3500.pa05.controller;

import cs3500.pa05.model.DayEvent;
import cs3500.pa05.view.ViewWeek;
import cs3500.pa05.view.ViewWeekImpl;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller class for the Java Journal
 */
public class ControlJournal extends Application {

  private final ViewWeek view;

  /**
   * Creates a new Controller
   */
  public ControlJournal() {
    this.view = new ViewWeekImpl();
  }

  @Override
  public void start(Stage stage) {
    this.weekView.setStage(stage);
    try {
      Scene scene = this.view.load();

      stage.setScene(scene);
      this.weekView.run();

      stage.show();
    } catch (IllegalStateException e) {
      System.err.println("could not load layout");
    }

  }

  private void setupView() {

  }

}
