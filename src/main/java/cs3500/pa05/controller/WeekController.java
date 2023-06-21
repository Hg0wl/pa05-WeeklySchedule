package cs3500.pa05.controller;

import cs3500.pa05.model.BujoOperator;
import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.DaysOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
  @FXML
  private ProgressBar sundayBar;
  @FXML
  private ProgressBar mondayBar;
  @FXML
  private ProgressBar tuesdayBar;
  @FXML
  private ProgressBar wednesdayBar;
  @FXML
  private ProgressBar thursdayBar;
  @FXML
  private ProgressBar fridayBar;
  @FXML
  private ProgressBar saturdayBar;
  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label percentTasksDone;
  @FXML
  private Label weekName;
  @FXML
  private Button createCategory;

  private final List<DayEvent> createdEvents;
  private List<DayTask> createdTasks;
  private List<Day> days;
  private Stage stage;
  private final String nameOfWeek;
  private final List<String> cats = new ArrayList<String>(List.of(""));
  private int maxEvents;
  private int maxTasks;

  /**
   * Constructor for WeekController
   *
   * @param createdEvents list of events that are displayed in the week
   */
  public WeekController(List<DayEvent> createdEvents, List<DayTask> createdTasks,
                        String weekName) {
    this.createdEvents = createdEvents;
    this.createdTasks = createdTasks;
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
    this.addNote.setOnAction(e -> handleAddNote());
    this.save.setOnAction(e -> handleSave());
    this.weekName.setText(nameOfWeek.toUpperCase());
  }

  private void handleReload() {
    this.clearDayBoxes();
    this.taskQueue.getChildren().remove(1, taskQueue.getChildren().size());
    this.addToWeek();
    this.updateProgressBar();
    this.updateOverview();
    this.updateNotes();
  }

  private void handleCreateCategory() {
    this.handleReload();
    Scene current = stage.getScene();
    CreateCategoryPopup catPopup = new CreateCategoryPopup();
    catPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.handleReload();
    });

    catPopup.displayPopup(cats, stage);
  }

  private void handleCreateEvent() {
    this.handleReload();
    Scene current = stage.getScene();
    AbstractPopup<DayEvent> eventPopup = new CreateEventPopup();
    eventPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();
      this.handleReload();});

    eventPopup.displayPopup(createdEvents, stage, cats);
  }

  private void handleCreateTask() {
    this.handleReload();
    Scene current = stage.getScene();
    AbstractPopup<DayTask> taskPopup = new CreateTaskPopup();
    taskPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();
      this.handleReload();});

    taskPopup.displayPopup(createdTasks, stage, cats);

  }

  private void handleAddNote() {
    this.handleReload();
    Scene current = stage.getScene();
    CreateNotePopup notePopup = new CreateNotePopup();
    notePopup.setOnHidden(e -> {
      stage.setScene(current);
      this.handleReload();
    });

    System.out.println("attempting to display note popup");
    notePopup.displayPopup(notes, stage);
    System.out.println("after displaying note popup");
  }

  /**
   * Initializes a week's GUI
   *
   * @param maxEvents integer representing the user set maximum number of events per day
   * @param maxTasks integer representing the user set maximum number of tasks per day
   * @throws IllegalStateException if events are not able to throw
   */
  public void run(int maxEvents, int maxTasks) throws IllegalStateException {
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;

    this.checkCommitment();
    this.commitmentWarning.setText("");
    this.initEvents();
    // call method to add events and tasks to the vbox
    this.addToWeek();

    //this.monday.getChildren().add(new Hyperlink("www.oracle.com"));
  }

  private void checkCommitment() {
    if (this.createdTasks.size() > maxTasks || this.createdEvents.size() > maxEvents) {
      this.commitmentWarning.setText("COMMITMENT WARNING: You might have too much going on this week");
    } else {
      this.commitmentWarning.setText("");
    }
  }

  private void addToWeek() {
    this.clearDayBoxes();
    this.addEvents();
    this.addTasks();
    this.addToQueue();
    this.checkCommitment();
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

  private void updateProgressBar() {
    Map<DaysOfWeek, Integer> mapCompleted = this.getNumTasksByStatus(CompleteStatus.COMPLETE);
    Map<DaysOfWeek, Integer> mapIncomplete = this.getNumTasksByStatus(CompleteStatus.INCOMPLETE);
    Map<DaysOfWeek, ProgressBar> mapProgress = this.initializeProgressMap();

    for (DaysOfWeek day : DaysOfWeek.values()) {
      int numCompleted = mapCompleted.get(day);
      int numTotal = mapCompleted.get(day) + mapIncomplete.get(day);
      double progress = (1.0 * numCompleted) / (1.0 * numTotal);
      mapProgress.get(day).setProgress(progress);
    }
  }

  private Map<DaysOfWeek, Integer> getNumTasksByStatus(CompleteStatus status) {
    Map<DaysOfWeek, Integer> mapping = new EnumMap<>(DaysOfWeek.class);
    for (DaysOfWeek days : DaysOfWeek.values()) {
      mapping.put(days, 0);
    }
    for (DayTask task : this.createdTasks) {
      DaysOfWeek curDay = task.getDay();
      if (task.getCompleteStatus() == status) {
        mapping.replace(curDay, mapping.get(curDay) + 1);
      }
    }
    return mapping;
  }
  private Map<DaysOfWeek, ProgressBar> initializeProgressMap() {
    Map<DaysOfWeek, ProgressBar> map = new EnumMap<>(DaysOfWeek.class);
    map.put(DaysOfWeek.SUNDAY, sundayBar);
    map.put(DaysOfWeek.MONDAY, mondayBar);
    map.put(DaysOfWeek.TUESDAY, tuesdayBar);
    map.put(DaysOfWeek.WEDNESDAY, wednesdayBar);
    map.put(DaysOfWeek.THURSDAY, thursdayBar);
    map.put(DaysOfWeek.FRIDAY, fridayBar);
    map.put(DaysOfWeek.SATURDAY, saturdayBar);
    return map;

  }

  private void updateOverview() {
    this.totalTasks.setWrapText(true);
    this.percentTasksDone.setWrapText(true);
    this.totalEvents.setWrapText(true);

    Map<DaysOfWeek, Integer> completedTasks = this.getNumTasksByStatus(CompleteStatus.COMPLETE);
    int numCompletedTasks = 0;
    for (DaysOfWeek day : completedTasks.keySet()) {
      numCompletedTasks += completedTasks.get(day);
    }
    int totalTasks = this.createdTasks.size();

    double percent;

    if (numCompletedTasks == 0 || totalTasks == 0) {
      percent = 0.0;
    } else {
      percent = (1.0 * numCompletedTasks) / (1.0 * totalTasks);
    }

    this.totalTasks.setText("Total Tasks: " + totalTasks);

    StringBuilder text = new StringBuilder();
    text.append("% Tasks Completed: ");
    text.append(String.format("%.2f", percent * 100));
    this.percentTasksDone.setText(text.toString());

    this.totalEvents.setText("Total Events: " + this.createdEvents.size());

  }

  private void updateNotes() {
    System.out.println("update");

    String note = this.notes.get(0);
    this.notesAndQuotes.getChildren().remove(1, notesAndQuotes.getChildren().size());
    Label notesLabel = new Label(note);
    notesLabel.setWrapText(true);
    this.notesAndQuotes.getChildren().add(notesLabel);
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
