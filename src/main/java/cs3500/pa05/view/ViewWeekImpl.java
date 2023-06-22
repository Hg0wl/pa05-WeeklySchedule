package cs3500.pa05.view;

import cs3500.pa05.controller.WeekController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * An Implementation of viewing the week
 */
public class ViewWeekImpl implements ViewScene {

  private final FXMLLoader loader;

  /**
   * Creates a new ViewWeekImplementation
   *
   * @param controller which is the controller that controls the week views
   */
  public ViewWeekImpl(WeekController controller) {
    this.loader = new FXMLLoader();

    this.loader.setLocation(getClass().getClassLoader().getResource("weekView.fxml"));
    this.loader.setController(controller);
  }

  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout");
    }
  }
}
