package cs3500.pa05.controller;

import cs3500.pa05.model.BujoOperator;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.json.NotesJson;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.ViewWeek;
import cs3500.pa05.view.ViewWeekImpl;
import cs3500.pa05.view.ViewOpenFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller class for the Java Journal
 */
public class ControlJournal extends Application {

  private ViewWeek view;
  private final ArrayList<DayEvent> eventsList = new ArrayList<>();
  private WeekController weekController;
  private final ViewOpenFile openFileView;
  private final List<PlannerJson> planners = new ArrayList<>();
  private final OpenController openController = new OpenController(planners);

  /**
   * Creates a new Controller
   */
  public ControlJournal() {

    this.openFileView = new ViewOpenFile(this.openController);
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Weekly Journal");


    try {

      Stage openFileStage = new Stage();
      this.openController.setStage(openFileStage);
      openFileStage.setScene(this.openFileView.load());
      this.openController.run();
      openFileStage.showAndWait();

      String weekName = planners.get(0).week().name();

      weekController = new WeekController(eventsList, weekName);
      this.weekController.setStage(stage);
      this.view = new ViewWeekImpl(this.weekController);

      Scene scene = this.view.load();
      stage.setScene(scene);
      this.weekController.run();

      stage.show();
      System.out.println("after stage.show");

    } catch (IllegalStateException e) {
      System.err.println("could not load layout");
    }
  }

  private void openWeek(Stage stage) {

  }

}
