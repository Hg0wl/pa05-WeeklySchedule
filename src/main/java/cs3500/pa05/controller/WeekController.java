package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DayTask;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a controller for the week on the journal
 */
public class WeekController {

  @FXML
  private VBox sunday;
  @FXML
  private VBox monday;
  @FXML
  private VBox tuesday;
  @FXML
  private VBox wednesday;
  @FXML
  private VBox thursday;
  @FXML
  private VBox friday;
  @FXML
  private VBox saturday;
  @FXML
  private Button createEvent;
  @FXML
  private Popup eventPopup;
  @FXML
  private Button createTask;
  @FXML
  private BorderPane borderPane;
  @FXML
  private VBox taskQueue;
  @FXML
  private Label commitmentWarning;

  private final List<DayEvent> createdEvents;
  private List<DayTask> createdTasks = new ArrayList<>();
  private List<Day> days;
  private Stage stage;

  /**
   * Constructor for WeekController
   *
   * @param createdEvents list of events that are displayed in the week
   */
  public WeekController(List<DayEvent> createdEvents) {
    this.createdEvents = createdEvents;
  }

  /**
   * Initializes events on interact-able objects
   */
  private void initEvents() {
    this.createEvent.setOnAction(e -> handleCreateEvent());
    this.createTask.setOnAction(e -> handleCreateTask());
    this.borderPane.setOnMouseClicked(e -> handleReload());
  }

  private void handleReload() {
    this.clearDayBoxes();
    this.taskQueue.getChildren().remove(1, taskQueue.getChildren().size());
    this.addToWeek();
  }

  private void handleCreateEvent() {
    Scene current = stage.getScene();
    AbstractPopup<DayEvent> eventPopup = new CreateEventPopup();
    eventPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();});

    eventPopup.displayPopup(createdEvents, stage);
  }

  private void handleCreateTask() {
    Scene current = stage.getScene();
    AbstractPopup<DayTask> taskPopup = new CreateTaskPopup();
    taskPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();});

    taskPopup.displayPopup(createdTasks, stage);

  }

  /**
   * Initializes a week's GUI
   *
   * @throws IllegalStateException <------ WRITE ------>
   */
  public void run() throws IllegalStateException {
    this.commitmentWarning.setText("");
    this.initEvents();
    // call method to add events and tasks to the vbox
    this.addToWeek();
  }

  /**
   * Sets this controller's stage to the given stage object
   *
   * @param stage the given stage object that will be used by this controller
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }





}
