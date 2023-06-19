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

  private final ViewWeek view;
  private final ArrayList<DayEvent> eventsList = new ArrayList<>();
  private final WeekController weekView = new WeekController(eventsList);
  private final ViewOpenFile openFileView;
  private final OpenController openController = new OpenController();

  /**
   * Creates a new Controller
   */
  public ControlJournal() {
    this.view = new ViewWeekImpl(this.weekView);
    this.openFileView = new OpenFileView(this.openController);
  }

  @Override
  public void start(Stage stage) {

//    Path path = null; //TODO - get path in here some how
//    PlannerJson plannerContent = BujoOperator.read(path);
//    WeekJson weekContent = plannerContent.week();
//    NotesJson notesContent = plannerContent.notes();
//    List<Day> listOfDays = weekContent.days();
//    String name = weekContent.name();
//    String notes = notesContent.notes();
//    // TODO - send contents over to week controller?

    stage.setTitle("Weekly Journal");

    //this.openFileView.setStage(stage);
    try {
      //Scene scene = this.openFileView.load();
    //  stage.setScene(scene);
     // this.openFileView.run();

      stage.show();

    } catch(IllegalStateException e) {
      System.err.println("could not load open file layout");
    }

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
