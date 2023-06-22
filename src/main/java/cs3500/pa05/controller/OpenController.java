package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DaysOfWeek;
import cs3500.pa05.model.json.NotesJson;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
  @FXML
  private CheckBox overrideCheckbox;

  private Stage stage;

  private final List<PlannerJson> listPlanner;
  private final List<Integer> maximums;

  /**
   * Handles the opening of files from the open file view
   *
   * @param listPlanner the list of planner sent by the journal controller
   * @param maximums the list of max events and max tasks
   */
  public OpenController(List<PlannerJson> listPlanner, List<Integer> maximums) {
    this.listPlanner = listPlanner;
    listPlanner.clear();
    this.maximums = maximums;
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
    StringBuilder pathNameWithExtension = new StringBuilder(pathName).append(".bujo");
    Path pathOfFile = Path.of(pathNameWithExtension.toString());
    int maxEvents = this.eventMaxSpinner.getValue();
    int maxTasks = this.taskMaxSpinner.getValue();

    PlannerJson planner = BujoOperator.read(pathOfFile);
    if (planner.week() == null && planner.notes() == null) {
      // create a new list of days to pass back through the lists
      List<Day> days = new ArrayList<>();
      for (DaysOfWeek daysOfWeek : DaysOfWeek.values()) {
        days.add(new Day(maxEvents, maxTasks, daysOfWeek));
      }
      NotesJson notes = new NotesJson("");
      WeekJson week = new WeekJson(days, new ArrayList<>(List.of("")), pathName);
      listPlanner.add(new PlannerJson(week, notes, ""));
      maximums.add(this.eventMaxSpinner.getValue());
      maximums.add(this.taskMaxSpinner.getValue());

    } else {
      // use the previously created planner
      listPlanner.add(planner);
      // if the override box is checked, then the file will be updated with the newly given values
      if (this.overrideCheckbox.isSelected()) {
        maximums.add(this.eventMaxSpinner.getValue());
        maximums.add(this.taskMaxSpinner.getValue());
      } else {
        // otherwise it will keep its previously saved maximums
        maximums.add(planner.week().days().get(0).getMaxEvents());
        maximums.add(planner.week().days().get(0).getMaxTasks());
      }

    }
    stage.hide();
  }

  /**
   * Set's this controller's stage to the given
   *
   * @param stage the stage that we want to use
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
