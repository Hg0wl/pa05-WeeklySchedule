package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.view.SplashScreen;
import cs3500.pa05.view.ViewOpenFile;
import cs3500.pa05.view.ViewPassword;
import cs3500.pa05.view.ViewScene;
import cs3500.pa05.view.ViewWeekImpl;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the Java Journal
 */
public class ControlJournal extends Application {

  private ViewScene view;
  private final ArrayList<DayEvent> eventsList = new ArrayList<>();
  private WeekController weekController;
  private final ViewScene viewOpenFile;
  private final List<PlannerJson> planners = new ArrayList<>();
  private final List<Integer> maximums = new ArrayList<>();
  private final OpenController openController = new OpenController(planners, maximums);
  private final List<String> returnedPasswords = new ArrayList<>();

  /**
   * Creates a new Controller
   */
  public ControlJournal() {

    this.viewOpenFile = new ViewOpenFile(this.openController);
  }

  /**
   * Starts the application
   *
   * @param stage the primary stage for this application, onto which
   the application scene can be set.
   Applications may create other stages, if needed, but they will not be
   primary stages.
   */
  @Override
  public void start(Stage stage) {
    stage.setTitle("Weekly Journal");

    try {
      this.openFile();
      this.openSplashScreen();


      String password = planners.get(0).password();
      this.validatePassword(password);

      String weekName = planners.get(0).week().name();

      List<DayEvent> events = extractEvents(planners.get(0));
      List<DayTask> tasks = extractTasks(planners.get(0));
      List<String> categories = planners.get(0).week().categories();

      if (!returnedPasswords.isEmpty()) {
        password = this.returnedPasswords.get(0);
      }

      weekController = new WeekController(events, tasks, weekName, categories, password);
      this.weekController.setStage(stage);
      this.view = new ViewWeekImpl(this.weekController);

      Scene scene = this.view.load();
      stage.setScene(scene);
      this.weekController.run(maximums.get(0), maximums.get(1));

      stage.show();

    } catch (IllegalStateException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Extracts the events from the supplied planner json
   *
   * @param plannerJson the planner to parse through
   * @return a list of day events found in the planner
   */
  private List<DayEvent> extractEvents(PlannerJson plannerJson) {
    List<Day> days = plannerJson.week().days();
    List<DayEvent> events = new ArrayList<>();
    for (Day eachDay : days) {
      events.addAll(eachDay.getEvents());
    }
    return events;
  }

  /**
   * Extracts the tasks from the supplied planner json
   *
   * @param plannerJson the planner to parse through
   * @return a list of day tasks found in the planner
   */
  private List<DayTask> extractTasks(PlannerJson plannerJson) {
    List<Day> days = plannerJson.week().days();
    List<DayTask> tasks = new ArrayList<>();
    for (Day eachDay : days) {
      tasks.addAll(eachDay.getTasks());
    }
    return tasks;
  }

  private void openFile() {
    Stage openFileStage = new Stage();
    this.openController.setStage(openFileStage);
    openFileStage.setScene(this.viewOpenFile.load());
    this.openController.run();
    openFileStage.showAndWait();

  }

  private void openSplashScreen() {
    SplashScreenController splashController = new SplashScreenController();
    ViewScene splashScene = new SplashScreen(splashController);

    Stage splashStage = new Stage();
    splashController.setStage(splashStage);
    splashStage.setScene(splashScene.load());
    splashController.run();
    splashStage.showAndWait();
  }

  private void validatePassword(String actualPassword) {
    this.returnedPasswords.clear();
    PasswordController passwordController =
        new PasswordController(actualPassword, this.returnedPasswords);
    ViewScene passwordView = new ViewPassword(passwordController);

    Stage passwordStage = new Stage();
    passwordController.setStage(passwordStage);
    passwordStage.setScene(passwordView.load());
    passwordController.run();
    passwordStage.showAndWait();


  }
}
