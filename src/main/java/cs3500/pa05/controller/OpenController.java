package cs3500.pa05.controller;

import cs3500.pa05.model.BujoOperator;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DaysOfWeek;
import cs3500.pa05.model.json.NotesJson;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Class for controlling the opening of a .bujo file or creating a new .bujo file
 */
public class OpenController {

  @FXML
  private TextField fileNameTextField;
  @FXML
  private Button submit;
  @FXML
  private Button exit;
  @FXML
  private Spinner<Integer> eventMaxSpinner;
  @FXML
  private Spinner<Integer> taskMaxSpinner;

  private Stage stage;

  private final List<PlannerJson> listPlanner;

  /**
   * Handles the opening of files from the open file view
   *
   * @param listPlanner the list of planner sent by the journal controller
   */
  public OpenController(List<PlannerJson> listPlanner) {
    this.listPlanner = listPlanner;
    listPlanner.clear();


  }

  /**
   *
   */
  public void run() {
    initEvents();
  }

  /**
   * Sets button events
   */
  private void initEvents() {
    this.submit.setOnAction(e -> handleSubmit());
    this.exit.setOnAction(e -> stage.hide());
    SpinnerValueFactory<Integer> valueFactoryEvents =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 420, 1);
    SpinnerValueFactory<Integer> valueFactoryTask =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 420, 1);
    this.eventMaxSpinner.setValueFactory(valueFactoryEvents);
    this.taskMaxSpinner.setValueFactory(valueFactoryTask);
  }

  /**
   * Handler for when the submit button is pressed
   */
  private void handleSubmit() {
    String pathName = this.fileNameTextField.getCharacters().toString();

    Path pathOfFile = Path.of(pathName);
    int maxEvents = this.eventMaxSpinner.getValue();
    int maxTasks = this.taskMaxSpinner.getValue();

    PlannerJson planner = BujoOperator.read(pathOfFile);
    if (planner.week() == null && planner.notes() == null) {
      // create a new list of days to pass back through the lists
      List<Day> days = new ArrayList<>();
      for (DaysOfWeek DaysOfWeek : DaysOfWeek.values()) {
        days.add(new Day(maxEvents, maxTasks, DaysOfWeek));
      }
      NotesJson notes = new NotesJson("");
      WeekJson week = new WeekJson(days, pathName);
      listPlanner.add(new PlannerJson(week, notes));
      stage.hide();
    } else {
      listPlanner.add(planner);
      stage.hide();
    }
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
