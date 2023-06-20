package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.DaysOfWeek;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
  private final String nameOfWeek;
  private final List<String> cats = new ArrayList<>(List.of(""));

  /**
   * Constructor for WeekController
   *
   * @param createdEvents list of events that are displayed in the week
   */
  public WeekController(List<DayEvent> createdEvents, String weekName) {
    this.createdEvents = createdEvents;
    this.nameOfWeek = weekName;
  }

  /**
   * Initializes events on interact-able objects
   */
  private void initEvents() {
    this.createEvent.setOnAction(e -> handleCreateEvent());
    this.createTask.setOnAction(e -> handleCreateTask());
    this.borderPane.setOnMouseClicked(e -> handleReload());
    this.createCategory.setOnAction(e -> handleCreateCategory());

    this.weekName.setText(nameOfWeek.toUpperCase());
  }

  private void handleReload() {
    this.clearDayBoxes();
    this.taskQueue.getChildren().remove(1, taskQueue.getChildren().size());
    this.addToWeek();
  }

  private void handleCreateCategory() {
    Scene current = stage.getScene();
    CreateCategoryPopup catPopup = new CreateCategoryPopup();
    catPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.handleReload();
    });

    catPopup.displayPopup(cats, stage);
  }

  private void handleCreateEvent() {
    Scene current = stage.getScene();
    AbstractPopup<DayEvent> eventPopup = new CreateEventPopup();
    eventPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();
      this.handleReload();});

    eventPopup.displayPopup(createdEvents, stage, cats);
  }

  private void handleCreateTask() {
    Scene current = stage.getScene();
    AbstractPopup<DayTask> taskPopup = new CreateTaskPopup();
    taskPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();
      this.handleReload();});

    taskPopup.displayPopup(createdTasks, stage, cats);

  }

  /**
   * Initializes a week's GUI
   *
   * @throws IllegalStateException if events are not able to be thrown
   */
  public void run() throws IllegalStateException {

    this.commitmentWarning.setText("");
    this.initEvents();
    // call method to add events and tasks to the vbox
    this.addToWeek();

    //this.monday.getChildren().add(new Hyperlink("www.oracle.com"));
  }

  private void addToWeek() {
    this.clearDayBoxes();
    this.addEvents();
    this.addTasks();
    this.addToQueue();
  }

  private void addToQueue() {
    for (DayTask task : this.createdTasks) {
      this.taskQueue.getChildren().add(TaskQueueLabel.create(task));
    }
  }

  private void addEvents() {
    for (DayEvent event : this.createdEvents) {
      DaysOfWeek day = event.getDay();
      switch (day) {
        case SUNDAY -> this.sunday.getChildren().add(EventLabel.create(event));
        case MONDAY -> this.monday.getChildren().add(EventLabel.create(event));
        case TUESDAY -> this.tuesday.getChildren().add(EventLabel.create(event));
        case WEDNESDAY -> this.wednesday.getChildren().add(EventLabel.create(event));
        case THURSDAY -> this.thursday.getChildren().add(EventLabel.create(event));
        case FRIDAY -> this.friday.getChildren().add(EventLabel.create(event));
        default -> this.saturday.getChildren().add(EventLabel.create(event));
      }
    }
  }

  private void addTasks() {
    for (DayTask task : this.createdTasks) {
      DaysOfWeek day = task.getDay();
      switch (day) {
        case SUNDAY -> this.sunday.getChildren().add(TaskCheckbox.create(task));
        case MONDAY -> this.monday.getChildren().add(TaskCheckbox.create(task));
        case TUESDAY -> this.tuesday.getChildren().add(TaskCheckbox.create(task));
        case WEDNESDAY -> this.wednesday.getChildren().add(TaskCheckbox.create(task));
        case THURSDAY -> this.thursday.getChildren().add(TaskCheckbox.create(task));
        case FRIDAY -> this.friday.getChildren().add(TaskCheckbox.create(task));
        default -> this.saturday.getChildren().add(TaskCheckbox.create(task));
      }
    }
  }

  private void clearDayBoxes() {
    List<VBox> vboxList = List.of(this.sunday, this.monday, this.tuesday,
        this.wednesday, this.thursday, this.friday, this.saturday);

    for (VBox days : vboxList) {
      days.getChildren().remove(1, days.getChildren().size());
    }
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
