package cs3500.pa05.controller;

import cs3500.pa05.controller.fxadapters.EventLabel;
import cs3500.pa05.controller.fxadapters.TaskCheckbox;
import cs3500.pa05.controller.fxadapters.TaskQueueLabel;
import cs3500.pa05.controller.popups.AbstractPopup;
import cs3500.pa05.controller.popups.CreateCategoryPopup;
import cs3500.pa05.controller.popups.CreateEventPopup;
import cs3500.pa05.controller.popups.CreateNotePopup;
import cs3500.pa05.controller.popups.CreateTaskPopup;
import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.DaysOfWeek;
import cs3500.pa05.model.json.NotesJson;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Path;
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
import javafx.stage.Stage;

/**
 * Represents a controller for the week on the journal
 */
public class WeekController {

  // vboxes for each day
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
  // labels on top of each progress bar
  @FXML
  private Label sunLabel;
  @FXML
  private Label monLabel;
  @FXML
  private Label tueLabel;
  @FXML
  private Label wedLabel;
  @FXML
  private Label thuLabel;
  @FXML
  private Label friLabel;
  @FXML
  private Label satLabel;
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
  @FXML
  private Button addNote;
  @FXML
  private Button save;
  @FXML
  private VBox notesAndQuotes;

  private List<String> notes = new ArrayList<>(List.of(""));
  private final List<DayEvent> createdEvents;
  private List<DayTask> createdTasks;
  private List<Day> days;
  private Stage stage;
  private final String nameOfWeek;
  private final List<String> cats;
  private int maxEvents;
  private int maxTasks;
  private final String password;

  /**
   * Constructor for WeekController
   *
   * @param createdEvents list of events that are displayed in the week
   * @param createdTasks list of tasks that are displayed in the week
   * @param weekName given name of the week
   * @param categories list of categories
   */
  public WeekController(List<DayEvent> createdEvents, List<DayTask> createdTasks,
                        String weekName, List<String> categories, String password)  {
    this.createdEvents = createdEvents;
    this.createdTasks = createdTasks;
    this.nameOfWeek = weekName;
    this.cats = categories;
    this.password = password;
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

  /**
   * Handles saving the week by collecting all the information in the week, and
   * writing to a file using the BujoOperator
   */
  private void handleSave() {
    this.handleReload();
    Map<DaysOfWeek, List<DayEvent>> listOfEventsForEachDay = this.mapEventsToDays();
    Map<DaysOfWeek, List<DayTask>> listOfTasksForEachDay = this.mapTasksToDays();
    List<Day> dayList = new ArrayList<>();
    for (DaysOfWeek day : DaysOfWeek.values()) {
      dayList.add(new Day(this.maxEvents, this.maxTasks, day,
          listOfEventsForEachDay.get(day), listOfTasksForEachDay.get(day)));
    }

    WeekJson week = new WeekJson(dayList, this.cats, this.nameOfWeek);
    NotesJson notesJson = new NotesJson(this.notes.get(0));
    PlannerJson planner = new PlannerJson(week, notesJson, this.password);
    StringBuilder pathName = new StringBuilder(nameOfWeek).append(".bujo");
    BujoOperator.write(planner, Path.of(pathName.toString()));
  }

  /**
   * Helper that maps days of the week to a list of events associated to that day
   *
   * @return a mapping of days to list of events
   */
  private Map<DaysOfWeek, List<DayEvent>> mapEventsToDays() {

    Map<DaysOfWeek, List<DayEvent>> output = new EnumMap<>(DaysOfWeek.class);
    for (DaysOfWeek day : DaysOfWeek.values()) {
      output.put(day, new ArrayList<>());
    }
    for (DayEvent event : this.createdEvents) {
      DaysOfWeek curDay = event.getDay();
      output.get(curDay).add(event);
    }
    return output;
  }

  /**
   * Helper that maps days of hte week to a list of tasks associated to that day
   *
   * @return a mapping of days to a list of tasks
   */
  private Map<DaysOfWeek, List<DayTask>> mapTasksToDays() {
    Map<DaysOfWeek, List<DayTask>> output = new EnumMap<>(DaysOfWeek.class);
    for (DaysOfWeek day : DaysOfWeek.values()) {
      output.put(day, new ArrayList<>());
    }
    for (DayTask task : this.createdTasks) {
      DaysOfWeek curDay = task.getDay();
      output.get(curDay).add(task);
    }
    return output;
  }

  /**
   * Reloads and updates the week
   */
  private void handleReload() {
    this.clearDayBoxes();
    this.taskQueue.getChildren().remove(1, taskQueue.getChildren().size());
    this.addToWeek();
    this.updateProgressBar();
    this.updateOverview();
    this.updateNotes();
  }

  /**
   * Helper that adds content to the week and warns the user if exceeded commitment numbers
   */
  private void addToWeek() {
    this.clearDayBoxes();
    this.addEvents();
    this.addTasks();
    this.addToQueue();
    this.checkCommitment();
  }

  /**
   * Handles what happens when the user clicks the create category button
   */
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

  /**
   * Handles what happens when the user clicks the create event button
   */
  private void handleCreateEvent() {
    this.handleReload();
    Scene current = stage.getScene();
    AbstractPopup<DayEvent> eventPopup = new CreateEventPopup();
    eventPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();
      this.handleReload(); });

    eventPopup.displayPopup(createdEvents, stage, cats);
  }

  /**
   * Handles what happens when the user clicks the create task button
   */
  private void handleCreateTask() {
    this.handleReload();
    Scene current = stage.getScene();
    AbstractPopup<DayTask> taskPopup = new CreateTaskPopup();
    taskPopup.setOnHidden(e -> {
      stage.setScene(current);
      this.addToWeek();
      this.handleReload(); });

    taskPopup.displayPopup(createdTasks, stage, cats);
  }

  /**
   * Handles what happens when the user clicks the add note button
   */
  private void handleAddNote() {
    this.handleReload();
    Scene current = stage.getScene();
    CreateNotePopup notePopup = new CreateNotePopup();
    notePopup.setOnHidden(e -> {
      stage.setScene(current);
      this.handleReload();
    });
    notePopup.displayPopup(notes, stage);
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
      this.commitmentWarning
          .setText("COMMITMENT WARNING: You might have too much going on this week");
    } else {
      this.commitmentWarning.setText("");
    }
  }

  /**
   * Helper that adds tasks to the queue panel
   */
  private void addToQueue() {
    for (DayTask task : this.createdTasks) {
      this.taskQueue.getChildren().add(TaskQueueLabel.create(task));
    }
  }

  /**
   * Helper that adds events to the week
   */
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

  /**
   * Helper that adds tasks to the week
   */
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

  /**
   * Handles clearing each day's boxes
   */
  private void clearDayBoxes() {
    List<VBox> vboxList = List.of(this.sunday, this.monday, this.tuesday,
        this.wednesday, this.thursday, this.friday, this.saturday);

    for (VBox days : vboxList) {
      days.getChildren().remove(1, days.getChildren().size());
    }
  }

  /**
   * Handles updating the progress bar
   */
  private void updateProgressBar() {
    Map<DaysOfWeek, Integer> mapCompleted = this.getNumTasksByStatus(CompleteStatus.COMPLETE);
    Map<DaysOfWeek, Integer> mapIncomplete = this.getNumTasksByStatus(CompleteStatus.INCOMPLETE);
    Map<DaysOfWeek, ProgressBar> mapProgress = this.initializeProgressMap();
    Map<DaysOfWeek, Label> mapLabelProgress = this.initializeProgressLabelMap();

    for (DaysOfWeek day : DaysOfWeek.values()) {
      int numCompleted = mapCompleted.get(day);
      int numTotal = mapCompleted.get(day) + mapIncomplete.get(day);
      double progress = (1.0 * numCompleted) / (1.0 * numTotal);
      mapProgress.get(day).setProgress(progress);
      mapLabelProgress.get(day).setText(numCompleted + "/" + numTotal);
    }
  }

  /**
   * Gets the number of tasks according to the complete status supplied
   *
   * @param status the status of the tasks to look for
   * @return a mapping of the days of the week and the number of tasks
   (that matches the supplied status) on that day
   */
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

  /**
   * Helper that creates a mapping between days of the week to their respective progress bar objects
   *
   * @return a mapping between days of the week and their progress bars
   */
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

  /**
   * Helper that creates a mapping between days of the week to their respective label
   *
   * @return a mapping of days of the week to labels
   */
  private Map<DaysOfWeek, Label> initializeProgressLabelMap() {
    Map<DaysOfWeek, Label> labelMap = new EnumMap<>(DaysOfWeek.class);
    labelMap.put(DaysOfWeek.SUNDAY, sunLabel);
    labelMap.put(DaysOfWeek.MONDAY, monLabel);
    labelMap.put(DaysOfWeek.TUESDAY, tueLabel);
    labelMap.put(DaysOfWeek.WEDNESDAY, wedLabel);
    labelMap.put(DaysOfWeek.THURSDAY, thuLabel);
    labelMap.put(DaysOfWeek.FRIDAY, friLabel);
    labelMap.put(DaysOfWeek.SATURDAY, satLabel);
    return labelMap;
  }

  /**
   * Handles updating the overview panel
   */
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

  /**
   * Handles updating the notes field
   */
  private void updateNotes() {
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
