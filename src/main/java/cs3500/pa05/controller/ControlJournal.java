package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.view.ViewWeek;
import cs3500.pa05.view.ViewWeekImpl;
import cs3500.pa05.view.ViewOpenFile;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
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
  private final List<Integer> maximums = new ArrayList<>();
  private final OpenController openController = new OpenController(planners, maximums);


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

      List<DayEvent> events = extractEvents(planners.get(0));
      List<DayTask> tasks = extractTasks(planners.get(0));

      weekController = new WeekController(events, tasks, weekName);
      this.weekController.setStage(stage);
      this.view = new ViewWeekImpl(this.weekController);

      Scene scene = this.view.load();
      stage.setScene(scene);
      this.weekController.run(maximums.get(0), maximums.get(1));

      stage.show();
      System.out.println("after stage.show");

    } catch (IllegalStateException e) {
      System.err.println(e.getMessage());
      //System.err.println("could not load layout - control journal");
    }
  }

  private void openWeek(Stage stage) {

  }

  private List<DayEvent> extractEvents(PlannerJson plannerJson) {
    List<Day> days = plannerJson.week().days();
    List<DayEvent> events = new ArrayList<>();
    for (Day eachDay : days) {
      events.addAll(eachDay.getEvents());
    }
    return events;
  }

  private List<DayTask> extractTasks(PlannerJson plannerJson) {
    List<Day> days = plannerJson.week().days();
    List<DayTask> tasks = new ArrayList<>();
    for (Day eachDay : days) {
      tasks.addAll(eachDay.getTasks());
    }
    return tasks;
  }

}
